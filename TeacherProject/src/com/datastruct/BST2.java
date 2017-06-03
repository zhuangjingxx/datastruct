package com.datastruct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.stream.FileImageInputStream;

public class BST2 implements Serializable{
	
	private TreeNode2 root;
	int size=0;

	public TreeNode2 getRoot() {
		return root;
	}

	public void setRoot(TreeNode2 root) {
		this.root = root;
	}
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public TreeNode2 add(TreeNode2 subroot,TreeNode2 node){
		if(subroot==null) {
			return node;
		}
		else if(node.getKey()<=subroot.getKey()) subroot.setLeft(add(subroot.getLeft(), node));
		else if(node.getKey()>subroot.getKey()) subroot.setRight(add(subroot.getRight(), node));
		return subroot;
	}
	
	

	
	public void getnodes(TreeNode2 subroot,int key,List<TreeNode2> node2s){
		if(subroot==null) return;
		if(key<subroot.getKey()) getnodes(subroot.getLeft(), key, node2s);
		else if(key>subroot.getKey()) getnodes(subroot.getRight(), key, node2s);
		else {
			node2s.add(subroot);
			getnodes(subroot.getLeft(), key, node2s);
		}
	}
	
	
	public TreeNode2 getNode(TreeNode2 subroot,int key,int mainkey){
		if(subroot==null) return subroot;
		else if(key<subroot.getKey()) return getNode(subroot.getLeft(), key,mainkey);
		else if(key>subroot.getKey()) return getNode(subroot.getRight(), key,mainkey);
		else if(key==subroot.getKey()&&mainkey!=subroot.getMainkey()) return getNode(subroot.getLeft(), key, mainkey);
		else  return subroot;
	}
	
	
	public TreeNode2 remove(TreeNode2 subroot,int key,int mainkey){
		if(subroot==null) return subroot;
		if(key<subroot.getKey()) subroot.setLeft(remove(subroot.getLeft(), key, mainkey));
		else if(key>subroot.getKey()) subroot.setRight(remove(subroot.getRight(), key, mainkey));
		else if(key==subroot.getKey()&&mainkey!=subroot.getMainkey()) subroot.setLeft(remove(subroot.getLeft(),key,mainkey));
		else{
			if(subroot.getLeft()==null&&subroot.getRight()==null){
				return null;
			}
			else if(subroot.getLeft()!=null&&subroot.getRight()==null){
				return subroot.getLeft();
			}
			else if(subroot.getRight()!=null&&subroot.getLeft()==null){
				return subroot.getRight();
			}else{
				if(subroot.getLeft()!=null&&subroot.getRight()!=null){
					TreeNode2 rMin=subroot.getRight();
					TreeNode2 rMinP=subroot;
					while(rMin.getLeft()!=null){
						rMinP=rMin;
						rMin=rMin.getLeft();
					}
					
					if(rMinP.getLeft()==rMin){
						rMinP.setLeft(null);
					}
					else if(rMinP.getRight()==rMin){
						rMinP.setRight(null);
					}
					rMin.setLeft(subroot.getLeft());
					rMin.setRight(subroot.getRight());
					return rMin;
					
				}
			}
		}
		return subroot;
	
	}
	
	public void getFilenames(TreeNode2 subroot,Set<String> names){
		if(subroot==null) return;
		names.add(subroot.getFilename());
		getFilenames(subroot.getLeft(), names);
		getFilenames(subroot.getRight(), names);
	}
	public void print(TreeNode2 subroot){
		if(subroot==null) return;
		System.out.println(subroot.getKey()+":"+subroot.getMainkey());
		print(subroot.getLeft());
		print(subroot.getRight());
	}
	
	public static void main(String args[]){
//		TreeNode2 node1=new TreeNode2(123, 123, "c",123);
//		TreeNode2 node2=new TreeNode2(126,126, "c",126);
//		TreeNode2 node3=new TreeNode2(125, 125, "c",2);
//		TreeNode2 node4=new TreeNode2(125, 127, "c",3);
//		TreeNode2 node5=new TreeNode2(129, 129, "c",4);
//		
//		BST2 bst=new BST2();
//		if(bst.getRoot()==null){
//			bst.setRoot(bst.add(bst.getRoot(), node1));
//		}
//		bst.add(bst.getRoot(), node2);
//		bst.add(bst.getRoot(), node3);
//		bst.add(bst.getRoot(), node4);
//		bst.add(bst.getRoot(), node5);	
//		
//		bst.setRoot(bst.remove(bst.getRoot(), 129, 129));
//		bst.print(bst.getRoot());
		

	}
	
}
