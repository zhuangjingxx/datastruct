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
import java.util.Iterator;
import java.util.List;

import com.model.College;
import com.util.Config;
import com.util.JsonUtil;

public class CollegeDao {

	public boolean add(College college){
		BufferedReader bufferedReader;
		
		try {
			File file=new File(Config.COL_INFORMASTION_FILENAME);
			if(!file.exists()){
				BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
				bufferedWriter.append(JsonUtil.collegeToString(college));
				bufferedWriter.newLine();
				bufferedWriter.close();
				return true;
			}
			bufferedReader = new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				College college2=JsonUtil.stringToCollege(tmp);
				if(college.getCol_name().equals(college2.getCol_name())){
					return false;
				}
			}
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
			bufferedWriter.append(JsonUtil.collegeToString(college));
			bufferedWriter.newLine();
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
	
	public  boolean reomve(College college){
		List<String> data=new ArrayList<>();
		boolean flag=false;
		try {
			File file=new File(Config.COL_INFORMASTION_FILENAME);
			if(!file.exists()){
				return false;
			}
			BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(Config.COL_INFORMASTION_FILENAME)));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				College college2=JsonUtil.stringToCollege(tmp);
				if(college2.getCol_name().equals(college.getCol_name())) {
					flag=true;
					continue;
				}
				data.add(tmp);
			}
			bufferedReader.close();
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File(Config.COL_INFORMASTION_FILENAME)));
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
		
		return flag;
		
	}
	
	
	public boolean update(College college){
		List<String> data=new ArrayList<>();
		boolean flag=false;
		try {
			File file=new File(Config.COL_INFORMASTION_FILENAME);
			if(!file.exists()) return false;
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				College college2=JsonUtil.stringToCollege(tmp);
				if(college2.getCol_name().equals(college.getCol_name())) {
					flag=true;
					tmp=JsonUtil.collegeToString(college);
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
		
		return flag;
		
	}
	
	public College getCollege(String name){
		College college=null;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(Config.COL_INFORMASTION_FILENAME)));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				College college2=JsonUtil.stringToCollege(tmp);
				if(college2.getCol_name().equals(name)){
					college=college2;
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
		return college;
	}
	
	public static void main(String args[]){
		College college=new College();
		college.setCol_name("dddj");
		college.setCol_location("djk");
		CollegeDao collegeDao=new CollegeDao();
		System.out.println(collegeDao.update(college));
		collegeDao.reomve(college);
		College tmp=collegeDao.getCollege(college.getCol_name());
		if(tmp!=null) System.out.println(tmp.getCol_location());
	}
}
