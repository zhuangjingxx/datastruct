package com.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.datastruct.BST2;
import com.datastruct.HashManager;
import com.datastruct.Hashtable;
import com.datastruct.Hashtable2;
import com.datastruct.Node;
import com.datastruct.Node2;
import com.model.Student;
import com.util.Config;
import com.util.FileUtil;
import com.util.HashUtil;
import com.util.JsonUtil;

public class CourseChooseDao {

	Hashtable hashtable;
	Hashtable2 hashtable2;
	Hashtable2 hashtable22;
	
	public boolean add(Map<String, String> courseChooseMap){
		hashtable=HashManager.getHashtable(Config.COUCHOOSE_INFORMASTION_STUINDEX_FILENAME);//按学号和课程号为索引
		hashtable2=HashManager.getHashtable2(Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME);//按学号索引
		hashtable22=HashManager.getHashtable2(Config.COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME);//按教师工号和课程号索引
		
		
		Node node=new Node();
		node.setKey(HashUtil.computeKey(courseChooseMap.get("stu_code")+courseChooseMap.get("cou_code")));
		node.setFilename("coursechoose"+(node.getKey()%hashtable.getSize())+".txt");
		if(!hashtable.add(node)) return false;
		HashManager.saveHashtable(Config.COUCHOOSE_INFORMASTION_STUINDEX_FILENAME, hashtable);
		Node2 node2=new Node2();
		node2.setFilename(node.getFilename());
		node2.setKey(HashUtil.computeKey(courseChooseMap.get("stu_code")));
		node2.setMainkey(node.getKey());
		hashtable2.add(node2);
		HashManager.saveHashtable2(Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME, hashtable2);
		
		Node2 node22=new Node2();
		node22.setFilename(node.getFilename());
		node22.setKey(HashUtil.computeKey(courseChooseMap.get("tea_code")+courseChooseMap.get("cou_code")));
		node22.setMainkey(node.getKey());
		hashtable22.add(node22);
		HashManager.saveHashtable2(Config.COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME, hashtable22);
		

		FileUtil.appenContent(JsonUtil.courseMapToString(courseChooseMap), node.getFilename());
		
		return true;
	}
	
	
	public boolean remove(Map<String, String> courseChooseMap){
		hashtable=HashManager.getHashtable(Config.COUCHOOSE_INFORMASTION_STUINDEX_FILENAME);//按学号和课程号为索引
		hashtable2=HashManager.getHashtable2(Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME);//按学号索引
		hashtable22=HashManager.getHashtable2(Config.COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME);//按教师工号和课程号索引
		
		int mainkey=HashUtil.computeKey(courseChooseMap.get("stu_code")+courseChooseMap.get("cou_code"));
		int key2=HashUtil.computeKey(courseChooseMap.get("stu_code"));
		int key22=HashUtil.computeKey(courseChooseMap.get("tea_code")+courseChooseMap.get("cou_code"));
		
		Node node=hashtable.getNode(mainkey);
		if(node==null) return false;
		

		
		File file=new File(node.getFilename());
		List<String> data=new ArrayList<>();
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Map<String, String> map=JsonUtil.stringToCourseMap(tmp);
				if(!(map.get("stu_code").equals(courseChooseMap.get("stu_code"))&&map.get("cou_code").equals(courseChooseMap.get("cou_code")))){
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
		//维护索引
		if(!hashtable.remove(mainkey)) return false;
		hashtable2.remove(key2, mainkey);
		hashtable22.remove(key22, mainkey);
		HashManager.saveHashtable(Config.STU_INFORMASTION_CODEINDEX__FILENAME, hashtable);	
		HashManager.saveHashtable2(Config.STU_INFORMASTION_NAMEINDEX_FILENAME, hashtable2);
		HashManager.saveHashtable2(Config.COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME, hashtable22);
		return true;
	}
	
	public boolean update(Map<String, String> courseChooseMap){
		hashtable=HashManager.getHashtable(Config.COUCHOOSE_INFORMASTION_STUINDEX_FILENAME);//按学号和课程号为索引
		
		Node node=hashtable.getNode(HashUtil.computeKey(courseChooseMap.get("stu_code")+courseChooseMap.get("cou_code")));
		if(node==null) return false;
		

		
		File file=new File(node.getFilename());
		List<String> data=new ArrayList<>();
		String tmp=null;
		
		try {
			
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			while((tmp=bufferedReader.readLine())!=null){
				Map<String, String> map=JsonUtil.stringToCourseMap(tmp);
				if(map.get("stu_code").equals(courseChooseMap.get("stu_code"))&&map.get("cou_code").equals(courseChooseMap.get("cou_code"))){
					tmp=JsonUtil.courseMapToString(courseChooseMap);
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
	
	public List< Map<String, String> >getCourseMapByStuCode(String code){
		hashtable2=HashManager.getHashtable2(Config.COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME);//按学号索引
		
		
		
		List< Map<String, String>> list=null;
		
		
		int key=HashUtil.computeKey(code);
		
		List<Node2> node2s=hashtable2.getNode(key);
		if(node2s==null) return list;
		list=new ArrayList<>();
		Iterator<Node2> iterator=node2s.iterator();
		Set<String> filenames=new HashSet<>();
		while(iterator.hasNext()){
			filenames.add(iterator.next().getFilename());
		}

		
		Iterator<String> iterator2=filenames.iterator();
		while(iterator2.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator2.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					Map<String, String> map=JsonUtil.stringToCourseMap(tmp);
					if(map.get("stu_code").equals(code)) list.add(map);
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
		
		return list;
	}
	
	public List< Map<String, String> >getCourseMapByTea(String tea_code,String cou_code){
		hashtable22=HashManager.getHashtable2(Config.COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME);//按教师工号和课程号索引
		
		
		
		List< Map<String, String>> list=null;
		
		
		int key=HashUtil.computeKey(tea_code+cou_code);
		
		List<Node2> node2s=hashtable2.getNode(key);
		if(node2s==null) return list;
		list=new ArrayList<>();
		Iterator<Node2> iterator=node2s.iterator();
		Set<String> filenames=new HashSet<>();
		while(iterator.hasNext()){
			filenames.add(iterator.next().getFilename());
		}

		
		Iterator<String> iterator2=filenames.iterator();
		while(iterator2.hasNext()){
			try {
				BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(iterator2.next())));
				String tmp=null;
				while((tmp=bufferedReader.readLine())!=null){
					Map<String, String> map=JsonUtil.stringToCourseMap(tmp);
					if(map.get("tea_code").equals(tea_code)&&map.get("cou_code").equals(cou_code)) list.add(map);
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
		
		return list;
	}
	
	
	public static void main(String args[]){
		Map<String, String> coureschoose=new HashMap<>();
		coureschoose.put("stu_code", "12345588");
		coureschoose.put("tea_code", "12356987");
		coureschoose.put("cou_code", "1122365888");
		coureschoose.put("score", "88");
		CourseChooseDao courseChooseDao=new CourseChooseDao();
		System.out.println(courseChooseDao.remove(coureschoose));
		
		List<Map<String, String>> tmps=courseChooseDao.getCourseMapByStuCode(coureschoose.get("stu_code"));
		if(tmps!=null){
			Iterator<Map<String, String>> iterator=tmps.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next().get("score"));
			}
		}
	}
	
}
