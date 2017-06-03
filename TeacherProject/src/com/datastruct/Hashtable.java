package com.datastruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hashtable implements Serializable{
	
	private int size;//�ж��ٸ�hash��
	private List< List<Node> > table;

	
	
	public Hashtable(int size, List< List<Node> > table) {
		this.size = size;
		this.table = table;
	}
	

	public Hashtable(int size) {
		this.size = size;
		table=new ArrayList<>();
		for(int i=0;i<size;i++){
			List<Node> tmp=new ArrayList<>();
			table.add(tmp);
		}
	}
	
	
	


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	int computeHashcode(String key){
		int res=0;
		for(int i=0;i<key.length();i++){
			res+=(key.charAt(i)-'0');
		}
		return res;
	}
	
	public boolean add(Node node){
		int hash=hash(node.getKey());
		List<Node> tmp=table.get(hash);
		//�жϹؼ����Ƿ��Ѿ����ڣ������򷵻�false,���벻�ɹ�
		if(tmp!=null){
			Iterator<Node> iterator=tmp.iterator();
			while(iterator.hasNext()){
				if(iterator.next().getKey()==node.getKey()) return false;
			}
		}
		table.get(hash).add(node);
		return true;
	}
	
	
	public boolean remove(int key){
		int hash=hash(key);
		List<Node> tmp=table.get(hash);
		if(tmp==null) return false;
		for(int i=0;i<tmp.size();i++){
			Node node=tmp.get(i);
			if(node.getKey()==key){	
				//ɾ��һ����¼��Ҫά��line����
				for(int j=0;j<tmp.size();j++){
					Node n=tmp.get(j);
					if(n.getLine()>node.getLine()){
						int line=n.getLine();
						line--;
						n.setLine(line);
					}
				}
				tmp.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Node getNode(int key){
		Node tmp=null;
		int hash=hash(key);
		List<Node> nodes=table.get(hash);
		if(nodes!=null){
			Iterator<Node> iterator=nodes.iterator();
			while(iterator.hasNext()){
				tmp=iterator.next();
				if(tmp.getKey()==key){
					break;
				}
			}
		}
		return tmp;
	}
	
	int hash(int key){
		int res=0;
		res=key%size;
		return res;
	}
	
	public static void main(String args[]){
		
	}
}
