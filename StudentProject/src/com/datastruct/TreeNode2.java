package com.datastruct;

import java.io.Serializable;

public class TreeNode2 implements Serializable{

	private int key;
	private int mainkey;
	private String filename;
	private int line;
	private TreeNode2 left=null;
	private TreeNode2 right=null;
	
	
	public TreeNode2(){}
	
	public TreeNode2(int key, int mainkey, String filename, int line) {
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
	public TreeNode2 getLeft() {
		return left;
	}
	public void setLeft(TreeNode2 left) {
		this.left = left;
	}
	public TreeNode2 getRight() {
		return right;
	}
	public void setRight(TreeNode2 right) {
		this.right = right;
	}
	
	
	
}
