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
 * ��־���棺
 * 
 * ����Ҫ��ɵĹ�����֪ͨ
 * ֪ͨ�����Ǻ��й�ע��ģ�黯�������У�������֪ͨ
 *      1) ����������������Ҫ�ı�̶����ǣ����档��������������Ҫ�ı�̶�����֪ͨ��
 *         �������ǻ���������д���ǵ�֪ͨ��
 *      2)ģ�黯�������й�ע����ȫ����ȡ��������������һ���࣬��������ר������־�ģ�
 *         ���ȿ����ڷ���ǰ�����׳��쳣�ȵȶ����Ը�������־�ġ���������һ������
 */


@Component  //�ȱ�ʶΪһ�������������ཻ��IOC����ȥ���� 
@Aspect 	//�ٱ�ʶΪһ�����棺����ʶ����������ݣ�
@Order(2)	//������������ȼ�
public class LoggingAspect {
	/**
	 * �����������ʽ
	 */
	@Pointcut("execution(* com.atguigu.aspectJ.annotation.*.*(..))")
	public void declarePointCut() {}
	
	
	
	
	
	
	
	/**
	 * ǰ��֪ͨ����Ŀ����󷽷�(���ӵ�)ִ��֮ǰִ�С�
	 * 
	 * ���ӵ����JoinPoint
	 */
	/*
	 * �������ʽ����ע���value���ԣ�������ô�ҵ��������
	 */
 	@Before("execution(public int com.atguigu.aspectJ.annotation.ArithmeticCalculatorImpl.add(int,int))")   
	public void beforeMethod(JoinPoint joinPoint) {
 		//��ȡ�����Ĳ���
 		Object[] args = joinPoint.getArgs();
 		
 		//��ȡ����������
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggintAspect==> The Method "+  methodName +" begin with "+Arrays.asList(args));
	}
	
 	
 	
 	
 	
 	
 	
 	
	
	/**
	 * ����֪ͨ����Ŀ����󷽷�ִ��֮��ִ�С�����Ŀ�귽����û���׳��쳣��ִ�С����ܻ�ȡ�����Ľ����
	 *  �ر�ͨ�ã�
	 * 	* com.atguigu.aspectJ.annotation.*.*(..)
	 *  * ���������η������ⷵ��ֵ
	 *  * �� ������
	 *  * �����ⷽ��
	 *  ..����������б�
	 *  
	 *  
	 *  ���ӵ����JoinPoint
	 *  
	 * 
	 * �������ʽ����֪ͨ���õ��ð��������࣬Ŀ���������ⷽ��(*)����������б�(..)���������η������ⷵ��ֵ����(*)
	 */
	//@After("execution(* com.atguigu.aspectJ.annotation.*.*(..))")
 	@After("declarePointCut()")
	public void afterMethod(JoinPoint joinPoint) {
		//��ȡ����������: ǩ��signature
		String methodName = joinPoint.getSignature().getName();
		
		System.out.println("LoggingAspect==> The Method " + methodName + " end .");
	}

	
	
	
	
	
	
	
	/**
	 * ����֪ͨ����Ŀ����󷽷�����ִ�н�����ִ�С�  ���Ի�ȡ�������ķ���ֵ��
	 * 
	 * ��ȡ�����ķ���ֵ��ͨ������֪ͨע���returning���� ��ָ��һ�����֣�  ����Ҫ�뷽���е�һ���β���һ��
	 */
	//@AfterReturning(value="execution(* com.atguigu.aspectJ.annotation.*.*(..))",returning="result")
 	@AfterReturning(value="declarePointCut()",returning="result")
	public void afterReturningMethod(JoinPoint joinPoint,Object result) {
 		//��ȡ����������
 		String methodName = joinPoint.getSignature().getName();
 		System.out.println("LoggingAspect==> The method " + methodName + " end with :" + result);
 		
	}
	
	
	
	
	/**
	 * �쳣 ֪ͨ�� ��Ŀ����󷽷��׳��쳣��ִ��                ��ȡĿ�귽�����쳣��Ϣ
	 * 
	 * 
	 * ��ȡ�������쳣��ͨ���쳣 ֪ͨע���throwing���� ��ָ��һ�����֣�  ����Ҫ�뷽���е�һ���β���һ��
	 * 
	 * ����ͨ���β����쳣������ �������׳�ָ���쳣�Ż�ִ���쳣֪ͨ��(��̬)
	 * 
	 */
	@AfterThrowing(value="execution(* com.atguigu.aspectJ.annotation.*.*(..))",throwing="ex")
	public void afterThrowingMethod(JoinPoint joinPoint,ArithmeticException ex) {
		//��ȡ����������
 		String methodName = joinPoint.getSignature().getName();
 		
		System.out.println("LoggingAspect==> The method " + methodName + " occurs Exception:" + ex);
	}
	
	
	/**
	 * ����֪ͨ�� ������Ŀ����󷽷�ִ�С�  �������Ϊ  ǰ��   ����   ����   �쳣 ֪ͨ�Ľ���壬�����Ƕ�̬������������̡�
	 * 
	 * ���ӵ����ProceedingJoinPoint  ����JoinPoint����
	 * 
	 */
	@Around(value="execution(* com.atguigu.aspectJ.annotation.*.*(..))")
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
