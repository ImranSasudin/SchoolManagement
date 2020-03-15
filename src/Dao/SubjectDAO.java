package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.Subject;
import Connection.ConnectionManager;

public class SubjectDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static String subjectID, subjectName, category;
	
	// Get all subjects
	public static List<Subject> getAllSubjectsByCategory(String category) {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("select * from subject where category ='"+ category +"' ");

			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSubjectID(rs.getString("subject_id"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setCategory(rs.getString("category"));

				subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subjects;
	}


}
