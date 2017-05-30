package com.model;
//课程id，课程名，开始上课周数，截止上课周数，
public class Course {


	private String cou_name;//唯一标识一门课程
	private String cou_startWeek;
	private String cou_endWeek;
	private String cou_loaction;

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
	public String getCou_loaction() {
		return cou_loaction;
	}
	public void setCou_loaction(String cou_loaction) {
		this.cou_loaction = cou_loaction;
	}
	
	
	
}
