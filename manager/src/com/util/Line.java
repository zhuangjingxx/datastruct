package com.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Line implements Serializable{

	private Map<String, Integer> map;
	
	public Line() {
		map=new HashMap<>();
	}
	
	public int getLine(String filename){
		if(map.containsKey(filename)){
			return map.get(filename);
		}
		
		return 0;
	}
	
	public void add(String filename,int line){
		map.put(filename, line);
	}
	
	public static void main(String args[]){
		File file=new File("wokao.txt");
		if(!file.exists()){
			System.out.println("file no exists");
		}
	}
}
