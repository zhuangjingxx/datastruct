package Service;

import com.dao.TeacherDao;
import com.model.Student;
import com.model.Teacher;

public class LoginService {
	
	private TeacherDao teacherDao;
	
	public LoginService(){
		teacherDao=new TeacherDao();
	}

	public Teacher login(String code,String password){
		Teacher teacher=null;
		teacher=teacherDao.getTeacherByCode(code);
		if(teacher.getTea_password().equals(password)){
			return teacher;
		}
	
		return null;
	}
}
