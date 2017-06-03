package com.model;
//工号，姓名，性别，，电话，院名，密码，课程名

import java.util.Date;

public class Teacher {

	private String tea_code;
	private String tea_name;
	private int  tea_sex;

	private String tea_phone;
	private String tea_colleagName;
	private String tea_password;
	private String tea_couname;
	public String getTea_code() {
		return tea_code;
	}
	public void setTea_code(String tea_code) {
		this.tea_code = tea_code;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	
	

	public int getTea_sex() {
		return tea_sex;
	}
	public void setTea_sex(int tea_sex) {
		this.tea_sex = tea_sex;
	}
	public String getTea_phone() {
		return tea_phone;
	}
	public void setTea_phone(String tea_phone) {
		this.tea_phone = tea_phone;
	}
	
	
	public String getTea_colleagName() {
		return tea_colleagName;
	}
	public void setTea_colleagName(String tea_colleagName) {
		this.tea_colleagName = tea_colleagName;
	}
	public String getTea_password() {
		return tea_password;
	}
	public void setTea_password(String tea_password) {
		this.tea_password = tea_password;
	}
	public String getTea_couname() {
		return tea_couname;
	}
	public void setTea_couname(String tea_couname) {
		this.tea_couname = tea_couname;
	}
	
	
	
}
