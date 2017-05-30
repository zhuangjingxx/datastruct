package com.datastruct;

import java.awt.event.MouseWheelEvent;
import java.io.Serializable;
import java.util.Set;

import javax.swing.tree.TreeModel;

public class BST implements Serializable{
	private TreeNode root;
	
	
	public BST(){
		
	}


	public TreeNode getRoot() {
		return root;
	}


	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	
	public TreeNode add(TreeNode subroot,TreeNode node){
		if(subroot==null) {
			return node;
		}
		else if(node.getKey()<subroot.getKey()) subroot.setLeft(add(subroot.getLeft(), node));
		else if(node.getKey()>subroot.getKey()) subroot.setRight(add(subroot.getRight(), node));
		return subroot;
	}
	
	
	public TreeNode getNode(TreeNode subroot,int key){
		if(subroot==null) return subroot;
		if(key<subroot.getKey()) return getNode(subroot.getLeft(), key);
		else if(key>subroot.getKey()) return getNode(subroot.getRight(), key);
		else return subroot;
	}
	
	
	public TreeNode remove(TreeNode subroot,int key){
		if(subroot==null) return subroot;
		if(key<subroot.getKey()) subroot.setLeft(remove(subroot.getLeft(), key));
		else if(key>subroot.getKey()) subroot.setRight(remove(subroot.getRight(), key));
		else{
			if(subroot.getLeft()==null&&subroot.getRight()==null){
				return null;
			}
			else if(subroot.getLeft()!=null&&subroot.getRight()==null){
				return subroot.getLeft();
			}
			else if(subroot.getRight()!=null&&subroot.getLeft()==null){
				return subroot.getRight();
			}
			else{
				if(subroot.getLeft()!=null&&subroot.getRight()!=null){
					TreeNode rMin=subroot.getRight();
					TreeNode rMinP=subroot;
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
	
	
	public void getFilenames(TreeNode subroot,Set<String> names){
		if(subroot==null) return;
		names.add(subroot.getFilename());
		getFilenames(subroot.getLeft(), names);
		getFilenames(subroot.getRight(), names);
	}
	
	public void print(TreeNode subroot){
		if(subroot==null) return;
		System.out.println(subroot.getKey());
		print(subroot.getLeft());
		print(subroot.getRight());
	}
	
	
	public static void main(String args[]){
		TreeNode node1=new TreeNode(123, 2, "c");
		TreeNode node2=new TreeNode(126, 2, "c");
		TreeNode node3=new TreeNode(122, 2, "c");
		TreeNode node4=new TreeNode(125, 2, "c");
		TreeNode node5=new TreeNode(129, 2, "c");
		
		BST bst=new BST();
		if(bst.getRoot()==null){
			bst.setRoot(bst.add(bst.getRoot(), node1));
		}
		System.out.println(bst.getRoot());
		bst.add(bst.getRoot(), node2);
		bst.add(bst.getRoot(), node3);
		bst.add(bst.getRoot(), node4);
		bst.add(bst.getRoot(), node5);		
		bst.setRoot(bst.remove(bst.getRoot(), 122));
		bst.print(bst.getRoot());

//		System.out.println(bst.getRoot());
		
		
	
		
	}
}
