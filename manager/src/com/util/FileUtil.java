package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.Timestamp;
import java.security.cert.CertPathValidatorException.Reason;

import org.omg.PortableInterceptor.ServerRequestInfo;

import com.datastruct.HashtableNode;

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
		try {
			randomAccessFile=new RandomAccessFile(new File(filename), "rw");
			File tmp=new File("temp.txt");
			
			FileOutputStream fileOutputStream=new FileOutputStream(tmp);
			FileInputStream fileInputStream=new FileInputStream(tmp);
			randomAccessFile.seek(start+s);
			
			byte buff[]=new byte[1024];
			int hasread=0;
			while((hasread=randomAccessFile.read(buff))>0){
				fileOutputStream.write(buff, 0, hasread);
				size+=hasread;
			}
			randomAccessFile.seek(start);
			randomAccessFile.writeBytes(content);
			while((hasread=fileInputStream.read(buff))>0){
				randomAccessFile.write(buff, 0, hasread);
			}
			randomAccessFile.setLength(size);
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
}
