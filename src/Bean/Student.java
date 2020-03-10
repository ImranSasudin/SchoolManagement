package Bean;

public class Student {
	Integer age;
	String id, ic, name, address, className, guardianName, guardianJob, cgpa;
	boolean valid;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Integer age, String id, String ic, String name, String address, String className,
			String guardianName, String guardianJob, boolean valid) {
		super();
		this.age = age;
		this.id = id;
		this.ic = ic;
		this.name = name;
		this.address = address;
		this.className = className;
		this.guardianName = guardianName;
		this.guardianJob = guardianJob;
		this.valid = valid;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianJob() {
		return guardianJob;
	}

	public void setGuardianJob(String guardianJob) {
		this.guardianJob = guardianJob;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
	
	
	
}
