package com.model;
//课程id，课程名，开始上课周数，截止上课周数，
public class Course {

	private String cou_code;
	private String cou_name;
	private String cou_startWeek;
	private String cou_endWeek;
	public String getCou_code() {
		return cou_code;
	}
	public void setCou_code(String cou_code) {
		this.cou_code = cou_code;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public String getCou_startWeek() {
		return cou_startWeek;
	}
	public void setCou_startWeek(String cou_startWeek) {
		this.cou_startWeek = cou_startWeek;
	}
	public String getCou_endWeek() {
		return cou_endWeek;
	}
	public void setCou_endWeek(String cou_endWeek) {
		this.cou_endWeek = cou_endWeek;
	}
	
	
	
}
