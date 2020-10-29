package com.atguigu.aspectJ.annotation;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/*
 * 日志切面：
 * 
 * 切面要完成的工作：通知
 * 通知：就是横切关注点模块化到切面中，描述成通知
 *      1) 对于面向切面编程主要的编程对象是：切面。对于切面来讲主要的编程对象是通知。
 *         所以我们会在切面中写我们的通知！
 *      2)模块化：给横切关注点它全部提取出来，给它生成一个类，这个类就是专门做日志的，
 *         它既可以在方法前、后、抛出异常等等都可以给你做日志的。这个类就是一个切面
 */


@Component  //先标识为一个组件：把这个类交给IOC容器去管理！ 
@Aspect 	//再标识为一个切面：并标识了这个类的身份！
@Order(2)	//设置切面的优先级
public class LoggingAspect {
	/**
	 * 声明切入点表达式
	 */
	@Pointcut("execution(* com.atguigu.aspectJ.annotation.*.*(..))")
	public void declarePointCut() {}
	
	
	
	
	
	
	
	/**
	 * 前置通知：在目标对象方法(连接点)执行之前执行。
	 * 
	 * 连接点对象：JoinPoint
	 */
	/*
	 * 切入点表达式，即注解的value属性：就是怎么找到这个方法
	 */
 	@Before("execution(public int com.atguigu.aspectJ.annotation.ArithmeticCalculatorImpl.add(int,int))")   
	public void beforeMethod(JoinPoint joinPoint) {
 		//获取方法的参数
 		Object[] args = joinPoint.getArgs();
 		
 		//获取方法的名字
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggintAspect==> The Method "+  methodName +" begin with "+Arrays.asList(args));
	}
	
 	
 	
 	
 	
 	
 	
 	
	
	/**
	 * 后置通知：在目标对象方法执行之后执行。不管目标方法有没有抛出异常都执行。不能获取方法的结果。
	 *  特别通用！
	 * 	* com.atguigu.aspectJ.annotation.*.*(..)
	 *  * ：任意修饰符，任意返回值
	 *  * ： 任意类
	 *  * ：任意方法
	 *  ..：任意参数列表
	 *  
	 *  
	 *  连接点对象：JoinPoint
	 *  
	 * 
	 * 切入点表达式：该通知作用到该包下任意类，目标对象的任意方法(*)，任意参数列表(..)，任意修饰符、任意返回值类型(*)
	 */
	//@After("execution(* com.atguigu.aspectJ.annotation.*.*(..))")
 	@After("declarePointCut()")
	public void afterMethod(JoinPoint joinPoint) {
		//获取方法的名字: 签名signature
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("LoggingAspect==> The Method " + methodName + " end .");
	}

	
	
	
	
	
	
	
	/**
	 * 返回通知：在目标对象方法正常执行结束后执行。  可以获取到方法的返回值。
	 * 
	 * 获取方法的返回值：通过返回通知注解的returning属性 来指定一个名字，  必须要与方法中的一个形参名一致
	 */
	//@AfterReturning(value="execution(* com.atguigu.aspectJ.annotation.*.*(..))",returning="result")
 	@AfterReturning(value="declarePointCut()",returning="result")
	public void afterReturningMethod(JoinPoint joinPoint,Object result) {
 		//获取方法的名字
 		String methodName = joinPoint.getSignature().getName();
 		System.out.println("LoggingAspect==> The method " + methodName + " end with :" + result);
 		
	}
	
	
	
	
	/**
	 * 异常 通知： 在目标对象方法抛出异常后执行                获取目标方法的异常信息
	 * 
	 * 
	 * 获取方法的异常：通过异常 通知注解的throwing属性 来指定一个名字，  必须要与方法中的一个形参名一致
	 * 
	 * 可以通过形参中异常的类型 来设置抛出指定异常才会执行异常通知。(多态)
	 * 
	 */
	@AfterThrowing(value="execution(* com.atguigu.aspectJ.annotation.*.*(..))",throwing="ex")
	public void afterThrowingMethod(JoinPoint joinPoint,ArithmeticException ex) {
		//获取方法的名字
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggingAspect==> The method " + methodName + " occurs Exception:" + ex);
	}
	
	
	/**
	 * 环绕通知： 环绕着目标对象方法执行。  可以理解为  前置   后置   返回   异常 通知的结合体，更像是动态代理的整个过程。
	 * 
	 * 连接点对象：ProceedingJoinPoint  它是JoinPoint子类
	 * 
	 */
	@Around(value="execution(* com.atguigu.aspectJ.annotation.*.*(..))")
	public  Object arroundMethod(ProceedingJoinPoint pjp) {
		
		
		//环绕通知：因为它更像是动态代理的整个过程
		try {
			
			//前置通知：
//	 		Object[] args = pjp.getArgs();
//	 		String methodName = pjp.getSignature().getName();
//			System.out.println("LoggintAspect==> The Method "+  methodName +" begin with "+Arrays.asList(args));
//			
			//执行目标方法：
			Object result = pjp.proceed();
			
			//返回通知：
			
			return result;
		} catch (Throwable e) {
			//异常通知
			e.printStackTrace();
		}finally {
			//后置通知
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	

}
