package com.atguigu.aspectJ.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	/*
	����һ����
	1. <aop:aspectj-autoproxy/>��Ҫ������Ϊ�����������֪ͨ�����õ���Ŀ������  ���ɡ�������  
	2.
	@Component  //�ȱ�ʶΪһ�������������ཻ��IOC����ȥ���� 
	@Aspect 	//�ٱ�ʶΪһ�����棺����ʶ����������ݣ�
	public class LoggingAspect {
		*//**
		 * ǰ��֪ͨ����Ŀ����󷽷�(���ӵ�)ִ��֮ǰִ�С�
		 *//*
		 //value���������ʽ��������ô�ҵ��������
		@Before("execution(public int com.atguigu.aspectJ.annotation.ArithmeticCalculatorImpl.add(int,int))")   
		public void beforeMethod() {
			System.out.println("LoggintAspect==> The Method xxx begin with [x,y]");
		} 
	}
	3. ���������ҵ������е�֪ͨ�����õ����ArithmeticCalculatorImplĿ�����ϣ�
	  ��ôspring�����1���������ɴ�Ŀ����Ĵ���
	4. ������spring�����л�ȡ���ArithmeticCalculatorImplĿ����Ķ���ʱ�򣬱����������ȡĿ����Ķ���
	   ����spring�����ˣ���Ŀ�����Ǳ������֪ͨ�����ù�/֪ͨ���ġ����ͷ��ش˶���Ĵ������
	   ArithmeticCalculator ac = 
					ctx.getBean("ArithmeticCalculatorImpl",ArithmeticCalculator.class);
	*/

	public static void main(String[] args) {
		
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("spring-aspectJ-annotation.xml");
		//ac�Ǵ������������ǣ�ac������ִ�����Ĵ���
		
		ArithmeticCalculator ac = 
				ctx.getBean("arithmeticCalculatorImpl",ArithmeticCalculator.class);
		
		System.out.println(ac.getClass().getName());//com.sun.proxy.$Proxy7  ������
		
		int result = ac.add(1, 1);
		System.out.println("Main Result:" + result);
		
//		result = ac.div(5, 0);
//		System.out.println("Main Result:" + result);
		
	}
	
	
	
	
	
	

	
}
