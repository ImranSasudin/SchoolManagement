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


@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Teacher teacher = new Teacher();
	
	private static String LIST_TEACHER = "/Teacher/ListTeachers.jsp";
	private String forward = "";
       

    public TeacherController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("ListTeachers")) {
			request.setAttribute("teachers" , TeacherDAO.getAllTeachers());
			forward = LIST_TEACHER;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
 	    view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		// Teacher Registration
		if (action.equalsIgnoreCase("RegisterTeacher")) {
			String teacherName = request.getParameter("teacherName");
			String password = request.getParameter("teacherPassword");
			
			String form = request.getParameter("form");
			String teacherClass = request.getParameter("class");
			String classHandle = form + " " + teacherClass;
			
			String department = request.getParameter("department");
			
			teacher.setTeacherName(teacherName);
			teacher.setPassword(password);
			teacher.setClassHandle(classHandle);
			teacher.setDepartment(department);
			
			try {
				TeacherDAO.add(teacher);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('New Teacher Registered');");
			pw.println("window.location.href='/SchoolManagement/TeacherController?action=ListTeachers';");
			pw.println("</script>");
			

		}
		
		// Update Teacher
		else if (action.equalsIgnoreCase("UpdateTeacher")) {
			
			HttpSession session = request.getSession(true);
			Integer teacherID = (Integer) session.getAttribute("userIDSession");
			String teacherName = request.getParameter("teacherName");
			String password = request.getParameter("teacherPassword");
			
			String form = request.getParameter("form");
			String teacherClass = request.getParameter("class");
			String classHandle = form + " " + teacherClass;
			
			String department = request.getParameter("department");
			
			teacher.setTeacherID(teacherID);
			teacher.setTeacherName(teacherName);
			teacher.setPassword(password);
			teacher.setClassHandle(classHandle);
			teacher.setDepartment(department);
			
			try {
				TeacherDAO.update(teacher);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Successfully Update Account');");
			pw.println("window.location.href='/SchoolManagement/TeacherController?action=ListTeachers';");
			pw.println("</script>");
			
		}
	}

}
