package com.datastruct;

import java.io.Serializable;

public class Node implements Serializable{

	private int key;
	private int line;
	private String filename;
	
	public Node(){}
	
	public Node(int key, int line, String filename) {
		super();
		this.key = key;
		this.line = line;
		this.filename = filename;
	}
	
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
}
