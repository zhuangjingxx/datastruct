package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.Timestamp;
import java.security.cert.CertPathValidatorException.Reason;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.omg.PortableInterceptor.ServerRequestInfo;

import com.datastruct.HashtableManager;
import com.datastruct.HashtableNode;
import com.datastruct.MyHashtable;
import com.model.Student;

public class FileUtil {

	public static void appenContent(String content,String filename){
		RandomAccessFile randomAccessFile=null;
		try {
			randomAccessFile=new RandomAccessFile(new File(filename), "rw");
			randomAccessFile.seek(randomAccessFile.length());
			randomAccessFile.writeBytes(content);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static void updateContent(String content,String filename,long start,long s){
		RandomAccessFile randomAccessFile=null;
		long size=start+content.length();
		//获取学生信息文件的两个索引
		MyHashtable name=HashtableManager.getHashtable(Config.STU_INFORMASTION_NAMEINDEX_FILENAME);
		MyHashtable code=HashtableManager.getHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME);
		try {
			randomAccessFile=new RandomAccessFile(new File(filename), "rw");
			File tmp=new File("temp.txt");
			FileOutputStream fileOutputStream=new FileOutputStream(tmp);
			FileInputStream fileInputStream=new FileInputStream(tmp);
			randomAccessFile.seek(start+s);
			byte buff[]=new byte[1024];
			int hasread=0;
			//将后面的内容先转移到临时文件中
			while((hasread=randomAccessFile.read(buff))>0){
				fileOutputStream.write(buff, 0, hasread);
				size+=hasread;
			}
			//写入要更新的内容
			randomAccessFile.seek(start);
			randomAccessFile.writeBytes(content);
			//将临时文件的内容转移到信息文件中
			while((hasread=fileInputStream.read(buff))>0){
				randomAccessFile.write(buff, 0, hasread);
			}
			randomAccessFile.setLength(size);
			//遍历整个哈希表，将offset大于start的节点更新offset
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public String readContent(String filename,long start,long size){
		
		long sum=size;
		StringBuffer temp=new StringBuffer();
		RandomAccessFile randomAccessFile=null;
		try {
			randomAccessFile =new RandomAccessFile(new File(filename), "rw");
			randomAccessFile.seek(start);
			int hasread=0;
			byte buff[]=new byte[1024];
			while((hasread=randomAccessFile.read(buff))>0){
				if(sum>0){
					if(sum>hasread){
						temp.append(new String(buff,0,hasread));
						sum-=hasread;
					}else{
						temp.append(new String(buff,0,(int)(sum)));
						break;
					}
				}else{
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				randomAccessFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return temp.toString();
	}
	
	public static void removeContent(String filename,long start,long size){
		updateContent("", filename, start, size);
	}
	
	public static long getFileSize(String filename){
		RandomAccessFile randomAccessFile;
		long res=0;
		try {
			randomAccessFile = new RandomAccessFile(new File(filename), "rw");
			res=randomAccessFile.length();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	public static void main(String args[]){
		String filename="D:\\temp\\temp.txt";
		String s1="hahada";
		String s2="nima";
		String s3="jdjf";
		
		
		removeContent(filename, 0, s3.length());
		
		System.out.println(s1.length());
	}
	
	public static void append(String content,String filename){
		try {
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File(filename),true));
			bufferedWriter.append(content);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void remove(String key,String filename){
		File file=new File(filename);
		List<String> data=new ArrayList<>();
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Student student2=JsonUtil.StringToStudent(tmp);
				if(!student2.getStu_code().equals(key)){
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
	}
	
	public static void update(String filename,String key,String content){
		File file=new File(filename);
		List<String> data=new ArrayList<>();
		String tmp=null;
		
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				Student student2=JsonUtil.StringToStudent(tmp);
				if(student2.getStu_code().equals(key)){
					tmp=content;
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
	}
	
	
	public static String readLine(String key,String filename){
		Student student=null;
		String tmp=null;
		File file=new File(filename);
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
		
		return tmp;
	}
	
	
	public static List<String> readLines(Set<String> filenames,String key){
		List<String> list=new ArrayList<>();
		Iterator<String> iterator2=filenames.iterator();
		while(iterator2.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator2.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					Student student=JsonUtil.StringToStudent(tmp);
					if(student.getStu_name().equals(key)) list.add(tmp);
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
}
