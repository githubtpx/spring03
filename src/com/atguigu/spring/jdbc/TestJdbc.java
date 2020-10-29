package com.atguigu.spring.jdbc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.atguigu.spring.jdbc.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class TestJdbc {

	private JdbcTemplate jdbcTemplate;
	//具名参数的JdbcTemplate
	private NamedParameterJdbcTemplate npjt;
	
	private EmployeeDao employeeDao;
	
	
	@Before
	public void init() {
		ApplicationContext  ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
		
		jdbcTemplate = ctx.getBean("jdbcTemplate",JdbcTemplate.class);
		npjt = ctx.getBean("namedParameterJdbcTemplate",NamedParameterJdbcTemplate.class);
		employeeDao = ctx.getBean("employeeDao",EmployeeDao.class);
	}
	
	
	/**
	 * update(): 增删改操作
	 * 
	 * 作业：删    改
	 */
	@Test
	public void testUpdate() {
		String sql ="insert into tbl_employee(last_name,email) values(?,?)";
				
		//jdbcTemplate.update(sql,"运慧","yh@atguigu.com");
		
		//可变参数底层就是一个数组 
		jdbcTemplate.update( sql,new Object []{"运慧","yh@atguigu.com"} );
	}
	
	
	
	
	/**
	 * batchUpdate()：批量增删改
	 */
	@Test
	public  void testBatchUpdate() {
		String sql ="insert into tbl_employee(last_name,email) values(?,?)";
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		
		batchArgs.add(new Object[] {"宋老师","ss@atguigu.com"});
		batchArgs.add(new Object[] {"康师傅","kk@atguigu.com"});
		batchArgs.add(new Object[] {"宋司机","sj@atguigu.com"});
		
		
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	
	
	/**
	 * queryForObject()：
	 * 		1. 查询单行数据   返回一个对象
	 * 		2. 查询单值	   返回单值
	 */
	@Test
	public void testQueryObjectReturnObject() {
		
		String sql = "select id,last_name lastName,email from tbl_employee where id=?";
		//rowMapper：行映射。    将结果集的一条数据映射成具体的一个对象。
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
		
	}
	
	@Test
	public void testQueryForObjectReturnValue() {
		String sql = "select count(id) from tbl_employee"; //非空，唯一，count()不统计空值
		
		Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(result);
	}
	
	
	/**
	 * query()：查询多条数据返回多个对象的集合。
	 */
	@Test
	public void testQuery() {
		String sql = "select id,last_name,email from tbl_employee";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		
		List<Employee> emps = jdbcTemplate.query(sql, rowMapper);
		System.out.println(emps);
		
	}
	
	
	
	/**
	 * 测试，具名参数Jdbc的模板类：NamedParameterJdbcTemplate
	 */
	
	@Test
	public void testNpjt() {
		String sql = "insert into tbl_employee(last_name,email) values(:ln,:em)";	
		
		//具名参数：我们通过Map给它赋值
		Map<String,Object> paramMap = new HashMap<>();
		
		paramMap.put("ln", "宋晓峰");
		paramMap.put("em", "sxf@sina.com");
		
		//它把你具体的Map中的value，根据你对应的key，找到该sql的具名参数，当该具名参数和key相等，此时这个值放到具名参数位置
		npjt.update(sql, paramMap);
		
	}
	
	@Test
	public void testNpjtObject() {
		//模拟Service层	直接传递给Dao层一个具体的	对象     
		Employee employee = new Employee(null,"张无忌","zhangwuji@sina.com");
		
		//在dao的插入方法中：
		String sql = "insert into tbl_employee(last_name,email) values(:lastName,:email)";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		
		//我直接给你一个bean对象paramSource，我从bean对象中去取具名参数的值。
		npjt.update(sql, paramSource);
		
	}
	
	
	
	
	@Test
	public void testEmployeeDao() {
		Employee employee = new Employee(null,"莫小贝","mxb@sina.com");
		employeeDao.insertEmployee(employee);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
