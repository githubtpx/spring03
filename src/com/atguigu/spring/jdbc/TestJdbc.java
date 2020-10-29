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
	//����������JdbcTemplate
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
	 * update(): ��ɾ�Ĳ���
	 * 
	 * ��ҵ��ɾ    ��
	 */
	@Test
	public void testUpdate() {
		String sql ="insert into tbl_employee(last_name,email) values(?,?)";
				
		//jdbcTemplate.update(sql,"�˻�","yh@atguigu.com");
		
		//�ɱ�����ײ����һ������ 
		jdbcTemplate.update( sql,new Object []{"�˻�","yh@atguigu.com"} );
	}
	
	
	
	
	/**
	 * batchUpdate()��������ɾ��
	 */
	@Test
	public  void testBatchUpdate() {
		String sql ="insert into tbl_employee(last_name,email) values(?,?)";
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		
		batchArgs.add(new Object[] {"����ʦ","ss@atguigu.com"});
		batchArgs.add(new Object[] {"��ʦ��","kk@atguigu.com"});
		batchArgs.add(new Object[] {"��˾��","sj@atguigu.com"});
		
		
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	
	
	/**
	 * queryForObject()��
	 * 		1. ��ѯ��������   ����һ������
	 * 		2. ��ѯ��ֵ	   ���ص�ֵ
	 */
	@Test
	public void testQueryObjectReturnObject() {
		
		String sql = "select id,last_name lastName,email from tbl_employee where id=?";
		//rowMapper����ӳ�䡣    ���������һ������ӳ��ɾ����һ������
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
		
	}
	
	@Test
	public void testQueryForObjectReturnValue() {
		String sql = "select count(id) from tbl_employee"; //�ǿգ�Ψһ��count()��ͳ�ƿ�ֵ
		
		Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(result);
	}
	
	
	/**
	 * query()����ѯ�������ݷ��ض������ļ��ϡ�
	 */
	@Test
	public void testQuery() {
		String sql = "select id,last_name,email from tbl_employee";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		
		List<Employee> emps = jdbcTemplate.query(sql, rowMapper);
		System.out.println(emps);
		
	}
	
	
	
	/**
	 * ���ԣ���������Jdbc��ģ���ࣺNamedParameterJdbcTemplate
	 */
	
	@Test
	public void testNpjt() {
		String sql = "insert into tbl_employee(last_name,email) values(:ln,:em)";	
		
		//��������������ͨ��Map������ֵ
		Map<String,Object> paramMap = new HashMap<>();
		
		paramMap.put("ln", "������");
		paramMap.put("em", "sxf@sina.com");
		
		//����������Map�е�value���������Ӧ��key���ҵ���sql�ľ������������þ���������key��ȣ���ʱ���ֵ�ŵ���������λ��
		npjt.update(sql, paramMap);
		
	}
	
	@Test
	public void testNpjtObject() {
		//ģ��Service��	ֱ�Ӵ��ݸ�Dao��һ�������	����     
		Employee employee = new Employee(null,"���޼�","zhangwuji@sina.com");
		
		//��dao�Ĳ��뷽���У�
		String sql = "insert into tbl_employee(last_name,email) values(:lastName,:email)";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		
		//��ֱ�Ӹ���һ��bean����paramSource���Ҵ�bean������ȥȡ����������ֵ��
		npjt.update(sql, paramSource);
		
	}
	
	
	
	
	@Test
	public void testEmployeeDao() {
		Employee employee = new Employee(null,"ĪС��","mxb@sina.com");
		employeeDao.insertEmployee(employee);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
