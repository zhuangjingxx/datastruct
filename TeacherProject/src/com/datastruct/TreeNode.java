package com.datastruct;

import java.io.Serializable;

public class TreeNode implements Serializable{

	private int key;
	private int line;
	private String filename;
	private TreeNode left=null;
	private TreeNode right=null;
	
	public TreeNode(){}
	
	public TreeNode(int key, int line, String filename, TreeNode left, TreeNode right) {
		super();
		this.key = key;
		this.line = line;
		this.filename = filename;
		this.left = left;
		this.right = right;
	}
	
	

	public TreeNode(int key, int line, String filename) {
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

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	
	
}
