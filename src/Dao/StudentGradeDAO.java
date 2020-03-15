package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.StudentGrade;
import Connection.ConnectionManager;

public class StudentGradeDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;

	static String studentID, subjectID, examinationID, gradeID;

	// Add New Student Grade
	public static void addStudentGrade(StudentGrade bean) throws NoSuchAlgorithmException {
		studentID = bean.getStudentID();
		// id = bean.getId();
		subjectID = bean.getSubjectID();
		examinationID = bean.getExaminationID();
		gradeID = bean.getGradeID();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement(
					"insert into studentgrade (student_id, subject_id, examination_id, grade_id)" + " values(?,?,?,?)");
			ps.setString(1, studentID);
			ps.setString(2, subjectID);
			ps.setString(3, examinationID);
			ps.setString(4, gradeID);

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

	// Get all student grades
	public static List<StudentGrade> getAllStudentGrades(String studentID, String examinationID) {
		List<StudentGrade> stuGrades = new ArrayList<StudentGrade>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select sg.subject_id, sub.subject_name, g.grade_id, g.grade_name from grade g join studentgrade sg on (sg.grade_id = g.GRADE_ID) "
							+ "join subject sub on (sub.SUBJECT_ID = sg.subject_id) join student s on (s.STUDENT_ID = sg.student_id) "
							+ "join examination e on (e.EXAMINATION_ID = sg.examination_id) where s.STUDENT_ID = "
							+ studentID + " and e.EXAMINATION_ID = " + examinationID + " ");

			while (rs.next()) {
				StudentGrade stuGrade = new StudentGrade();
				stuGrade.setSubjectID(rs.getString(1));
				stuGrade.setSubjectName(rs.getString(2));
				stuGrade.setGradeID(rs.getString(3));
				stuGrade.setGradeName(rs.getString(4));

				stuGrades.add(stuGrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stuGrades;
	}
	
	// Dekete Student Grade
	public static void deleteStudentGrade(StudentGrade bean) throws NoSuchAlgorithmException {
		studentID = bean.getStudentID();
		examinationID = bean.getExaminationID();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement(
					"delete from studentgrade where student_id = ? and examination_id = ?");
			ps.setString(1, studentID);
			ps.setString(2, examinationID);

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

}
