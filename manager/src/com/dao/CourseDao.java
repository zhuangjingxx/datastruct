package com.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.event.TreeExpansionEvent;

import com.datastruct.BST;
import com.datastruct.BST2;
import com.datastruct.HashManager;
import com.datastruct.Node2;
import com.datastruct.TreeNode;
import com.datastruct.TreeNode2;
import com.model.Course;
import com.model.Teacher;
import com.util.Config;
import com.util.FileUtil;
import com.util.HashUtil;
import com.util.JsonUtil;

public class CourseDao {
	private BST bst;
	
	
	public boolean add(Course course){
		
		bst=HashManager.getBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME);
		int key= HashUtil.computeKey(course.getCou_name());
		TreeNode treeNode=null;
		treeNode=bst.getNode(bst.getRoot(),key);
		if(treeNode!=null) return false;
		treeNode=new TreeNode();
		treeNode.setKey(key);
		treeNode.setFilename("cou_"+treeNode.getKey()%20+".txt");
		
		bst.setRoot(bst.add(bst.getRoot(), treeNode));
		HashManager.saveBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME, bst);
		
		FileUtil.append(JsonUtil.courseToString(course), treeNode.getFilename());
		
		return true;
	}
	
	
	public boolean remove(Course course){
		bst=HashManager.getBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME);
		
		int key=HashUtil.computeKey(course.getCou_name());
		TreeNode treeNode=null;
		treeNode=bst.getNode(bst.getRoot(), key);
		if(treeNode==null) return false;
		
		File file=new File(treeNode.getFilename());
		List<String> data=new ArrayList<>();
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Course course2=JsonUtil.stringToCourse(tmp);
				if(!course2.getCou_name().equals(course.getCou_name())){
					data.add(tmp);
				}
			}
			bufferedReader.close();
			BufferedWriter bufferedWriter=new BufferedWriter( new FileWriter(file));
			Iterator<String> iterator=data.iterator();
			while(iterator.hasNext()){
				bufferedWriter.write(iterator.next());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bst.setRoot(bst.remove(bst.getRoot(), key));
		HashManager.saveBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME, bst);
		
		return true;
	}
	
	
	public boolean update(Course course){
		bst=HashManager.getBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME);
		int key=HashUtil.computeKey(course.getCou_name());
		TreeNode treeNode=null;
		treeNode=bst.getNode(bst.getRoot(), key);
		if(treeNode==null) return false;
		

		File file=new File(treeNode.getFilename());
		List<String> data=new ArrayList<>();
		String tmp=null;
		
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				Course course2=JsonUtil.stringToCourse(tmp);
				
				if(course.getCou_name().equals(course2.getCou_name())){
					tmp=JsonUtil.courseToString(course);
				}
				data.add(tmp);
			}
			bufferedReader.close();
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
			Iterator<String> iterator=data.iterator();
			while(iterator.hasNext()){
				bufferedWriter.write(iterator.next());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public Course getCourseByname(String name){
		Course course=null;
		bst=HashManager.getBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME);
		
		int key=HashUtil.computeKey(name);
		TreeNode treeNode=bst.getNode(bst.getRoot(), key);
		if(treeNode==null) return course;
		File file=new File(treeNode.getFilename());
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				course=JsonUtil.stringToCourse(tmp);
				if(course.getCou_name().equals(name)){
					break;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
		
	}
	
	public List<Course> getCoursesByString(String str){
		List<Course> courses=null;
		bst=HashManager.getBST(Config.COU_INFORMASTION_CODEINDEX_FILENAME);
		
		Set<String> filenames=new HashSet<>();
		bst.getFilenames(bst.getRoot(), filenames);
		if(filenames.size()==0) return courses;
		
		Iterator<String> iterator=filenames.iterator();
		courses=new ArrayList<>();
		while(iterator.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					Course course=JsonUtil.stringToCourse(tmp);
					if(course.getCou_name().contains(str)){
						courses.add(course);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return courses;
	}
	
	public static void main(String args[]){
		Course course=new Course();
		course.setCou_name("hahada8");
		course.setCou_startWeek("3");
		CourseDao courseDao=new CourseDao();
		courseDao.add(course);
		List<Course> tmp=courseDao.getCoursesByString("hahada");
		System.out.println(tmp);
		
	}
}
