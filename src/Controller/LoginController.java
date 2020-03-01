package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Teacher;
import Dao.TeacherDAO;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Teacher teacher = new Teacher();

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		// For teacher login
		if (action.equalsIgnoreCase("TeacherLogin")) {
			
			Integer teacherID = Integer.parseInt(request.getParameter("teacherID"));
			String password = request.getParameter("teacherPassword");

			teacher.setTeacherID(teacherID);
			teacher.setPassword(password);

			try {
				teacher = TeacherDAO.login(teacher);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			// If teacher exist
			if (teacher.isValid()) {
				// Set Session
				HttpSession session = request.getSession(true);
				session.setAttribute("teacherIDSession", teacherID);
				session.setAttribute("teacherNameSession", teacher.getTeacherName());
				session.setAttribute("userRoleSession", "Teacher");

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Login Successful');");
				pw.println("window.location.href='Student/ListStudents.jsp';");
				pw.println("</script>");
			}
			// If teacher not exist
			else {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Invalid ID or Password');");
				pw.println("window.location.href='index.jsp';");
				pw.println("</script>");
			}

		}
	}

}
