package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

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

}
