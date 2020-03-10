package Bean;

import java.util.Date;

public class Examination {
	String examinationID, examinationName;
	Date examinationDate;

	public Examination(String examinationID, String examinationName, Date examinationDate) {
		super();
		this.examinationID = examinationID;
		this.examinationName = examinationName;
		this.examinationDate = examinationDate;
	}

	public Examination() {
		super();
	}

	public String getExaminationID() {
		return examinationID;
	}

	public void setExaminationID(String examinationID) {
		this.examinationID = examinationID;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}
	
	

}
