package com.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.model.Grade;
import com.model.Major;
import com.util.Config;
import com.util.JsonUtil;

public class MajorDao {
	public boolean add(Major major){
		BufferedReader bufferedReader=null;
		
		try {
			File file=new File(Config.MAJ_INFORMASTION_FILENAME);
			if(!file.exists()){
				BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
				bufferedWriter.append(JsonUtil.majorToString(major));
				bufferedWriter.newLine();
				bufferedWriter.close();
				return true;
			}
			bufferedReader = new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Major major2=JsonUtil.stringToMajor(tmp);
				if(major.getMaj_name().equals(major2.getMaj_name())&&major.getMaj_collegeName().equals(major2.getMaj_collegeName())){
					return false;
				}
			}
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
			bufferedWriter.append(JsonUtil.majorToString(major));
			bufferedWriter.newLine();
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
	
	
	public boolean remove(Major major){
		List<String> data=new ArrayList<>();
		boolean flag=false;
		try {
			File file=new File(Config.MAJ_INFORMASTION_FILENAME);
			if(!file.exists()){
				return false;
			}
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Major major2=JsonUtil.stringToMajor(tmp);
				if(major.getMaj_name().equals(major2.getMaj_name())&&major.getMaj_collegeName().equals(major2.getMaj_collegeName())) {
					flag=true;
					continue;
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
		
		return flag;
	}
	
}
