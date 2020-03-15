package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
						"insert into studentgrade (student_id, subject_id, examination_id, grade_id)"
								+ " values(?,?,?,?)");
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

}
