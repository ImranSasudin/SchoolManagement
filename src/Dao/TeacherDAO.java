package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.Teacher;
import Connection.ConnectionManager;

public class TeacherDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	static Integer teacherID;
	static String teacherName, password, classHandle, department;

	// Teacher Login
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
	// Register New Teacher
	public static void add(Teacher bean) throws NoSuchAlgorithmException {
		teacherName = bean.getTeacherName();
		password = bean.getPassword();
		classHandle = bean.getClassHandle();
		department = bean.getDepartment();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement(
					"insert into teacher (teacher_name, password, class_handle, department) values(?,?,?,?)");
			ps.setString(1, teacherName);
			ps.setString(2, password);
			ps.setString(3, classHandle);
			ps.setString(4, department);
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
	
	public static List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        try {
        	currentCon = ConnectionManager.getConnection();
        	stat = currentCon.createStatement();
            ResultSet rs = stat.executeQuery("select * from teacher order by teacher_id");
            
            while (rs.next()) {
            	Teacher teacher = new Teacher();
            	teacher.setTeacherID(rs.getInt("teacher_id"));
                teacher.setTeacherName(rs.getString("teacher_name"));
                teacher.setClassHandle(rs.getString("class_handle"));
                teacher.setDepartment(rs.getString("department"));

                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }
}
