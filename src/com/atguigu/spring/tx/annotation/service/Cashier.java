package com.atguigu.spring.tx.annotation.service;

import java.util.List;

public interface Cashier {

	public void checkOut(String username,List<String> isbns);
	
	
}
