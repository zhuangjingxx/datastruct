package Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.CourseChooseDao;
import com.dao.CourseDao;
import com.dao.CourseTimeDao;
import com.model.Course;
import com.model.CourseTime;

public class CheckCourseTableService {

	private CourseChooseDao courseChooseDao;
	private CourseDao courseDao;
	private CourseTimeDao courseTimeDao;
	public CheckCourseTableService(){
		courseChooseDao=new CourseChooseDao();
		courseDao=new CourseDao();
		courseTimeDao=new CourseTimeDao();
	}
	
	public List<Course> getCourses(String stu_code,String term){
		List<Course> courses=null;
		//�ȵõ���ѧ����ѡ��ȫ���γ�
		List<Map<String, String> > tmps=courseChooseDao.getCourseMapByStuCode(stu_code);
		if(tmps!=null){
			courses=new ArrayList<>();
			//�������пγ̣�ѡ����ѧ�ڵĿγ�
			Iterator<Map<String, String>> iterator=tmps.iterator();
			while(iterator.hasNext()){
				Map<String, String> map=iterator.next();
				if(map.get("term").equals(term)){
					Course course=courseDao.getCourseByname(map.get("cou_name"));
					courses.add(course);
				}
			}
		}
		
		return courses;
	}
	
	
	public List<CourseTime> geCourseTimes(String name){
		return courseTimeDao.geCourseTime(name);
	}
	
}
