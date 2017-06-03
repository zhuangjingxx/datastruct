package com.datastruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HashtableManager {

	
	
	
	
	//hashtable序列化
	public static void saveHashtable(MyHashtable myHashtable,String destFile){
		File file=new File(destFile);
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(myHashtable);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//反序列化出一个hashtable
	public static MyHashtable getHashtable(String srcFile){
		MyHashtable myHashtable=null;
		File file=new File(srcFile);
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
			myHashtable=(MyHashtable) objectInputStream.readObject();
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(myHashtable==null){
			myHashtable=new MyHashtable(100);
		}
		
		return  myHashtable;
	}
	
	
	public static void main(String args[]){
		
		String filename="D:\\temp\\temp.txt";
//		MyHashtable myHashtable=new MyHashtable(100);
//		HashtableNode node1=new HashtableNode("1234",23,2,null);
//		HashtableNode node2=new HashtableNode("1235",23,2,null);
//		HashtableNode node3=new HashtableNode("1236",23,2,null);
//		myHashtable.add(node1,filename);
//		myHashtable.add(node2,filename);
//		myHashtable.add(node3,filename);
		
	
		HashtableNode node1=new HashtableNode("1234",23,2,null);
		HashtableNode node2=new HashtableNode("1235",23,2,null);
		HashtableNode node3=new HashtableNode("1236",23,2,null);
		MyHashtable myHashtable=HashtableManager.getHashtable(filename);
		System.out.println(myHashtable.remove(node1, filename));
		System.out.println(myHashtable.remove(node2, filename));
		System.out.println(myHashtable.remove(node3, filename));
	
	}
	
	
	
}
