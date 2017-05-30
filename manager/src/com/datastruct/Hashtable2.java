package com.datastruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hashtable2 implements Serializable{

	private List< List<Node2> > table;
	private int size;
	
	public Hashtable2(List< List<Node2> >  table, int size) {
		this.table = table;
		this.size = size;
	}
	
	public Hashtable2(int size) {
		this.size = size;
		table=new ArrayList<>();
		for(int i=0;i<size;i++){
			List<Node2> tmp=new ArrayList<>();
			table.add(tmp);
		}
	}

	
	
	
	
	public boolean add(Node2 node){
		int hash=hash(node.getKey());
		List<Node2> tmp=table.get(hash);
		table.get(hash).add(node);
		
		return true;
	}
	
	
	public boolean remove(int key,int mainkey){
		int hash=hash(key);
		List<Node2> tmp=table.get(hash);
		if(tmp==null) return false;
		for(int i=0;i<tmp.size();i++){
			Node2 node2=tmp.get(i);
			if(node2.getKey()==key&&node2.getMainkey()==mainkey){	
				tmp.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	
	public List<Node2> getNode(int key){
		List<Node2> tmp=null;
		int hash=hash(key);
		List<Node2> list=table.get(hash);
		if(list!=null){
			tmp=new ArrayList<>();
			Iterator<Node2> iterator=list.iterator();
			while(iterator.hasNext()){
				Node2 node=iterator.next();
				if(node.getKey()==key){
					tmp.add(node);
				}
			}
		}
		//存在则返回list，否则返回null.
		if(tmp.size()>0) return tmp;
		else return null;
	}

	int hash(int key){
		int res=0;
		res=key%size;
		return res;
	}
	
}
