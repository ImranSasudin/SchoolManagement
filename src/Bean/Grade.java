package Bean;

public class Grade extends Subject {
	String gradeID, gradeName, gradeMark, category;

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grade(String gradeID, String gradeName, String gradeMark, String category) {
		super();
		this.gradeID = gradeID;
		this.gradeName = gradeName;
		this.gradeMark = gradeMark;
		this.category = category;
	}

	public String getGradeID() {
		return gradeID;
	}

	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeMark() {
		return gradeMark;
	}

	public void setGradeMark(String gradeMark) {
		this.gradeMark = gradeMark;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
