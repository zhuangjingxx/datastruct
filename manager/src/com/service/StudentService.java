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
	//ѧ�ţ��������Ա𣬵绰����ѧ��ݣ��༶id��Ժid������
	public boolean add(Student student){
		student.setStu_password(student.getStu_code());//Ĭ�������ѧ����ͬ
		if(studentDao.add(student)){
			Grade grade=null;
			grade=classDao.getGrade(student.getStu_majorName(), student.getStu_className());
			if(grade!=null){
				int num=grade.getGra_num();
				num++;
				grade.setGra_num(num);
				classDao.update(grade);
				
			}else{
				grade=new Grade();
				grade.setGra_majorName(student.getStu_majorName());
				grade.setGra_name(student.getStu_className());
				grade.setGra_num(1);
				classDao.add(grade);
				
			}
			
			return true;
		}
		return false;
	}
	
	
	public boolean remove(Student student){
		if(studentDao.remove(student)){
			//ɾ��һ��ѧ��������ɹ�����˵������༶�Ǵ��ڵ�
			Grade grade=classDao.getGrade(student.getStu_majorName(), student.getStu_className());
			int num=grade.getGra_num();
			num--;
			grade.setGra_num(num);
			classDao.update(grade);
			return true;
			
		} 
		
		return false;
	}
	
	//ѧ�ź����ֲ��ܸ���
	public boolean update(Student student,String oldGradeName,String oldMajorName){
		if(studentDao.update(student)){
			if(!oldGradeName.equals(student.getStu_className())||!oldMajorName.equals(student.getStu_majorName())){
				//�༶�ı䣬�ȸ���ԭ�����ڰ༶���������ٸ��º���༶������
				Grade grade=classDao.getGrade(oldMajorName, oldGradeName);
				int num=grade.getGra_num();
				num--;
				grade.setGra_num(num);
				classDao.update(grade);
				
				
				grade=classDao.getGrade(student.getStu_majorName(), student.getStu_className());
				if(grade!=null){
					int num2=grade.getGra_num();
					num2++;
					grade.setGra_num(num);
					classDao.update(grade);
					
				}else{
					grade=new Grade();
					grade.setGra_majorName(student.getStu_majorName());
					grade.setGra_name(student.getStu_className());
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
		
	}
}
