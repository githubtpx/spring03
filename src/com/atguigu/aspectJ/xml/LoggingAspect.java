package com.atguigu.aspectJ.xml;
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
 */

public class LoggingAspect {
	public void beforeMethod(JoinPoint joinPoint) {
 		//获取方法的参数
 		Object[] args = joinPoint.getArgs();
 		
 		//获取方法的名字
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggintAspect==> The Method "+  methodName +" begin with "+Arrays.asList(args));
	}

 	
 	
 	
 	
 	
 	
	public void afterMethod(JoinPoint joinPoint) {
		//获取方法的名字: 签名signature
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("LoggingAspect==> The Method " + methodName + " end .");
	}

	
	
	
	
	
	public void afterReturningMethod(JoinPoint joinPoint,Object result) {
 		//获取方法的名字
 		String methodName = joinPoint.getSignature().getName();
 		System.out.println("LoggingAspect==> The method " + methodName + " end with :" + result);
 		
	}
	
	
	
	
	
	
	public void afterThrowingMethod(JoinPoint joinPoint,ArithmeticException ex) {
		//获取方法的名字
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggingAspect==> The method " + methodName + " occurs Exception:" + ex);
	}
	
	
	
	
	
	
	
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
