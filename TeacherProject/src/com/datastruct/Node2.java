package com.datastruct;

import java.io.Serializable;

public class Node2 implements Serializable{

	private int key;
	private int mainkey;
	private String filename;
	private int line;
	
	public Node2(){}
	
	public Node2(int key, int mainkey, String filename, int line) {
		super();
		this.key = key;
		this.mainkey = mainkey;
		this.filename = filename;
		this.line = line;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getMainkey() {
		return mainkey;
	}

	public void setMainkey(int mainkey) {
		this.mainkey = mainkey;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	
	
	
	
}
