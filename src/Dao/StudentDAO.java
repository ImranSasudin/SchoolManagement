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
			//ic = bean.getIc();
			name = bean.getName();
			className = bean.getClassName();
			guardianName = bean.getGuardianName();
			guardianJob = bean.getGuardianJob();
			address = bean.getAddress();

			try {
				currentCon = ConnectionManager.getConnection();

				ps = currentCon.prepareStatement(
						"UPDATE student set student_name = ?," 
						+ "student_age = ?,"
						+ "student_address = ?,"
						+ "class_name = ?,"
						+ "guardian_name = ?,"
						+ "guardian_job = ?"
						+ " where student_id = ? ");

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
	

}
