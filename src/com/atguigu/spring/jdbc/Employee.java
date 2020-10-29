package com.atguigu.spring.jdbc;

public class Employee {
	private Integer id;
	
	private String lastName;
	
	private String email;
	
	public Employee() {
		super();
	}
	
	
	public Employee(Integer id, String lastName, String email) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}
