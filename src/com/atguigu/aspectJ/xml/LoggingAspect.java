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
 * ��־���棺
 */

public class LoggingAspect {
	public void beforeMethod(JoinPoint joinPoint) {
 		//��ȡ�����Ĳ���
 		Object[] args = joinPoint.getArgs();
 		
 		//��ȡ����������
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggintAspect==> The Method "+  methodName +" begin with "+Arrays.asList(args));
	}

 	
 	
 	
 	
 	
 	
	public void afterMethod(JoinPoint joinPoint) {
		//��ȡ����������: ǩ��signature
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("LoggingAspect==> The Method " + methodName + " end .");
	}

	
	
	
	
	
	public void afterReturningMethod(JoinPoint joinPoint,Object result) {
 		//��ȡ����������
 		String methodName = joinPoint.getSignature().getName();
 		System.out.println("LoggingAspect==> The method " + methodName + " end with :" + result);
 		
	}
	
	
	
	
	
	
	public void afterThrowingMethod(JoinPoint joinPoint,ArithmeticException ex) {
		//��ȡ����������
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggingAspect==> The method " + methodName + " occurs Exception:" + ex);
	}
	
	
	
	
	
	
	
	public  Object arroundMethod(ProceedingJoinPoint pjp) {
		
		//����֪ͨ����Ϊ�������Ƕ�̬�������������
		try {
			
			//ǰ��֪ͨ��
//	 		Object[] args = pjp.getArgs();
//	 		String methodName = pjp.getSignature().getName();
//			System.out.println("LoggintAspect==> The Method "+  methodName +" begin with "+Arrays.asList(args));
//			
			//ִ��Ŀ�귽����
			Object result = pjp.proceed();
			
			//����֪ͨ��
			
			return result;
		} catch (Throwable e) {
			//�쳣֪ͨ
			e.printStackTrace();
		}finally {
			//����֪ͨ
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	

}
