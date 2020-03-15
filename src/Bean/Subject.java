package Bean;

public class Subject {
	
	String subjectID, subjectName, category;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(String subjectID, String subjectName, String category) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.category = category;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
