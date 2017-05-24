package com.datastruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

public class MyHashtable implements Serializable{

	private int size;
	private MylinkedList [] table;
	
	public MyHashtable(int size,MylinkedList [] table){
		this.size=size;
		this.table=table;
	}
	
	public MyHashtable(int size){
		this.size=size;
		table=new MylinkedList[size];
		for(int i=0;i<size;i++){
		   table[i]=new MylinkedList();
		}
		
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public MylinkedList[] getTable() {
		return table;
	}
	public void setTable(MylinkedList[] table) {
		this.table = table;
	}
	
	
	//增加一个索引节点同时写进索引文件从而使索引同步
	public void add(HashtableNode node,String destFile){
		int hashcode=hash(computeHashcode(node.getKey()));
		table[hashcode].append(node);
		HashtableManager.saveHashtable(this, destFile);
	}
	
	
	//删除一个索引节点同时写进索引文件从而使索引同步
	public boolean remove(HashtableNode node,String destFile){
		
		int hashcode=hash(computeHashcode(node.getKey()));
		if(table[hashcode].remove(node)) {
			HashtableManager.saveHashtable(this, destFile);
			return true;
		}
		else return false;
	}
	
	
	
	int computeHashcode(String key){
		int res=0;
		for(int i=0;i<key.length();i++){
			res+=(key.charAt(i)-'0');
		}
		return res;
	}
	
	
	int hash(int key){
		int res=0;
		res=key%size;
		return res;
	}
	
//	public static void main(String args[]){
//		
//		MyHashtable myHashtable=new MyHashtable(100);
//		HashtableNode node1=new HashtableNode("1234",23,2,null);
//		HashtableNode node2=new HashtableNode("1235",23,2,null);
//		HashtableNode node3=new HashtableNode("1236",23,2,null);
//		myHashtable.add(node1);
//		myHashtable.add(node2);
//		myHashtable.add(node3);
//		System.out.println(myHashtable.remove(node1));
//		
//	
//	}
	
}







