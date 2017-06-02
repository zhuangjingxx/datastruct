package Service;

import com.dao.StudentDao;
import com.model.Student;

public class LoginService {

	private StudentDao studentDao;
	
	public LoginService(){
		studentDao=new StudentDao();
	}
	
	//成功则返回这个Studnet对象的所有信息，否则返回null
	public Student login(String code,String password){
		Student student=null;
		student=studentDao.getStudentByCode(code);
		if(student.getStu_password().equals(password)){
			return student;
		}
		
		return null;
	}
}
