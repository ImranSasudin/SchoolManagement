package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.Student;
import Connection.ConnectionManager;

public class StudentDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;

	static Integer age;
	static String id, ic, name, address, className, guardianName, guardianJob;
	boolean valid;

	// Student Login
	public static Student login(Student bean) throws NoSuchAlgorithmException {

		id = bean.getId();
		ic = bean.getIc();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement("select * from student where student_id = ? AND student_ic = ?");
			ps.setString(1, id);
			ps.setString(2, ic);

			rs = ps.executeQuery();

			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				bean.setId(rs.getString("student_id"));
				bean.setName(rs.getString("student_name"));
				bean.setValid(true);
			}

			// if user does not exist set the isValid variable to false
			else if (!more) {
				System.out.println("Sorry, you are not a registered teacher! Please sign up first");
				bean.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {
				}
				stat = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;
	}

	// Register New Student
	public static void add(Student bean) throws NoSuchAlgorithmException {
		age = bean.getAge();
		// id = bean.getId();
		ic = bean.getIc();
		name = bean.getName();
		className = bean.getClassName();
		guardianName = bean.getGuardianName();
		guardianJob = bean.getGuardianJob();
		address = bean.getAddress();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement(
					"insert into student (student_ic, student_name, student_age, student_address, class_name, guardian_name, guardian_job)"
							+ " values(?,?,?,?,?,?,?)");
			ps.setString(1, ic);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, address);
			ps.setString(5, className);
			ps.setString(6, guardianName);
			ps.setString(7, guardianJob);
			ps.executeUpdate();

		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
				if (currentCon != null) {
					try {
						currentCon.close();
					} catch (Exception e_) {
						currentCon = null;
					}
				}
			}
		}
	}

	// get user use to check if user is exist or not
	public static Student getStudent(Student bean) {

		ic = bean.getIc();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from student where student_ic = ? ");
			ps.setString(1, ic);

			rs = ps.executeQuery();

			boolean more = rs.next();

			// if student exists set the isValid variable to true
			if (more) {

				bean.setValid(true);
			}

			else if (!more) {
				System.out.println("IC already Exist");
				bean.setValid(false);
			}

		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {
				}
				stat = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;
	}

	// Get student by student id
	public static Student getUserByID(String id) {

		Student student = new Student();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from student where student_id =?");

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				student.setId(rs.getString("student_id"));
				student.setIc(rs.getString("student_ic"));
				student.setName(rs.getString("student_name"));
				student.setAge(rs.getInt("student_age"));
				student.setAddress(rs.getString("student_address"));
				student.setClassName(rs.getString("class_name"));
				student.setGuardianName(rs.getString("guardian_name"));
				student.setGuardianJob(rs.getString("guardian_job"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	// Update Student
	public static void update(Student bean) throws NoSuchAlgorithmException {
		age = bean.getAge();
		id = bean.getId();
		// ic = bean.getIc();
		name = bean.getName();
		className = bean.getClassName();
		guardianName = bean.getGuardianName();
		guardianJob = bean.getGuardianJob();
		address = bean.getAddress();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement(
					"UPDATE student set student_name = ?," + "student_age = ?," + "student_address = ?,"
							+ "class_name = ?," + "guardian_name = ?," + "guardian_job = ?" + " where student_id = ? ");

			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, address);
			ps.setString(4, className);
			ps.setString(5, guardianName);
			ps.setString(6, guardianJob);
			ps.setString(7, id);

			ps.executeUpdate();

		}

		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
				if (currentCon != null) {
					try {
						currentCon.close();
					} catch (Exception e_) {
						currentCon = null;
					}
				}
			}
		}
	}

	// Get all students
	public static List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("select * from student ");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("student_id"));
				student.setIc(rs.getString("student_ic"));
				student.setName(rs.getString("student_name"));
				student.setAge(rs.getInt("student_age"));
				student.setAddress(rs.getString("student_address"));
				student.setClassName(rs.getString("class_name"));
				student.setGuardianName(rs.getString("guardian_name"));
				student.setGuardianJob(rs.getString("guardian_job"));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

//	// Get all students grade 1 (PT3)
//	public static List<Student> getAllStudentGrade1() {
//		List<Student> students = new ArrayList<Student>();
//		try {
//			currentCon = ConnectionManager.getConnection();
//			stat = currentCon.createStatement();
//			ResultSet rs = stat.executeQuery(
//					"select s.student_id, s.student_name, s.class_name, e.examination_id, round(sum(g.grade_mark)/10,2) from student s left join studentgrade sg on (s.STUDENT_ID = sg.STUDENT_ID)\r\n"
//							+ "left join grade g on (g.GRADE_ID = sg.GRADE_ID) left join subject sub on (sub.SUBJECT_ID = sg.SUBJECT_ID) left join examination e on (e.EXAMINATION_ID = sg.EXAMINATION_ID)\r\n"
//							+ "where  SUBSTR(s.class_name, 1, 1) = 1 \r\n" + "OR SUBSTR(s.class_name, 1, 1) = 2 \r\n"
//							+ "OR SUBSTR(s.class_name, 1, 1) = 3 \r\n"
//							+ "group by student_id,student_name, class_name, examination_id ");
//
//			while (rs.next()) {
//				Student student = new Student();
//				student.setId(rs.getString(1));
//				student.setName(rs.getString(2));
//				student.setClassName(rs.getString(3));
//				student.setCgpa(rs.getString(5));
//				student.setExaminationID(rs.getString(4));
//
//				students.add(student);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return students;
//	}

//	Get all joineed student with no cgpa
	public static List<Student> getAllJoinedStudentNoCGPA(String examinationID, String eventID) {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("SELECT\r\n" + 
					"    s.student_id,\r\n" + 
					"    s.student_name,\r\n" + 
					"    s.class_name\r\n" + 
					"FROM\r\n" + 
					"    student s join studentevent se on (s.STUDENT_ID = se.STUDENT_ID)\r\n" + 
					"WHERE\r\n" + 
					"	se.EVENT_ID = '"+ eventID +"' AND\r\n" + 
					"    s.STUDENT_ID NOT IN (select STUDENT_ID from studentgrade)\r\n" + 
					"    \r\n" + 
					"UNION\r\n" + 
					"\r\n" + 
					"SELECT\r\n" + 
					"    s.student_id,\r\n" + 
					"    s.student_name,\r\n" + 
					"    s.class_name\r\n" + 
					"FROM\r\n" + 
					"    student s join studentevent se on (s.STUDENT_ID = se.STUDENT_ID)\r\n" + 
					"WHERE \r\n" + 
					"	se.EVENT_ID = '"+ eventID +"' AND\r\n" + 
					"	s.STUDENT_ID\r\n" + 
					"NOT IN\r\n" + 
					"\r\n" + 
					"    (SELECT\r\n" + 
					"    s.student_id\r\n" + 
					"FROM\r\n" + 
					"    student s JOIN studentgrade sg ON (s.STUDENT_ID = sg.STUDENT_ID)\r\n" + 
					"WHERE\r\n" + 
					"\r\n" + 
					"    sg.EXAMINATION_ID = '"+ examinationID +"')");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassName(rs.getString(3));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}
	
//	Get all joined student with cgpa
	public static List<Student> getAllJoinedStudentWithCGPA(String examinationID, String eventID) {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("SELECT\r\n" + 
					"    s.student_id,\r\n" + 
					"    s.student_name,\r\n" + 
					"    s.class_name,\r\n" + 
					"    ROUND(SUM(g.grade_mark) / 10,\r\n" + 
					"    2)\r\n" + 
					"FROM\r\n" + 
					"    student s\r\n" + 
					"LEFT JOIN studentgrade sg ON\r\n" + 
					"    (s.STUDENT_ID = sg.STUDENT_ID)\r\n" + 
					"LEFT JOIN grade g ON\r\n" + 
					"    (g.GRADE_ID = sg.GRADE_ID)\r\n" + 
					"LEFT JOIN SUBJECT sub ON\r\n" + 
					"    (sub.SUBJECT_ID = sg.SUBJECT_ID)\r\n" + 
					"LEFT JOIN examination e ON\r\n" + 
					"    (e.EXAMINATION_ID = sg.EXAMINATION_ID)\r\n" + 
					"JOIN studentevent se on (se.STUDENT_ID = s.STUDENT_ID)\r\n" + 
					"WHERE se.EVENT_ID = '"+ eventID +"' AND\r\n" + 
					" e.EXAMINATION_ID = '"+ examinationID +"' \r\n" + 
					"GROUP BY\r\n" + 
					"    student_id,\r\n" + 
					"    student_name,\r\n" + 
					"    class_name");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassName(rs.getString(3));
				student.setCgpa(rs.getString(4));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}
	
//	 Get all students grade 1 (PT3)
	public static List<Student> getAllStudentGrade1(String examinationID) {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("SELECT\r\n" + "    student_id,\r\n" + "    student_name,\r\n"
					+ "    class_name\r\n" + "FROM\r\n" + "    student \r\n" + "WHERE\r\n"
					+ "    (SUBSTR(class_name, 1, 1) = 1 OR SUBSTR(class_name, 1, 1) = 2 OR SUBSTR(class_name, 1, 1) = 3) \r\n"
					+ "    AND\r\n" + "    STUDENT_ID NOT IN (select STUDENT_ID from studentgrade)\r\n" + "    \r\n"
					+ "UNION\r\n" + "\r\n" + "SELECT\r\n" + "    DISTINCT student_id,\r\n" + "    student_name,\r\n"
					+ "    class_name\r\n" + "FROM\r\n" + "    student \r\n" + "WHERE\r\n"
					+ "    ( SUBSTR(class_name, 1, 1) = 1 OR SUBSTR(class_name, 1, 1) = 2 OR SUBSTR(class_name, 1, 1) = 3)\r\n"
					+ "	AND (STUDENT_ID\r\n" + "NOT IN\r\n" + "\r\n" + "    (SELECT\r\n" + "    s.student_id\r\n"
					+ "FROM\r\n" + "    student s JOIN studentgrade sg ON (s.STUDENT_ID = sg.STUDENT_ID)\r\n"
					+ "WHERE\r\n"
					+ "    (SUBSTR(class_name, 1, 1) = 1 OR SUBSTR(class_name, 1, 1) = 2 OR SUBSTR(class_name, 1, 1) = 3) \r\n"
					+ "    AND\r\n" + "    sg.EXAMINATION_ID = '" + examinationID + "'))");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassName(rs.getString(3));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	// Get all students grade 2 (SPM)
	public static List<Student> getAllStudentGrade2(String examinationID) {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("SELECT\r\n" + "    student_id,\r\n" + "    student_name,\r\n"
					+ "    class_name\r\n" + "FROM\r\n" + "    student \r\n" + "WHERE\r\n"
					+ "    (SUBSTR(class_name, 1, 1) = 4 OR SUBSTR(class_name, 1, 1) = 5) \r\n" + "    AND\r\n"
					+ "    STUDENT_ID NOT IN (select STUDENT_ID from studentgrade)\r\n" + "    \r\n" + "UNION\r\n"
					+ "\r\n" + "SELECT\r\n" + "    DISTINCT student_id,\r\n" + "    student_name,\r\n"
					+ "    class_name\r\n" + "FROM\r\n" + "    student \r\n" + "WHERE\r\n"
					+ "    ( SUBSTR(class_name, 1, 1) = 4 OR SUBSTR(class_name, 1, 1) = 5 )\r\n"
					+ "	AND (STUDENT_ID\r\n" + "NOT IN\r\n" + "\r\n" + "    (SELECT\r\n" + "    s.student_id\r\n"
					+ "FROM\r\n" + "    student s JOIN studentgrade sg ON (s.STUDENT_ID = sg.STUDENT_ID)\r\n"
					+ "WHERE\r\n" + "    (SUBSTR(class_name, 1, 1) = 4 OR SUBSTR(class_name, 1, 1) = 5 ) \r\n"
					+ "    AND\r\n" + "    sg.EXAMINATION_ID = '" + examinationID + "'))");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassName(rs.getString(3));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

//	// Get all students grade 2 (SPM)
//	public static List<Student> getAllStudentGrade2() {
//		List<Student> students = new ArrayList<Student>();
//		try {
//			currentCon = ConnectionManager.getConnection();
//			stat = currentCon.createStatement();
//			ResultSet rs = stat.executeQuery(
//					"select s.student_id, s.student_name, s.class_name, e.examination_id, round(sum(g.grade_mark)/10,2) from student s left join studentgrade sg on (s.STUDENT_ID = sg.STUDENT_ID)\r\n"
//							+ "left join grade g on (g.GRADE_ID = sg.GRADE_ID) left join subject sub on (sub.SUBJECT_ID = sg.SUBJECT_ID) left join examination e on (e.EXAMINATION_ID = sg.EXAMINATION_ID)\r\n"
//							+ "where  SUBSTR(s.class_name, 1, 1) = 4  \r\n" + "OR SUBSTR(s.class_name, 1, 1) = 5  \r\n"
//							+ "group by student_id,student_name, class_name, examination_id ");
//
//			while (rs.next()) {
//				Student student = new Student();
//				student.setId(rs.getString(1));
//				student.setName(rs.getString(2));
//				student.setClassName(rs.getString(3));
//				student.setCgpa(rs.getString(5));
//				student.setExaminationID(rs.getString(4));
//
//				students.add(student);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return students;
//	}

	// Get all students grade 1 (PT3)
	public static List<Student> getAllStudentGradeCgpa1(String examinationID) {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select s.student_id, s.student_name, s.class_name, round(sum(g.grade_mark)/10,2) from student s left join studentgrade sg on (s.STUDENT_ID = sg.STUDENT_ID)\r\n"
							+ "left join grade g on (g.GRADE_ID = sg.GRADE_ID) left join subject sub on (sub.SUBJECT_ID = sg.SUBJECT_ID) left join examination e on (e.EXAMINATION_ID = sg.EXAMINATION_ID)\r\n"
							+ "where  (SUBSTR(s.class_name, 1, 1) = 1 \r\n" + "OR SUBSTR(s.class_name, 1, 1) = 2 \r\n"
							+ "OR SUBSTR(s.class_name, 1, 1) = 3 )\r\n" + "AND\r\n" + "e.EXAMINATION_ID = "
							+ examinationID + " group by student_id ,student_name, class_name ");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassName(rs.getString(3));
				student.setCgpa(rs.getString(4));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	// Get all students grade 2 (SPM)
	public static List<Student> getAllStudentGradeCgpa2(String examinationID) {
		List<Student> students = new ArrayList<Student>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select s.student_id, s.student_name, s.class_name, round(sum(g.grade_mark)/10,2) from student s left join studentgrade sg on (s.STUDENT_ID = sg.STUDENT_ID) "
							+ "left join grade g on (g.GRADE_ID = sg.GRADE_ID) left join subject sub on (sub.SUBJECT_ID = sg.SUBJECT_ID) left join examination e on (e.EXAMINATION_ID = sg.EXAMINATION_ID) "
							+ "where  ( SUBSTR(s.class_name, 1, 1) = 4 "
							+ "OR SUBSTR(s.class_name, 1, 1) = 5 ) AND e.EXAMINATION_ID = " + examinationID
							+ " group by student_id ,student_name, class_name ");

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassName(rs.getString(3));
				student.setCgpa(rs.getString(4));

				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}
	
	
}
