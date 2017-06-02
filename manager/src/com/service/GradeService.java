package com.service;

import com.model.Grade;

import GradeDao.ClassDao;

public class GradeService {

	private ClassDao classDao;
	
	public GradeService(){
		classDao=new ClassDao();
	}
	
	public boolean add(Grade grade){
		return classDao.add(grade);
	}
	
	public boolean remove(Grade grade){
		return classDao.remove(grade);
	}
	
	public boolean update(Grade grade){
		return classDao.update(grade);
	}
	
	public Grade getGrade(String gra_name,String maj_name){
		return classDao.getGrade(maj_name, gra_name);
	}
}
