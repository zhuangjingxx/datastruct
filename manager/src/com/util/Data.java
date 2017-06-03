package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.dao.CollegeDao;
import com.dao.MajorDao;
import com.dao.StudentDao;
import com.model.College;
import com.model.Course;
import com.model.CourseTime;
import com.model.Grade;
import com.model.Major;
import com.model.Student;
import com.model.Teacher;
import com.service.CourseService;
import com.service.StudentService;
import com.service.TeacherService;

public class Data {
	
	public void addStudent(){
		List<String> name=new ArrayList<>();
		List<String> code=new ArrayList<>();
		List<String> collegeName=new ArrayList<>();
		List<String> phone=new ArrayList<>();
		
		for(int i=0;i<20000;i++){
			StringBuffer tmp1=new StringBuffer("135600");
			if(i<10) tmp1.append("0000"+i);
			else if(i<100) tmp1.append("000"+i);
			else if(i<1000) tmp1.append("00"+i);
			else if(i<10000) tmp1.append("0"+i);
			else tmp1.append(""+i);
			phone.add(tmp1.toString());
		}
		String tmp=null;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("D:\\studentName.txt"));
			while((tmp=bufferedReader.readLine())!=null) {
				name.add(tmp);
			}
			
			bufferedReader=new BufferedReader(new FileReader("D:\\stu_code.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				code.add(tmp);
			}
			
			System.out.println(code.size());
			bufferedReader=new BufferedReader(new FileReader("D:\\collegeName.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				collegeName.add(tmp);
			}
			
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("D:\\student_tmp.txt"));
			for(int i=0;i<19000;i++){
				Student student=new Student();
				student.setStu_code(code.get(i));
				student.setStu_name(name.get(i));
				student.setEntryYear(2015);
				student.setStu_collegeName(collegeName.get(i%collegeName.size()));
				student.setStu_password(phone.get(i));
				int j=(int)(Math.random()*2);
				if(j==0) student.setStu_sex(0);
				else student.setStu_sex(1);
				int k=(int)(Math.random()*5);
				student.setStu_className(""+(k+1)+"班");
				
				int m=(int)(Math.random()*100);
				
				student.setStu_majorName("专业"+(m+1));
				StudentService studentService=new StudentService();
				studentService.add(student);
				System.out.println(i);
			}
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void addTeacher(){
		List<String> name=new ArrayList<>();
		List<String> code=new ArrayList<>();
		List<String> collegeName=new ArrayList<>();
		List<String> phone=new ArrayList<>();
		List<String> cou_name=new ArrayList<>();
		
		
		for(int i=0;i<20000;i++){
			StringBuffer tmp1=new StringBuffer("135600");
			if(i<10) tmp1.append("0000"+i);
			else if(i<100) tmp1.append("000"+i);
			else if(i<1000) tmp1.append("00"+i);
			else if(i<10000) tmp1.append("0"+i);
			else tmp1.append(""+i);
			phone.add(tmp1.toString());
		}
		String tmp=null;
		
		
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("D:\\TeacherName.txt"));
			while((tmp=bufferedReader.readLine())!=null) {
				name.add(tmp);
			}
			
			bufferedReader=new BufferedReader(new FileReader("D:\\tea_code.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				code.add(tmp);
				System.out.println(tmp);
			}
			
			System.out.println(code.size());
			bufferedReader=new BufferedReader(new FileReader("D:\\collegeName.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				collegeName.add(tmp);
			}
			
			bufferedReader=new BufferedReader(new FileReader("D:\\courseName.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				cou_name.add(tmp);
			}
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("D:\\teacher_tmp.txt"));
			for(int i=0;i<2000;i++){
				Teacher teacher=new Teacher();
				teacher.setTea_code(code.get(i));
				teacher.setTea_name(name.get(i));
				teacher.setTea_colleagName(collegeName.get(i%collegeName.size()));
				teacher.setTea_couname(cou_name.get(i%cou_name.size()));
				teacher.setTea_phone(phone.get(i));
				teacher.setTea_sex((int)(Math.random()*2));
				TeacherService teacherService=new TeacherService();
				teacherService.add(teacher);
				System.out.println(i);
			}
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void addCourse(){
		
		String times[]=new String[]{"星期一","星期二","星期三","星期四","星期五"};
		List<String> courseNames=new ArrayList<>();
		String starts[]=new String[]{"1","2","3","4"};
		String ends[]=new String[]{"15","16","17","18"};
		String builds[]=new String[]{"A1","A2","A3","A4","A5"};
		String floors[]=new String[]{"1","2","3","4","5"};
		String rooms[]=new String[]{"01","02","03","04","05","06","07","08"};
		List<List<Integer>> aList=new ArrayList<>();
		for(int i=0;i<4;i++){
			List<Integer> tmp=new ArrayList<>();
			tmp.add(i+1);
			tmp.add(i+2);
			aList.add(tmp);
		}
		
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("D:\\TeacherName.txt"));
			
			String tmp=null;
			bufferedReader=new BufferedReader(new FileReader("D:\\courseName.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				courseNames.add(tmp);
			}
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("D:\\course_tmp.txt"));
			BufferedWriter bufferedWriter2=new BufferedWriter(new FileWriter("D:\\courseTime_tmp.txt"));
			
			for(int i=0;i<200;i++){
				Course course=new Course();
				List<CourseTime> courseTimes=new ArrayList<>();
				course.setCou_name(courseNames.get(i));
				int week=(int)(Math.random()*4);
				course.setCou_startWeek(starts[week]);
				course.setCou_endWeek(ends[week]);
				int rbuild=(int)(Math.random()*5);
				int rfloor=(int)(Math.random()*5);
				int rroom=(int)(Math.random()*8);
				String locatin=builds[rbuild]+"-"+floors[rfloor]+rooms[rroom];
				course.setCou_loaction(locatin);
				
				
				int n=(int)(Math.random()*2);
				int rday=(int)(Math.random()*5);
				for(int j=-1;j<n;j++){
					CourseTime courseTime=new CourseTime();
					
					courseTime.setCouuseName(courseNames.get(i));
					courseTime.setDay(times[rday%5]);
					rday++;
					int a=(int)(Math.random()*4);
					courseTime.setStart(aList.get(a).get(0));
					courseTime.setEnd(aList.get(a).get(1));
					courseTimes.add(courseTime);
				}
				
				CourseService courseService=new CourseService();
				courseService.add(course, courseTimes);
			}
			
			bufferedWriter.flush();
			bufferedWriter2.flush();
			bufferedWriter.close();
			bufferedWriter2.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void addMajor(){
		List<String> collegeName=new ArrayList<>();
		String tmp=null;

		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\collegeName.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				collegeName.add(tmp);
			}
			
			for(int i=0;i<100;i++){
				Major major=new Major();
				
				int m=(int)(Math.random()*collegeName.size());
				major.setMaj_name("专业"+(i+1));
				major.setMaj_collegeName(collegeName.get(m));
				MajorDao majorDao=new MajorDao();
				majorDao.add(major);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void addCollege(){
		List<String> collegeName=new ArrayList<>();
		String tmp=null;

		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\collegeName.txt"));
			while((tmp=bufferedReader.readLine())!=null){
				collegeName.add(tmp);
			}
			int i=1;
			Iterator<String> iterator=collegeName.iterator();
			while(iterator.hasNext()){
				College college=new College();
				college.setCol_location("地点"+i);
				i++;
				college.setCol_name(iterator.next());
				System.out.println(JsonUtil.collegeToString(college));
				new CollegeDao().add(college);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public static void main(String args[]){
//		List<String> datas=new ArrayList<>();
//		
//		for(int i=0;i<5000;i++){
//			StringBuffer tmp1=new StringBuffer("2015365");
//			if(i<10) tmp1.append("000"+i);
//			else if(i<100) tmp1.append("00"+i);
//			else if(i<1000) tmp1.append("0"+i);
//			else tmp1.append(""+i);
//			datas.add(tmp1.toString());
//		}
//		
//		int a[]=new int[5000];
//		for(int i=0;i<5000;i++) a[i]=i;
//		for(int i=0;i<30000;i++){
//			int j=(int)(Math.random()*5000);
//			int k=(int)(Math.random()*5000);
//			int tmp=a[j];
//			a[j]=a[k];;
//			a[k]=tmp;
//		}
//		System.out.println(datas.size());
//		try {
//			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("D:\\tea_code.txt"));
//			for(int i=0;i<5000;i++){
//				bufferedWriter.write(datas.get(a[i]));
//				bufferedWriter.newLine();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		Data data=new Data();
		data.addCollege();
		
	}
}
