package com.service;

import java.awt.GradientPaint;
import java.awt.GraphicsConfigTemplate;
import java.util.List;

import com.dao.StudentDao;
import com.model.Grade;
import com.model.Student;

import GradeDao.ClassDao;

public class StudentService {

	private StudentDao studentDao;
	private ClassDao classDao;
	public StudentService(){
		studentDao=new StudentDao();
		classDao=new ClassDao();
	}
	//学号，姓名，性别，电话，入学年份，班级id，院id，密码
	public boolean add(String code,String name,int sex,String phone,int entryYear,String gradeName,String collegeName,String majorName){
		Student student=new Student(code, name, sex, phone, gradeName, collegeName,majorName, entryYear);
		student.setStu_password(code);//默认密码和学号相同
		if(studentDao.add(student)){
			Grade grade=classDao.getGrade(majorName, gradeName);
			if(grade!=null){
				int num=grade.getGra_num();
				num++;
				grade.setGra_num(num);
				classDao.update(grade);
				
			}else{
				grade=new Grade();
				grade.setGra_majorName(majorName);
				grade.setGra_name(gradeName);
				grade.setGra_num(1);
				classDao.add(grade);
				
			}
			
			return true;
		}
		return false;
	}
	
	
	public boolean remove(String code,String name,String gradeName,String majorName){
		Student student=new Student();
		student.setStu_code(code);
		student.setStu_name(name);
		if(studentDao.remove(student)){
			//删除一个学生，如果成功，则说明这个班级是存在的
			Grade grade=classDao.getGrade(majorName, gradeName);
			int num=grade.getGra_num();
			num--;
			grade.setGra_num(num);
			classDao.update(grade);
			return true;
			
		} 
		
		return false;
	}
	
	//学号和名字不能更改
	public boolean update(String code,String name,int sex,String phone,int entryYear,String gradeName,String collegeName,
			String majorName,String password,String oldGradeName,String oldMajorName){
		Student student=new Student(code, name, sex, phone, gradeName, collegeName, majorName, entryYear);
		student.setStu_password(password);
		if(studentDao.update(student)){
			if(!oldGradeName.equals(gradeName)||!oldMajorName.equals(majorName)){
				//班级改变，先更新原来所在班级的人数，再更新后面班级的人数
				Grade grade=classDao.getGrade(oldMajorName, oldGradeName);
				int num=grade.getGra_num();
				num--;
				grade.setGra_num(num);
				classDao.update(grade);
				
				
				grade=classDao.getGrade(majorName, gradeName);
				if(grade!=null){
					int num2=grade.getGra_num();
					num2++;
					grade.setGra_num(num);
					classDao.update(grade);
					
				}else{
					grade=new Grade();
					grade.setGra_majorName(majorName);
					grade.setGra_name(gradeName);
					grade.setGra_num(1);
					classDao.add(grade);
					
				}
				
			}
			return true;
		}
		
		return false;
		
	}
	
	public Student getStudentByCode(String code){
		return studentDao.getStudentByCode(code);
	}
	
	public List<Student> getStudentsByname(String name){
		return studentDao.getStudentsByName(name);
	}
	
	
	public static void main(String args[]){
		StudentService service=new StudentService();
		System.out.println(service.add("12388", "yiyi", 0, null, 2015, "16", "13", "88"));
		Student student=service.getStudentByCode("12388");
		if(student!=null) System.out.println(student.getStu_name());
	}
}
