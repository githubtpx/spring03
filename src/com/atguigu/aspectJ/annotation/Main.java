package com.atguigu.aspectJ.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	/*
	分析一波：
	1. <aop:aspectj-autoproxy/>主要作用是为满足给切面中通知能作用到的目标类中  生成“代理”。  
	2.
	@Component  //先标识为一个组件：把这个类交给IOC容器去管理！ 
	@Aspect 	//再标识为一个切面：并标识了这个类的身份！
	public class LoggingAspect {
		*//**
		 * 前置通知：在目标对象方法(连接点)执行之前执行。
		 *//*
		 //value：切入点表达式：就是怎么找到这个方法
		@Before("execution(public int com.atguigu.aspectJ.annotation.ArithmeticCalculatorImpl.add(int,int))")   
		public void beforeMethod() {
			System.out.println("LoggintAspect==> The Method xxx begin with [x,y]");
		} 
	}
	3. 现在上面我的切面中的通知能作用到这个ArithmeticCalculatorImpl目标类上，
	  那么spring会根据1的配置生成此目标类的代理
	4. 而你在spring容器中获取这个ArithmeticCalculatorImpl目标类的对象时候，本意上你想获取目标类的对象。
	   但是spring发现了，此目标类是被切面的通知所作用过/通知过的。它就返回此对象的代理对象。
	   ArithmeticCalculator ac = 
					ctx.getBean("ArithmeticCalculatorImpl",ArithmeticCalculator.class);
	*/

	public static void main(String[] args) {
		
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("spring-aspectJ-annotation.xml");
		//ac是代理对象，如果不是，ac仅仅是执行它的代码
		
		ArithmeticCalculator ac = 
				ctx.getBean("arithmeticCalculatorImpl",ArithmeticCalculator.class);
		
		System.out.println(ac.getClass().getName());//com.sun.proxy.$Proxy7  代理类
		
		int result = ac.add(1, 1);
		System.out.println("Main Result:" + result);
		
//		result = ac.div(5, 0);
//		System.out.println("Main Result:" + result);
		
	}
	
	
	
	
	
	

	
}
