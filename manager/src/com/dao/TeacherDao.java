package com.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.datastruct.BST;
import com.datastruct.BST2;
import com.datastruct.HashManager;
import com.datastruct.TreeNode;
import com.datastruct.TreeNode2;
import com.model.Student;
import com.model.Teacher;
import com.util.Config;
import com.util.FileUtil;
import com.util.HashUtil;
import com.util.JsonUtil;

public class TeacherDao {
	private BST bst;
	private BST2 bst2;
	
	public TeacherDao(){}
	
	public boolean add(Teacher teacher){
		bst=HashManager.getBST(Config.TEA_INFORMASTION_CODEINDEX_FILENAME);
		bst2=HashManager.getBST2(Config.TEA_INFORMASTION_NAMEINDEX_FILENAME);
		
		TreeNode treeNode=new TreeNode();
		TreeNode2 treeNode2=new TreeNode2();
		
		treeNode.setKey(HashUtil.computeKey(teacher.getTea_code()));
		treeNode.setFilename("tea_"+treeNode.getKey()%50+".txt");
		if(bst.getNode(bst.getRoot(), treeNode.getKey())!=null) return false;
		bst.setRoot(bst.add(bst.getRoot(), treeNode));
		HashManager.saveBST(Config.TEA_INFORMASTION_CODEINDEX_FILENAME, bst);
		
		treeNode2.setKey(HashUtil.computeKey(teacher.getTea_name()));
		treeNode2.setFilename(treeNode.getFilename());
		bst2.setRoot(bst2.add(bst2.getRoot(), treeNode2));
		HashManager.saveBST2(Config.TEA_INFORMASTION_NAMEINDEX_FILENAME, bst2);
		
		FileUtil.append(JsonUtil.teacherToString(teacher), treeNode.getFilename());
		
		return true;
		
	}
	
	
	public boolean remove(Teacher teacher){
		bst=HashManager.getBST(Config.TEA_INFORMASTION_CODEINDEX_FILENAME);
		bst2=HashManager.getBST2(Config.TEA_INFORMASTION_NAMEINDEX_FILENAME);
		
		int mainkey=HashUtil.computeKey(teacher.getTea_code());
		int key=HashUtil.computeKey(teacher.getTea_name());
		
		TreeNode treeNode=bst.getNode(bst.getRoot(), mainkey);
		
		if(treeNode==null) return false;
		
		File file=new File(treeNode.getFilename());
		List<String> data=new ArrayList<>();
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Teacher teacher2=JsonUtil.stringToTeacher(tmp);
				if(!teacher2.getTea_code().equals(teacher.getTea_code())){
					data.add(tmp);
				}
			}
			bufferedReader.close();
			BufferedWriter bufferedWriter=new BufferedWriter( new FileWriter(file));
			Iterator<String> iterator=data.iterator();
			while(iterator.hasNext()){
				bufferedWriter.write(iterator.next());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Î¬»¤Ë÷Òý
		bst.setRoot(bst.remove(bst.getRoot(), mainkey));
		bst2.setRoot(bst2.remove(bst2.getRoot(), key, mainkey));
		HashManager.saveBST(Config.TEA_INFORMASTION_CODEINDEX_FILENAME, bst);
		HashManager.saveBST2(Config.TEA_INFORMASTION_NAMEINDEX_FILENAME, bst2);
		
		return true;
	}
	
	public boolean update(Teacher teacher){
		bst=HashManager.getBST(Config.TEA_INFORMASTION_CODEINDEX_FILENAME);
		bst2=HashManager.getBST2(Config.TEA_INFORMASTION_NAMEINDEX_FILENAME);
		
		int key=HashUtil.computeKey(teacher.getTea_code());
		
		TreeNode treeNode=bst.getNode(bst.getRoot(), key);
		if(treeNode==null) return false;
		
		File file=new File(treeNode.getFilename());
		List<String> data=new ArrayList<>();
		String tmp=null;
		
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				Teacher teacher2=JsonUtil.stringToTeacher(tmp);
				
				if(teacher2.getTea_code().equals(teacher.getTea_code())){
					tmp=JsonUtil.teacherToString(teacher);
				}
				data.add(tmp);
			}
			bufferedReader.close();
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
			Iterator<String> iterator=data.iterator();
			while(iterator.hasNext()){
				bufferedWriter.write(iterator.next());
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	public Teacher getTeacherByCode(String code){
		bst=HashManager.getBST(Config.TEA_INFORMASTION_CODEINDEX_FILENAME);
		Teacher teacher=null;
		
		int key=HashUtil.computeKey(code);
		TreeNode treeNode=bst.getNode(bst.getRoot(), key);
		if(treeNode==null) return teacher;
		
		String tmp=null;
		File file=new File(treeNode.getFilename());
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				teacher=JsonUtil.stringToTeacher(tmp);
				if(teacher.getTea_code().equals(key)){
					break;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return teacher;
	}
	
	public List<Teacher> geTeachersByName(String name){
		bst2=HashManager.getBST2(Config.TEA_INFORMASTION_NAMEINDEX_FILENAME);
		List<Teacher> res=null;
		
		int key=HashUtil.computeKey(name);
		
		List<TreeNode2> node2s=new ArrayList<>();
		bst2.getnodes(bst2.getRoot(), key, node2s);
		if(node2s.size()==0) return res;
		
		res=new ArrayList<>();
		Set<String> filenames=new HashSet<>();
		Iterator<TreeNode2> iterator=node2s.iterator();
		while(iterator.hasNext()){
			filenames.add(iterator.next().getFilename());
		}
		
		Iterator<String> iterator2=filenames.iterator();
		while(iterator2.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator2.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					Teacher teacher=JsonUtil.stringToTeacher(tmp);
					if(teacher.getTea_name().equals(name)) res.add(teacher);
				}
				bufferedReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
		
	}
	
	public static void main(String args[]){
		Teacher teacher=new Teacher();
		teacher.setTea_code("23948475");
		teacher.setTea_password("dfkdkfjkdjfkj666");
		teacher.setTea_name("dfkdklfjk");
		TeacherDao teacherDao=new TeacherDao();
		Teacher teacher2=teacherDao.getTeacherByCode(teacher.getTea_code());
		List<Teacher> list=teacherDao.geTeachersByName(teacher.getTea_name());
		if(list!=null){
			Iterator<Teacher> iterator=list.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next().getTea_code());
			}
		}
	}

}
