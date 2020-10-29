package com.atguigu.spring.tx.annotation.service;

public interface BookShopService {
	
	//买书方法的参数：哪个人？买了哪些书？
	public void buyBook(String username,String isbn);

}
