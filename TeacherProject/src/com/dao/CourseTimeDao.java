package com.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.datastruct.BST;
import com.datastruct.BST2;
import com.datastruct.HashManager;
import com.datastruct.TreeNode;
import com.datastruct.TreeNode2;
import com.model.Course;
import com.model.CourseTime;
import com.util.Config;
import com.util.FileUtil;
import com.util.HashUtil;
import com.util.JsonUtil;

public class CourseTimeDao {

	private BST2 bst2;
	
	public CourseTimeDao(){}
	
	public boolean add(CourseTime courseTime){
		bst2=HashManager.getBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME);
		
		int key= HashUtil.computeKey(courseTime.getCourseName());
		int mainkey=HashUtil.computeKey(courseTime.getDay());
		TreeNode2 treeNode2=null;
		treeNode2=bst2.getNode(bst2.getRoot(),key, mainkey);
		if(treeNode2!=null) return false;
		treeNode2=new TreeNode2();
		treeNode2.setKey(key);
		treeNode2.setMainkey(mainkey);
		treeNode2.setFilename(".\\coursetime_data\\coutime_"+treeNode2.getKey()%20+".txt");
		
		bst2.setRoot(bst2.add(bst2.getRoot(), treeNode2));
		HashManager.saveBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME, bst2);
		
		FileUtil.append(JsonUtil.courseTimeToString(courseTime), treeNode2.getFilename());
		
		return true;
	}
	
	public boolean remove(CourseTime courseTime){
		bst2=HashManager.getBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME);
		
		int key=HashUtil.computeKey(courseTime.getCourseName());
		int mainkey=HashUtil.computeKey(courseTime.getDay());
		TreeNode2 treeNode2=null;
		treeNode2=bst2.getNode(bst2.getRoot(), key, mainkey);
		if(treeNode2==null) return false;
		
		File file=new File(treeNode2.getFilename());
		List<String> data=new ArrayList<>();
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				CourseTime courseTime2=JsonUtil.stringToCourseTime(tmp);
				if(!(courseTime.getClass().equals(courseTime2.getClass())&&courseTime.getDay().equals(courseTime2.getDay()))){
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
		
		bst2.setRoot(bst2.remove(bst2.getRoot(), key, mainkey));
		HashManager.saveBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME, bst2);
		
		return true;
	}
	
	public boolean update(CourseTime courseTime,String oldDay){
		//更新时一个课程不能在同一天上两次课
		if(!oldDay.equals(courseTime.getDay())){
			List<CourseTime> tmps=geCourseTime(courseTime.getCourseName());
			Iterator<CourseTime> iterator=tmps.iterator();
			while(iterator.hasNext()){
				if(iterator.next().getDay().equals(courseTime.getDay())) return false;
			}
		}
		bst2=HashManager.getBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME);
		int key=HashUtil.computeKey(courseTime.getCourseName());
		int mainkey=HashUtil.computeKey(oldDay);
		TreeNode2 treeNode2=null;
		treeNode2=bst2.getNode(bst2.getRoot(), key, mainkey);
		if(treeNode2==null) return false;
		treeNode2.setMainkey(HashUtil.computeKey(courseTime.getDay()));

		File file=new File(treeNode2.getFilename());
		List<String> data=new ArrayList<>();
		String tmp=null;
		
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				CourseTime courseTime2=JsonUtil.stringToCourseTime(tmp);
				
				if((courseTime.getClass().equals(courseTime2.getClass())&&oldDay.equals(courseTime2.getDay()))){
					tmp=JsonUtil.courseTimeToString(courseTime);
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
		HashManager.saveBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME, bst2);
		return true;
	}
	
	public List<CourseTime> geCourseTime(String courseName){
		bst2=HashManager.getBST2(Config.COUTIME_INFORMASTION_CODEINDEX_FILENAME);
		List<CourseTime> courses=null;
		int key=HashUtil.computeKey(courseName);
		
		List<TreeNode2> node2s=new ArrayList<>();
		bst2.getnodes(bst2.getRoot(), key, node2s);
		if(node2s.size()==0) return courses;
		
		courses=new ArrayList<>();
		Set<String> filenames=new HashSet<>();
		Iterator<TreeNode2> iterator=node2s.iterator();
		while(iterator.hasNext()){
			filenames.add(iterator.next().getFilename());
		}
		
		Iterator<String> iterator2=filenames.iterator();
		while(iterator2.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator2.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					CourseTime courseTime=JsonUtil.stringToCourseTime(tmp);
					if(courseTime.getCourseName().equals(courseName)) courses.add(courseTime);
				}
				bufferedReader.close();
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
		CourseTime courseTime=new CourseTime();
		courseTime.setCouuseName("125");
		courseTime.setDay("6");
		CourseTimeDao courseTimeDao=new CourseTimeDao();
		System.out.println(courseTimeDao.add(courseTime));
		List<CourseTime> courseTime2=courseTimeDao.geCourseTime("124");
		System.out.println(courseTime2);
		
	}
}
