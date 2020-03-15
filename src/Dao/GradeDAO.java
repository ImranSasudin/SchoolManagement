package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.Grade;
import Connection.ConnectionManager;

public class GradeDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static String gradeID, gradeName, gradeMark, category;

	// Get all grades
	public static List<Grade> getAllGradesByCategory(String category) {
		List<Grade> grades = new ArrayList<Grade>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery("select * from grade where category ='" + category + "' ");

			while (rs.next()) {
				Grade grade = new Grade();
				grade.setGradeID(rs.getString("grade_id"));
				grade.setGradeName(rs.getString("grade_name"));
				grade.setGradeMark(rs.getString("grade_mark"));
				grade.setCategory(rs.getString("category"));

				grades.add(grade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return grades;
	}

}
