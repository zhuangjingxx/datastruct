package com.datastruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.util.Config;
import com.util.FilenameManager;

public class HashManager {
	
	public static Hashtable getHashtable(String filename){
		Hashtable hashtable=null;
		File file=new File(filename);
		if(!file.exists()){
			hashtable=new Hashtable(FilenameManager.HASHTABLE_SIZE);
			return hashtable;
		}
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(new File(filename)));
			hashtable=(Hashtable) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashtable;
		
	}
	
	
	public static Hashtable2 getHashtable2(String filename){
		Hashtable2 hashtable=null;
		File file=new File(filename);
		if(!file.exists()){
			hashtable=new Hashtable2(FilenameManager.HASHTABLE_SIZE);
			return hashtable;
		}
		try {
			
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(new File(filename)));
			hashtable=(Hashtable2) objectInputStream.readObject();
			objectInputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashtable;
		
	}
	
	
	public static BST getBST(String filename){
		BST bst=null;
		File file=new File(filename);
		if(!file.exists()){
			bst=new BST();
			return bst;
		}
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(new File(filename)));
			bst=(BST) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bst;
	}
	
	
	public static BST2 getBST2(String filename){
		BST2 bst=null;
		File file=new File(filename);
		if(!file.exists()){
			bst=new BST2();
			return bst;
		}
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(new File(filename)));
			bst=(BST2) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bst;
	}
	
	
	public static void saveHashtable(String filename,Hashtable hashtable){
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(filename)));
			objectOutputStream.writeObject(hashtable);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void saveHashtable2(String filename,Hashtable2 hashtable){
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(filename)));
			objectOutputStream.writeObject(hashtable);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void saveBST(String filename,BST bst){
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(filename)));
			objectOutputStream.writeObject(bst);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void saveBST2(String filename,BST2 bst){
		try {
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(filename)));
			objectOutputStream.writeObject(bst);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
	
		
		
	}
	
	
}
