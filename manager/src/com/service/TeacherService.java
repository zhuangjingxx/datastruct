package com.service;

import java.util.List;

import com.dao.TeacherDao;
import com.model.Teacher;

public class TeacherService {

	private TeacherDao teacherDao;
	
	
	public TeacherService(){
		teacherDao=new TeacherDao();
	}
	
	public boolean add(Teacher teacher){
		return teacherDao.add(teacher);
	}
	
	public boolean remove(Teacher teacher){
		return teacherDao.remove(teacher);
	}
	
	//工号和名字不能改
	public boolean update(Teacher teacher){
		return teacherDao.update(teacher);
	}
	
	public Teacher geTeacherByCode(String code){
		return teacherDao.getTeacherByCode(code);
	}
	
	public List<Teacher> getTeachersByName(String name){
		return teacherDao.geTeachersByName(name);
	}
	
	
}
