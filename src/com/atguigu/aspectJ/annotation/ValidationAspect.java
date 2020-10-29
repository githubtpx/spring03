package com.atguigu.aspectJ.annotation;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 验证切面
 */
@Component	//标识为一个组件
@Aspect		//标识为一个切面
@Order(1)	//设置切面的优先级    	  2147483647(int最大值)
public class ValidationAspect {
	
	//这个前置通知作用于：使用切入点表达式 (使用纵/横坐标确定连接点位置) 
	//@Before("execution(* com.atguigu.aspectJ.annotation.*.*(..))")
	
	@Before("LoggingAspect.declarePointCut()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		
		Object args[] = joinPoint.getArgs();
		
		System.out.println("ValidationAspect==> The method "+ methodName + " begin with " + Arrays.asList(args));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/* int最大值  -- 4个字节  -- 1个字节8位数  -- 总共32位，高位符号位二进制: 
 * 最大值二进制是： 0 后面都是1		最大值+1 = 最小值
 * 最小值二进制是： 1 后面都是0		最小值-1 = 最大值
 * 二进制转十六进制：二进制四位为六进制的一位，15=f
 * Integer.MAX_VALUE,   0x7fffffff,   01111111	 11111111  11111111  11111111
*/
	
	
	
	
	
	
	
	

}
