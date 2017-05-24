package com.model;
//   属性：id,课程id,上课日期（周一-周五),开始的节数，结束的节数
public class CourseTime {

	private String sout_code;
	private String couuseId;
	private String day;
	private int start;
	private int end;
	public String getSout_code() {
		return sout_code;
	}
	public void setSout_code(String sout_code) {
		this.sout_code = sout_code;
	}
	public String getCouuseId() {
		return couuseId;
	}
	public void setCouuseId(String couuseId) {
		this.couuseId = couuseId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
	
}
