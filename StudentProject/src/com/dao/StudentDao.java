package com.dao;

import java.awt.TexturePaint;
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
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import org.w3c.dom.ls.LSInput;

import com.datastruct.HashManager;
import com.datastruct.Hashtable;
import com.datastruct.Hashtable2;
import com.datastruct.HashtableManager;
import com.datastruct.Node;
import com.datastruct.Node2;
import com.model.Student;
import com.util.Config;
import com.util.FileUtil;
import com.util.FilenameManager;
import com.util.HashUtil;
import com.util.JsonUtil;
import com.util.Line;


public class StudentDao {
	private  Hashtable hashtable;
	private  Hashtable2 hashtable2;
	
	public StudentDao(){
		
	}

	public  boolean add(Student student){
		hashtable=HashManager.getHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME);
		hashtable2=HashManager.getHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
		
		Node node=new Node();
		node.setKey(HashUtil.computeKey(student.getStu_code()));
		node.setFilename("stu_"+(node.getKey()%hashtable.getSize())+".txt");
		if(!hashtable.add(node)) return false;
		HashManager.saveHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME, hashtable);
		Node2 node2=new Node2();
		node2.setFilename(node.getFilename());
		node2.setKey(HashUtil.computeKey(student.getStu_name()));
		node2.setMainkey(node.getKey());
		hashtable2.add(node2);
		HashManager.saveHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME, hashtable2);
		

		FileUtil.appenContent(JsonUtil.studentToString(student), node.getFilename());
		
		return true;
	}
	
	
	public boolean remove(Student student){
		hashtable=HashManager.getHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME);
		hashtable2=HashManager.getHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
		
		int mainkey=HashUtil.computeKey(student.getStu_code());
		int key=HashUtil.computeKey(student.getStu_name());
		
		
		Node node=hashtable.getNode(mainkey);
		if(node==null) return false;
		

		
		File file=new File(node.getFilename());
		List<String> data=new ArrayList<>();
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Student student2=JsonUtil.StringToStudent(tmp);
				if(!student2.getStu_code().equals(student.getStu_code())){
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
		//Î¬»¤Ë÷Òý
		if(!hashtable.remove(mainkey)) return false;
		hashtable2.remove(key, mainkey);
		
		HashManager.saveHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME, hashtable);	
		HashManager.saveHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME, hashtable2);
		return true;
	}
	
	
	public boolean update(Student student){
		hashtable=HashManager.getHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME);
		hashtable2=HashManager.getHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
		
		Node node=hashtable.getNode(HashUtil.computeKey(student.getStu_code()));
		if(node==null) return false;
		

		
		File file=new File(node.getFilename());
		List<String> data=new ArrayList<>();
		String tmp=null;
		
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				Student student2=JsonUtil.StringToStudent(tmp);
				if(student2.getStu_code().equals(student.getStu_code())){
					tmp=JsonUtil.studentToString(student);
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
		
		HashManager.saveHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME, hashtable);	
		HashManager.saveHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME, hashtable2);
		return true;
		
	}
	
	
	public Student getStudentByCode(String code){
		hashtable=HashManager.getHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME);
		Student student=null;
		int key=HashUtil.computeKey(code);
		
		Node node=hashtable.getNode(key);
		if(node==null){
			return student;
		}
		

		
		
		String tmp=null;
		File file=new File(node.getFilename());
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				student=JsonUtil.StringToStudent(tmp);
				if(student.getStu_code().equals(key)){
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
		
		return student;
	}
	
	public List<Student> getStudentsByName(String name){
		List<Student> list=null;
		hashtable2=HashManager.getHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
		
		int key=HashUtil.computeKey(name);
		
		List<Node2> node2s=hashtable2.getNode(key);
		if(node2s==null) return list;
		list=new ArrayList<>();
		Iterator<Node2> iterator=node2s.iterator();
		Set<String> filenames=new HashSet<>();
		while(iterator.hasNext()){
			filenames.add(iterator.next().getFilename());
		}

		
		Iterator<String> iterator2=filenames.iterator();
		while(iterator2.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator2.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					Student student=JsonUtil.StringToStudent(tmp);
					if(student.getStu_name().equals(name)) list.add(student);
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
		
		return list;
		
	}
	
	public static void main(String args[]){
		Student student=new Student();
		student.setStu_name("hahad6");
		student.setStu_code("12345830000");
		student.setStu_password("123456300");
		StudentDao studentDao=new StudentDao();
//		Student tmp=studentDao.getStudentByCode(student.getStu_code());
//		System.out.println(tmp.getStu_name());
		List<Student> list=studentDao.getStudentsByName("hahad5");
		if(list!=null){
			Iterator<Student> iterator=list.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next().getStu_code());
			}
		}
	}
}
