package com.datastruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.attribute.FileTime;
import java.util.Hashtable;

import com.google.gson.stream.JsonToken;
import com.model.Student;
import com.util.Config;
import com.util.FileUtil;
import com.util.JsonUtil;

public class StudentTable {

//	private static MyHashtable name=null;
//	private static MyHashtable code=null;
//	static{
//		name=HashtableManager.getHashtable(Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
//		code=HashtableManager.getHashtable(Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME);
//	}
//	
//	private static HashtableNode getNode(Student student,String key){
//		HashtableNode hashtableNode=new HashtableNode();
//		hashtableNode.setKey(key);
//		hashtableNode.setSize(JsonUtil.studentToString(student).length());
//		hashtableNode.setOffset(FileUtil.getFileSize(Config.STU_INFORMASTION_FILENAME));
//		
//		return hashtableNode;
//	}
//	
//	
//	public static void add(Student student){
//		//向名字索引中添加一个记录
//		HashtableNode node1=new HashtableNode();
//		node1.setKey(student.getStu_name());
//		node1.setSize(JsonUtil.studentToString(student).length());
//		node1.setOffset(FileUtil.getFileSize(Config.STU_INFORMASTION_FILENAME));
//		name.add(node1, Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
//		//向学号索引中添加一个记录
//		HashtableNode node2=new HashtableNode();
//		node2.setKey(student.getStu_code());
//		node2.setSize(JsonUtil.studentToString(student).length());
//		node1.setOffset(FileUtil.getFileSize(Config.STU_INFORMASTION_FILENAME));
//		code.add(node2, Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME);
//		//向学生信息文件中加入一个记录
//		FileUtil.appenContent(JsonUtil.studentToString(student), Config.STU_INFORMASTION_FILENAME);
//	}
//	
//	
//	public static boolean remove(Student student){
//		//删除索引的对应记录
//		HashtableNode node1=getNode(student, student.getStu_name());
//		HashtableNode node2=getNode(student, student.getStu_code());
//		if(!name.remove(node1, Config.STU_INFORMASTION_NAMEINDEX_FILENAME)) return false;
//		if(!code.remove(node2, Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME)) return false;
//		//删除信息文件对应的记录
//		FileUtil.removeContent(Config.STU_INFORMASTION_FILENAME, node1.getOffset(),node1.getSize());
//		return true;
//	}
//	
//	
//	
//	public static void update(Student student){
//		//
//	}
	
}
