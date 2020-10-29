package com.atguigu.spring.tx.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.spring.tx.annotation.dao.BookShopDao;
import com.atguigu.spring.tx.annotation.exception.UserAccountException;



@Transactional	//�Ե�ǰ�������з�����������
@Service
public class BookShopServiceImpl implements BookShopService{
	
	//��Ҫʹ��Dao�Ķ���
	@Autowired
	private BookShopDao bookShopDao;
	
	
	/**
	 * spring������BookShopServiceImpl�Ĵ�����󣬸�Ŀ����������������������
	 * 
	 * Spring���ͨ������ʽ������������� ? ��  �������������ԭ��
	 * 1. ����ɨ�����Service���/��ʱ��Ҳ��ɨ�赽@Transactionalע��
	 * 2. ���������ע��֮���൱���Ҿ�֪�������������Ҫ������/(֪ͨ�����й�ע��)�����õġ������������������������������ġ�
	 * 3. �����൱�ڶ�̬�İ�������������������֪ͨ�������õ�������ӵ��ϡ����������Ҫ����ǰ������һ������
	 * 
	 */
	
	
	/**
	 * �������ԣ�
	 * 	 Spring�Ƿ���������ƺ͵ײ����ݿ�û��ϵ���������Ҫ��Ҫ�����µ���������spring���ơ�
	 * 	 1.����Ĵ�����Ϊ Propagation:	һ�����񷽷�������һ�����񷽷�����ʱ����ǰ�������ʹ������
	 * 		 	(Propagation:��������ֳ����ֳ)
	 * 			Propagation.REQUIRED	      Ĭ��ֵ�� ʹ�õ����ߵ�����
	 * 			Propagation.REQUIRES_NEW  �������ߵ��������Ȼ�����¿���������ʹ�á�	
	 * 		
	 * 	 2. ����ĸ��뼶�� isolation��	 ���ڵ����⣺
	 * 			1        ��δ�ύ	                       ���
	 * 			2	�����ύ			 �����ظ���	(���ζ�ȡ�������ݲ�һ�£���Ӧ�޸Ĳ���) --���ã�Ч�ʸߣ��ϰ�ȫ(�Ա���ɱ���ı仯)
	 * 			4	���ظ���--����		 �ö�(��ӦͻȻ����/ɾ��)		--�ɽ��ܣ�����ͳһ��������
	 * 			8	���л�--����		 ���Ŷ�һ��һ�����������/Ч���ر��		
	 * 
	 * 
	 *   3. ����Ļع��벻�ع�	Ĭ������£�Spring������С�����ʱ�쳣�����лع���
	 *   		������ʲô�쳣ʱ���лع�
	 *   		rollbackFor				--ָ���쳣���͵�Class��Class<? extends Throwable>[]
	 *   		rollbackForClassName	--ͨ���ַ���ָ��ʲô�쳣��String[]
	 *   
	 *   		noRollbackFor
	 *   		noRollbackForClassName
	 *   
	 *   4. �����ֻ�����ã�
	 *   		readOnly   Ĭ��false
	 *   			true:   ֻ��      ����Spring������ֻ������ݿ���ж�ȡ�������������޸ĵĲ�����Spring������������Ч�ʿ죬��ʹ����޸Ĳ�����
	 *   						   ���ȷ����ǰ������ֻ�ж�ȡ���������б�Ҫ����Ϊֻ�������԰����������ݿ�
	 *   						   �����Ż�(�������ʵ�)����
	 *   
	 *   			false:	��ֻ��(�ɶ�д)  �������ȡ���ݣ��������޸����ݵĲ�����	-->��֤ ���ݰ�ȫ������
	 *   						
	 *     �������������и����Եģ�ÿ����������޸Ĳ���������Ҫ���������Ԫ�ؽ��м���������
	 *     
	 *   5. ����ĳ�ʱ���ã�	����������ǿ�ƻع�֮ǰ����ռ�õ�ʱ�䡣(�����ܹ�������ʱ��)
	 *   		timeout:    ��ֵѹ�����ԣ����ͬʱ1000���û����봦���ꣿ	��Ҫ������ԣ����ϸ����ԡ�
	 *   
	 */
	
	//ֻ�Ե�ǰ�ķ��������ã�����������
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
		
		//����Ĳ��ع����ԣ�noRollbackFor= {UserAccountException.class}
		//��������񷽷��׳�UserAccountException�쳣�����񲻽��лع�
		bookShopDao.updateUserAccount(username, price);
	}
	
	
	
	
	
}
