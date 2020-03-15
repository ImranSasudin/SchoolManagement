package Bean;

public class StudentGrade {
	String studentID, subjectID, examinationID, gradeID;

	public StudentGrade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentGrade(String studentID, String subjectID, String examinationID, String gradeID) {
		super();
		this.studentID = studentID;
		this.subjectID = subjectID;
		this.examinationID = examinationID;
		this.gradeID = gradeID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getExaminationID() {
		return examinationID;
	}

	public void setExaminationID(String examinationID) {
		this.examinationID = examinationID;
	}

	public String getGradeID() {
		return gradeID;
	}

	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	
	
}
