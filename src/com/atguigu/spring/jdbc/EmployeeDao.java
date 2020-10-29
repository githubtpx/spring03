package com.atguigu.spring.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	
	@Autowired   //自动装配
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate npjt;
	

	public void insertEmployee(Employee employee) {
		String sql ="insert into tbl_employee(last_name,email) values(:lastName,:email)";
		
		//该Bean对象的属性作为参数来源
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee); 
		npjt.update(sql, paramSource);
	}
	
	
	
	
}
