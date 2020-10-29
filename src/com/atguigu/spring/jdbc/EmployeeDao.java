package com.atguigu.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	
	@Autowired   //�Զ�װ��
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate npjt;
	

	public void insertEmployee(Employee employee) {
		String sql ="insert into tbl_employee(last_name,email) values(:lastName,:email)";
		
		//��Bean�����������Ϊ������Դ
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee); 
		npjt.update(sql, paramSource);
	}
	
	
	
	
}
