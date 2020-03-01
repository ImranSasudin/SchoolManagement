package Bean;

public class Teacher {
	Integer teacherID;
	String teacherName, password, classHandle, department;
	boolean valid;
	
	public Teacher(Integer teacherID, String teacherName, String password, String classHandle, String department, boolean valid) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.password = password;
		this.classHandle = classHandle;
		this.department = department;
		this.valid = valid;
	}
	
	public Teacher(){
		
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(Integer teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getClassHandle() {
		return classHandle;
	}

	public void setClassHandle(String classHandle) {
		this.classHandle = classHandle;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
	
}
