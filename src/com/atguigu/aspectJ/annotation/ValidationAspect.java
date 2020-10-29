package com.atguigu.aspectJ.annotation;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ��֤����
 */
@Component	//��ʶΪһ�����
@Aspect		//��ʶΪһ������
@Order(1)	//������������ȼ�    	  2147483647(int���ֵ)
public class ValidationAspect {
	
	//���ǰ��֪ͨ�����ڣ�ʹ���������ʽ (ʹ����/������ȷ�����ӵ�λ��) 
	//@Before("execution(* com.atguigu.aspectJ.annotation.*.*(..))")
	
	@Before("LoggingAspect.declarePointCut()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		Object args[] = joinPoint.getArgs();
		
		System.out.println("ValidationAspect==> The method "+ methodName + " begin with " + Arrays.asList(args));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/* int���ֵ  -- 4���ֽ�  -- 1���ֽ�8λ��  -- �ܹ�32λ����λ����λ������: 
 * ���ֵ�������ǣ� 0 ���涼��1		���ֵ+1 = ��Сֵ
 * ��Сֵ�������ǣ� 1 ���涼��0		��Сֵ-1 = ���ֵ
 * ������תʮ�����ƣ���������λΪ�����Ƶ�һλ��15=f
 * Integer.MAX_VALUE,   0x7fffffff,   01111111	 11111111  11111111  11111111
*/
	
	
	
	
	
	
	
	

}
