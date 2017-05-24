package com.datastruct;

import java.io.Serializable;

import javax.xml.namespace.QName;

public class MylinkedList implements Serializable{

	HashtableNode head;
	HashtableNode tail;
	int size;
	
	public MylinkedList() {
		head=new HashtableNode("",23,2,null);
		tail=head;
		size=0;
	}
	
	public boolean append(HashtableNode node){
		if(check(node)) return false;
		tail.setNext(node);
		tail=tail.getNext();
		size++;
		System.out.println(size);
		return true;
	}
	
	public boolean remove(HashtableNode node){
		HashtableNode p=null;
		HashtableNode q=head;
		while(q!=null&&!(q.getKey().equals(node.getKey()))){
			p=q;
			q=q.getNext();
		}
		if(q==null){
			return false;
		}else{
			p.setNext(q.getNext());
			q=null;
			size--;
            return true;
		}
	}
	
	public boolean check(HashtableNode node){
		HashtableNode p=head;
		while(p!=null){
			if(p.getKey().equals(node.getKey())){
				return true;
			}
			p=p.getNext();
		}
		return false;
	}
	
	
	
	public void print(){
		HashtableNode p=head.getNext();
		while(p!=null){
			System.out.println(p.getKey());
			p=p.getNext();
		}
	}
	
	
	
	public HashtableNode getHead() {
		return head;
	}

	public void setHead(HashtableNode head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static void main(String args[]){
		HashtableNode node1=new HashtableNode("1234",23,2,null);
		HashtableNode node2=new HashtableNode("1234",23,2,null);
		HashtableNode node3=new HashtableNode("1236",23,2,null);
		MylinkedList mylinkedList=new MylinkedList();
		mylinkedList.append( node1);
		mylinkedList.append( node2);
		mylinkedList.append( node3);
		mylinkedList.print();
		System.out.println(mylinkedList.remove(node1));
		System.out.println(mylinkedList.remove(node2));
		System.out.println(mylinkedList.remove(node3));
		mylinkedList.print();
	}
	
}
