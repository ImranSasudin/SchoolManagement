package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.Teacher;
import Connection.ConnectionManager;

public class TeacherDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	static Integer teacherID;
	static String teacherName, password, classHandle, department;

	public static Teacher login(Teacher bean) throws NoSuchAlgorithmException {

		teacherID = bean.getTeacherID();
		password = bean.getPassword();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement("select * from teacher where teacher_id = ? AND password = ?");
			ps.setInt(1, teacherID);
			ps.setString(2, password);

			rs = ps.executeQuery();

			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				bean.setTeacherID(rs.getInt("teacher_id"));
				bean.setTeacherName(rs.getString("teacher_name"));
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
}
