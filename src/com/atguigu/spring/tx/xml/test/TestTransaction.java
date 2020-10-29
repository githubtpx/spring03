package com.atguigu.spring.tx.xml.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.spring.tx.xml.dao.BookShopDao;
import com.atguigu.spring.tx.xml.service.BookShopService;
import com.atguigu.spring.tx.xml.service.Cashier;


public class TestTransaction {
	
	private BookShopDao bookShopDao;
	
	private BookShopService bookShopService;
	
	private Cashier cashier;
	
	@Before
	public void init() {
		//
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-tx_xml.xml");
		bookShopDao = ctx.getBean("bookShopDaoImpl",BookShopDao.class);
		bookShopService = ctx.getBean("bookShopServiceImpl",BookShopService.class);
		cashier = ctx.getBean("cashierImpl",Cashier.class);
		
		System.out.println(bookShopService.getClass().getName());  //com.sun.proxy.$Proxy14
	}
	
	@Test
	public void testTx() {
		bookShopService.buyBook("Tom", "ISBN-001");
	}

	
	@Test
	public void testCheckOut() {	//两本都买成功     买成功其中一本    两本都没买成功
		
		List<String> isbns = new ArrayList<>();
		
		isbns.add("ISBN-001");
		isbns.add("ISBN-002");
		
		cashier.checkOut("Tom", isbns);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
