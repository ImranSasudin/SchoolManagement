package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
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
	
	private static String VIEW_ACCOUNT = "/Teacher/ViewAccount.jsp";
	private String forward = "";

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("Account")) {
			HttpSession session = request.getSession(true);
			String role = (String) session.getAttribute("userRoleSession");
			
			if (role.equalsIgnoreCase("Teacher")) {
				Integer teacherID = Integer.parseInt(request.getParameter("ID"));
				forward = VIEW_ACCOUNT;
				
				teacher = TeacherDAO.getUserByID(teacherID);
				String classHandle = teacher.getClassHandle();
				
				String[] arrOfStr = classHandle.split(" ");
				
				String form = arrOfStr[0];
				String formClass = arrOfStr[1];
				
				request.setAttribute("teacher", teacher);
				request.setAttribute("form", form);
				request.setAttribute("formClass", formClass);
			}
			else if (role.equalsIgnoreCase("Student")) {
				
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
 	    view.forward(request, response);

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
				session.setAttribute("userIDSession", teacherID);
				session.setAttribute("userNameSession", teacher.getTeacherName());
				session.setAttribute("userRoleSession", "Teacher");

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Login Successful');");
				pw.println("window.location.href='/SchoolManagement/StudentController?action=ListStudents';");
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
