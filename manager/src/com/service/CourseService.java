package com.service;

import java.util.Iterator;
import java.util.List;

import com.dao.CourseDao;
import com.dao.CourseTimeDao;
import com.model.Course;
import com.model.CourseTime;

public class CourseService {

	private CourseDao courseDao;
	private CourseTimeDao courseTimeDao;
	public CourseService(){
		courseDao=new CourseDao();
		courseTimeDao=new CourseTimeDao();
	}
	
	public boolean add(Course course,List<CourseTime> courseTimes){
		Iterator<CourseTime> iterator=courseTimes.iterator();
		while(iterator.hasNext()){
			if(!courseTimeDao.add(iterator.next())) return false;
		}
		if(!courseDao.add(course)) return false;
		
		return true;
		
	}
	
	public boolean remove(Course course,List<CourseTime> courseTimes){
		Iterator<CourseTime> iterator=courseTimes.iterator();
		while(iterator.hasNext()){
			if(!courseTimeDao.remove(iterator.next())) return false;
		}
		if(!courseDao.remove(course)) return false;
		
		return true;
	}
	
	
	public boolean update(Course course,CourseTime courseTime,String oldDay){
		if(courseDao.update(course)&&courseTimeDao.update(courseTime, oldDay)) return true;
		else return false;
	}
	
	public Course getCourse(String name){
		return courseDao.getCourseByname(name);
	}
	
	public List<CourseTime> geCourseTime(String courseName){
		return courseTimeDao.geCourseTime(courseName);
	}
	
	public List<Course> getCoursesByString(String str){
		return courseDao.getCoursesByString(str);
	}
	
	public static void main(String args[]){
		
	}
}
