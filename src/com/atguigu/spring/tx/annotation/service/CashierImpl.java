package com.atguigu.spring.tx.annotation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashierImpl implements Cashier{

	@Autowired
	private BookShopService bookShopService;
	
	
	@Transactional     
	public void checkOut(String username, List<String> isbns) {
		for(String isbn: isbns) {
			bookShopService.buyBook(username, isbn);
		}
		
	}

}
