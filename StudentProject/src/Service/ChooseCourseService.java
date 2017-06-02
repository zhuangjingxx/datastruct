package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import com.dao.CourseChooseDao;
import com.dao.CourseDao;
import com.dao.CourseTimeDao;
import com.dao.TeacherDao;
import com.model.Course;
import com.model.CourseTime;
import com.model.Teacher;

public class ChooseCourseService {
	private CourseDao courseDao;
	private CourseTimeDao courseTimeDao;
	private CourseChooseDao courseChooseDao;
	private TeacherDao teacherDao;
	
	
	public ChooseCourseService(){
		courseDao=new CourseDao();
		courseTimeDao=new CourseTimeDao();
		courseChooseDao=new CourseChooseDao();
		teacherDao=new TeacherDao();
	}
	public Course getCourse(String name){
		return courseDao.getCourseByname(name);
	}
	
	public List<CourseTime> geCourseTimes(String name){
		return courseTimeDao.geCourseTime(name);
	}
	
	//÷ß≥÷∆•≈‰≤È’“
	public List<Course> getCourses(String str){
		
		return courseDao.getCoursesByString(str);
	}
	
	public List<Teacher> geTeachers(String couName){
		return teacherDao.geTeachersByCouName(couName);
	}
	
	
	public boolean chooseCourse(String stu_code,String courseName,String tea_code,String term){
		Map<String, String> map=new HashMap<>();
		map.put("stu_code", stu_code);
		map.put("cou_name", courseName);
		map.put("tea_code", tea_code);
		map.put("term", term);
		return courseChooseDao.add(map);
	}
	
	
	
	
}
