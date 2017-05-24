package com.model;
//学号，姓名，性别，电话，出生日期，班级id，院id，密码

import java.util.Date;

public class Student {

	private String stu_code;
	private String stu_name;
	private int stu_sex;
	private String stu_phone;
	private Date stu_birthday;
	
	private String  stu_classId;
	private String stu_collegeId;
	private String stu_password;
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
	public Date getStu_birthday() {
		return stu_birthday;
	}
	public void setStu_birthday(Date stu_birthday) {
		this.stu_birthday = stu_birthday;
	}
	public String getStu_classId() {
		return stu_classId;
	}
	public void setStu_classId(String stu_classId) {
		this.stu_classId = stu_classId;
	}
	public String getStu_collegeId() {
		return stu_collegeId;
	}
	public void setStu_collegeId(String stu_collegeId) {
		this.stu_collegeId = stu_collegeId;
	}
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	
	
	
}
