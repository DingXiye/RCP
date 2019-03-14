package com.dy.editor;
import java.util.Date;
/**
 * 实体对象
 * @author dingye
 *
 */
public class StaffEntity {
	private int age;
	private Date createDate;
	private String department;
	private int id;
	private String name;
	private int phone;
	private String relatPeople;
	private boolean sex;
	public int getAge() {
		return age;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getDepartment() {
		return department;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPhone() {
		return phone;
	}
	public String getRelatPeople() {
		return relatPeople;
	}
	public boolean isSex() {
		return sex;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public void setRelatPeople(String relatPeople) {
		this.relatPeople = relatPeople;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	public StaffEntity(int id ,String name ,boolean sex,int age,int phone,String department,String relatPeople,Date createDate){
		this.id=id ;
		this.name=name;
		this.sex=sex;
		this.age=age;
		this.phone=phone;
		this.department=department;
		this.relatPeople=relatPeople;
		this.createDate=createDate;
	}
}
