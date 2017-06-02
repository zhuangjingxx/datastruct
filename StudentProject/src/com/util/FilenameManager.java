package com.util;

public class FilenameManager {

	public static final int HASHTABLE_SIZE=300;
	public static final int BST_SIZE=50;
	
	public static String getHashtableFilename(int key){
		return (key%HASHTABLE_SIZE)+".txt";
	}
	
	public static String getBSTFilename(int key){
		return (key%BST_SIZE)+".txt";
	}
}
