package Service;

import com.dao.StudentDao;
import com.model.Student;

public class LoginService {

	private StudentDao studentDao;
	
	public LoginService(){
		studentDao=new StudentDao();
	}
	
	//�ɹ��򷵻����Studnet�����������Ϣ�����򷵻�null
	public Student login(String code,String password){
		Student student=null;
		student=studentDao.getStudentByCode(code);
		if(student.getStu_password().equals(password)){
			return student;
		}
		
		return null;
	}
}
