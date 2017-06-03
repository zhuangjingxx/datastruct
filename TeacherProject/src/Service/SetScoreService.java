package Service;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.events.EndDocument;

import com.dao.CourseChooseDao;
import com.dao.StudentDao;
import com.model.Student;

public class SetScoreService {

	private CourseChooseDao courseChooseDao;
	private StudentDao studentDao;
	public SetScoreService(){
		courseChooseDao=new CourseChooseDao();
		studentDao=new StudentDao();
	}
	
	public List<Student> getStudent(String tea_code,String cou_name,String term){
		List<Student> students=null;
		List<Map<String, String>>  maps=courseChooseDao.getCourseMapByTea(tea_code, cou_name);
		if(maps!=null){
			students=new ArrayList<>();
			Iterator<Map<String, String>> iterator=maps.iterator();
			while(iterator.hasNext()){
				Map<String ,String > tmp=iterator.next();
				Student student=studentDao.getStudentByCode(tmp.get("stu_code"));
				if(student!=null) students.add(student);
			}
		}
		
		return students;
	}
	
	
	public void setScores(String tea_code,Map<String, String> scores,String term){
			
			if(scores!=null){
				
				for(Entry<String, String> entry: scores.entrySet()){
					Map<String, String> tmp=new HashMap<>();
					tmp.put("stu_code", entry.getKey());
					tmp.put("score", entry.getValue());
					tmp.put("tea_code", tea_code);
					tmp.put("term", term);
					courseChooseDao.update(tmp);
				}
			}
	}
}
