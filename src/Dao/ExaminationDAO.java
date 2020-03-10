package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bean.Examination;
import Connection.ConnectionManager;

public class ExaminationDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static String examinationID, examinationName;
	static Date examinationDate;
	
	// Register New Student
		public static void add(Examination bean) throws NoSuchAlgorithmException {
			examinationName = bean.getExaminationName();
			examinationDate = bean.getExaminationDate();

			try {
				currentCon = ConnectionManager.getConnection();

				ps = currentCon.prepareStatement(
						"insert into examination (examination_name, examination_date)"
								+ " values(?,?)");
				ps.setString(1, examinationName);
				ps.setDate(2, (java.sql.Date) examinationDate);
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
		
		// Get student by student id
		public static Examination getExamination(String id) {

			Examination exam = new Examination();

			try {
				currentCon = ConnectionManager.getConnection();
				ps = currentCon.prepareStatement("select examination_id, examination_name, date_format(EXAMINATION_DATE,\"%M %Y\") from examination where examination_id =?");

				ps.setString(1, id);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					exam.setExaminationID(rs.getString(1));
					exam.setExaminationName(rs.getString(2));
					exam.setExaminationDateText(rs.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return exam;
		}
		
		// Get all examinations
		public static List<Examination> getAllExaminations() {
			List<Examination> exams = new ArrayList<Examination>();
			try {
				currentCon = ConnectionManager.getConnection();
				stat = currentCon.createStatement();
				ResultSet rs = stat.executeQuery("select EXAMINATION_ID, EXAMINATION_NAME, date_format(EXAMINATION_DATE,\"%M, %Y\") from examination ");

				while (rs.next()) {
					Examination exam = new Examination();
					exam.setExaminationID(rs.getString("EXAMINATION_ID"));
					exam.setExaminationName(rs.getString("EXAMINATION_NAME"));
					exam.setExaminationDateText(rs.getString(3));

					exams.add(exam);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return exams;
		}

}
