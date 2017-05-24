package com.datastruct;

import java.io.Serializable;

public class HashtableNode implements Serializable{

	
	private String key;
	private long size;
	private long offset;
	private HashtableNode next=null;
	
	public HashtableNode(){}
	
	public HashtableNode(String key,long size,long offset,HashtableNode next){
		this.key=key;
		this.size=size;
		this.offset=offset;
		this.next=next;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}

	public HashtableNode getNext() {
		return next;
	}

	public void setNext(HashtableNode next) {
		this.next = next;
	}
	
	
	
	
}
