package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Bean.StudentEvent;
import Connection.ConnectionManager;

public class StudentEventDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static String studentID, eventID;
	
	static StudentEvent studentEvent = new StudentEvent();
	
	// Verify event
	public static StudentEvent verifyEvent(String eventID, String studentID) {
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select * from studentevent where student_id = '"+ studentID +"' and event_id = '"+ eventID +"' ");

			if (rs.next()) {
				studentEvent.setValid(true);
			} else {
				studentEvent.setValid(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentEvent;
	}
	
	// Student join event
	public static void add(StudentEvent stu) {
		eventID = stu.getEventID();
		studentID = stu.getStudentID();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon
					.prepareStatement("insert into studentevent (student_id, event_id)" + " values(?,?)");
			ps.setString(1, studentID);
			ps.setString(2, eventID);
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
