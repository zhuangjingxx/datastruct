package com.model;
//学号，姓名，性别，电话，出生日期，班级id，院id，密码

import java.util.Date;

public class Student {

	private String stu_code;
	private String stu_name;
	private int stu_sex;//0表示男
	private String stu_phone;
	
	private String  stu_className;
	private String stu_collegeName;
	private String stu_majorName;
	private int entryYear;
	private String stu_password;
	
	

	public Student(){}
	
	public Student(String stu_code, String stu_name, int stu_sex, String stu_phone, String stu_className,
			String stu_collegeName,String stu_majorName, int entryYear) {
		super();
		this.stu_code = stu_code;
		this.stu_name = stu_name;
		this.stu_sex = stu_sex;
		this.stu_phone = stu_phone;
		this.stu_className = stu_className;
		this.stu_collegeName = stu_collegeName;
		this.stu_majorName=stu_majorName;
		this.entryYear = entryYear;
	}
	
	
	public String getStu_majorName() {
		return stu_majorName;
	}

	public void setStu_majorName(String stu_majorName) {
		this.stu_majorName = stu_majorName;
	}

	public int getEntryYear() {
		return entryYear;
	}
	public void setEntryYear(int entryYear) {
		this.entryYear = entryYear;
	}
	public String getStu_code() {
		return stu_code;
	}
	public void setStu_code(String stu_code) {
		this.stu_code = stu_code;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public int getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(int stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_phone() {
		return stu_phone;
	}
	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}


	public String getStu_className() {
		return stu_className;
	}
	public void setStu_className(String stu_className) {
		this.stu_className = stu_className;
	}
	public String getStu_collegeName() {
		return stu_collegeName;
	}
	public void setStu_collegeName(String stu_collegeName) {
		this.stu_collegeName = stu_collegeName;
	}
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	
	
	
}
