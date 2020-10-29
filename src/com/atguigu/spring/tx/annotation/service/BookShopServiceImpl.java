package com.atguigu.spring.tx.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.spring.tx.annotation.dao.BookShopDao;
import com.atguigu.spring.tx.annotation.exception.UserAccountException;



@Transactional	//对当前类中所有方法都起作用
@Service
public class BookShopServiceImpl implements BookShopService{
	
	//需要使用Dao的对象
	@Autowired
	private BookShopDao bookShopDao;
	
	
	/**
	 * spring会生成BookShopServiceImpl的代理对象，给目标对象加上了事务管理的切面
	 * 
	 * Spring如何通过声明式事务来事务管理 ? （  事务管理器对象原理）
	 * 1. 它在扫描这个Service组件/类时候，也会扫描到@Transactional注解
	 * 2. 它看到这个注解之后，相当于我就知道了这个方法是要被事务/(通知：横切关注点)所作用的。而事务是由事务管理器切面所管理的。
	 * 3. 它就相当于动态的把事务管理器切面里面的通知功能作用到这个连接点上。这个过程需要给当前类生成一个代理。
	 * 
	 */
	
	
	/**
	 * 事务属性：
	 * 	 Spring是否开启事务控制和底层数据库没关系。这个事务要不要开启新的事物是由spring控制。
	 * 	 1.事务的传播行为 Propagation:	一个事务方法被另外一个事务方法调用时，当前事务如何使用事务。
	 * 		 	(Propagation:传播；繁殖；增殖)
	 * 			Propagation.REQUIRED	      默认值： 使用调用者的事务。
	 * 			Propagation.REQUIRES_NEW  将调用者的事务挂起，然后重新开启事务来使用。	
	 * 		
	 * 	 2. 事务的隔离级别 isolation：	 存在的问题：
	 * 			1        读未提交	                       脏读
	 * 			2	读已提交			 不可重复读	(两次读取回来数据不一致，对应修改操作) --常用：效率高，较安全(淘宝秒杀量的变化)
	 * 			4	可重复读--行锁		 幻读(对应突然插入/删除)		--可接受（数据统一，完整）
	 * 			8	串行化--表锁		 排着队一个一个操作这个表/效率特别低		
	 * 
	 * 
	 *   3. 事务的回滚与不回滚	默认情况下，Spring会对所有“运行时异常”进行回滚。
	 *   		当出现什么异常时进行回滚
	 *   		rollbackFor				--指定异常类型的Class，Class<? extends Throwable>[]
	 *   		rollbackForClassName	--通过字符串指定什么异常，String[]
	 *   
	 *   		noRollbackFor
	 *   		noRollbackForClassName
	 *   
	 *   4. 事务的只读设置：
	 *   		readOnly   默认false
	 *   			true:   只读      告诉Spring代表着只会对数据库进行读取操作，不会有修改的操作。Spring不会给你加锁，效率快，即使你会修改操作。
	 *   						   如果确保当前的事务只有读取操作，就有必要设置为只读，可以帮助我们数据库
	 *   						   引擎优化(其它访问的)事务。
	 *   
	 *   			false:	非只读(可读写)  不仅会读取数据，还会有修改数据的操作。	-->保证 数据安全，完整
	 *   						
	 *     事务与事务是有隔离性的，每个事务进行修改操作，必须要对其操作的元素进行加锁操作。
	 *     
	 *   5. 事务的超时设置：	设置事务在强制回滚之前可以占用的时间。(事务总共操作的时间)
	 *   		timeout:    峰值压力测试：最多同时1000个用户几秒处理完？	需要合理测试，加上该属性。
	 *   
	 */
	
	//只对当前的方法起作用，方法上优先
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED,
			/* noRollbackFor= {UserAccountException.class} */
			readOnly=false,
			timeout=3)	
	
	
	public void buyBook(String username, String isbn) {
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		Integer price = bookShopDao.findPriceByIsbn(isbn);
		
		bookShopDao.updateStock(isbn);
		
		//事务的不回滚属性：noRollbackFor= {UserAccountException.class}
		//当这个事务方法抛出UserAccountException异常后，事务不进行回滚
		bookShopDao.updateUserAccount(username, price);
	}
	
	
	
	
	
}
