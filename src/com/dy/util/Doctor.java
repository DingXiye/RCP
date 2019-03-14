package com.dy.util;

public class Doctor {
	private String name;
	private String password;
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Doctor(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
}
