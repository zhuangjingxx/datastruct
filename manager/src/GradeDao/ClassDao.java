package GradeDao;

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

import com.model.College;
import com.model.Grade;
import com.util.Config;
import com.util.JsonUtil;

public class ClassDao {
	public boolean add(Grade grade){
		BufferedReader bufferedReader;
		
		try {
			File file=new File(Config.CLS_INFORMASTION_FILENAME);
			if(!file.exists()){
				BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
				bufferedWriter.append(JsonUtil.gradeToString(grade));
				bufferedWriter.newLine();
				bufferedWriter.close();
				return true;
			}
			bufferedReader = new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Grade grade2=JsonUtil.stringToGrade(tmp);
				if(grade.getGra_name().equals(grade2.getGra_name())&&grade.getGra_majorName().equals(grade2.getGra_majorName())){
					return false;
				}
			}
			
			BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true));
			bufferedWriter.append(JsonUtil.gradeToString(grade));
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
	
	
	
	public boolean remove(Grade grade){
		List<String> data=new ArrayList<>();
		boolean flag=false;
		try {
			File file=new File(Config.CLS_INFORMASTION_FILENAME);
			if(!file.exists()){
				return false;
			}
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Grade grade2=JsonUtil.stringToGrade(tmp);
				if(grade.getGra_name().equals(grade2.getGra_name())&&grade.getGra_majorName().equals(grade2.getGra_majorName())) {
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
	
	public boolean update(Grade grade){
		List<String> data=new ArrayList<>();
		boolean flag=false;
		try {
			File file=new File(Config.CLS_INFORMASTION_FILENAME);
			if(!file.exists()) return false;
			BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Grade grade2=JsonUtil.stringToGrade(tmp);
				if(grade.getGra_name().equals(grade2.getGra_name())&&grade.getGra_majorName().equals(grade2.getGra_majorName())) {
					flag=true;
					tmp=JsonUtil.gradeToString(grade);
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
	
	public Grade getGrade(String majorName,String gradeName){
		Grade grade=null;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(Config.CLS_INFORMASTION_FILENAME)));
			String tmp=null;
			while((tmp=bufferedReader.readLine())!=null){
				Grade grade2=JsonUtil.stringToGrade(tmp);
				if(grade2.getGra_name().equals(gradeName)&&grade2.getGra_majorName().equals(majorName)){
					grade=grade2;
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
		return grade;
	}
	
	public static void main(String args[]){
		Grade grade=new Grade();
		grade.setGra_majorName("8j");
		grade.setGra_name("abd");
		grade.setGra_num(23);
		ClassDao classDao=new ClassDao();
		System.out.println(classDao.add(grade));
		
	}
}
