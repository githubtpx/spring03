package com.atguigu.spring.tx.annotation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atguigu.spring.tx.annotation.exception.BookStockException;
import com.atguigu.spring.tx.annotation.exception.UserAccountException;

@Repository
public class BookeShopDaoImpl implements BookShopDao{
	@Autowired  //�����Զ�װ�䣬����ע��
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public int findPriceByIsbn(String isbn) {
		String sql = "select price from book where isbn = ?";
		
		return  jdbcTemplate.queryForObject(sql, Integer.class,isbn);
	}

	@Override
	public void updateStock(String isbn) {
		//�жϿ���Ƿ��㹻
		String sql = "select stock from book_stock where isbn = ?";
		Integer stock = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		
		if(stock <= 0) {
			throw new BookStockException("��治��...");
		}
		
		//�޸Ŀ��
		sql = "update book_stock set stock = stock -1 where isbn = ?";
		
		jdbcTemplate.update(sql, isbn);
		
	}

	
	
	@Override
	public void updateUserAccount(String username, Integer price) {
		//�ж�����Ƿ��㹻
		String sql = "select balance from account where username = ?";
		Integer balance = jdbcTemplate.queryForObject(sql, Integer.class,username);
		
		if(balance < price) {
			throw new UserAccountException("�û�����");
		}
		
		//�����û����
		sql = "update account set balance = balance - ? where username = ?";
		
		jdbcTemplate.update(sql,price,username);
		
	}
	
	
	

	
}
