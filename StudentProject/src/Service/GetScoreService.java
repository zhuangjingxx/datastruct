package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.CourseChooseDao;
import com.dao.CourseDao;
import com.model.Course;

public class GetScoreService {
	private CourseChooseDao courseChooseDao;
	private CourseDao courseDao;
	
	
	public Map<String, String> getScores(String stu_code,String term){
		Map<String, String> map=new HashMap<>();
		List<Map<String, String> > tmps=courseChooseDao.getCourseMapByStuCode(stu_code);
		if(tmps!=null){
			
			//遍历所有课程，选出该学期的课程
			Iterator<Map<String, String>> iterator=tmps.iterator();
			while(iterator.hasNext()){
				Map<String, String> tmp=iterator.next();
				if(map.get("term").equals(term)){
					map.put(tmp.get("cou_name"), tmp.get("score"));
				}
			}
		}
		
		
		return map;
	}

}
