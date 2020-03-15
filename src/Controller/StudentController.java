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

import Bean.Student;
import Dao.StudentDAO;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Student student = new Student();
	
	private static String LIST_STUDENT = "/Student/ListStudents.jsp";
	private static String UPDATE_STUDENT = "/Student/UpdateStudent.jsp";
	
	private String forward = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("ListStudents")) {
			request.setAttribute("students" , StudentDAO.getAllStudents());
			forward = LIST_STUDENT;
		}
		else if (action.equalsIgnoreCase("UpdateStudent")) {
			String id = request.getParameter("id");
			forward = UPDATE_STUDENT;
			
			student = StudentDAO.getUserByID(id);
			String classHandle = student.getClassName();
			
			String[] arrOfStr = classHandle.split(" ");
			
			String form = arrOfStr[0];
			String formClass = arrOfStr[1];
			
			request.setAttribute("student", student);
			request.setAttribute("form", form);
			request.setAttribute("formClass", formClass);
		}
	
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
 	    view.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		// Register Student
		if (action.equalsIgnoreCase("RegisterStudent")) {
			String ic = request.getParameter("ic");
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");

			String form = request.getParameter("form");
			String studentClass = request.getParameter("class");
			String classHandle = form + " " + studentClass;

			String guardianName = request.getParameter("guardianName");
			String guardianJob = request.getParameter("job");

			student.setIc(ic);
			student.setName(name);
			student.setAge(age);
			student.setAddress(address);
			student.setClassName(classHandle);
			student.setGuardianName(guardianName);
			student.setGuardianJob(guardianJob);

			student = StudentDAO.getStudent(student);
			// Student IC not exist yet
			if (!student.isValid()) {

				try {
					StudentDAO.add(student);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Register Success');");
				pw.println("window.location.href='/SchoolManagement/StudentController?action=ListStudents';");
				pw.println("</script>");
				
			} else {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('IC already exist');");
				pw.println("window.location.href='/SchoolManagement/Student/RegisterStudent.jsp';");
				pw.println("</script>");
			}
		}
		else if (action.equalsIgnoreCase("UpdateStudent")) {
			String id = request.getParameter("id");
			String ic = request.getParameter("ic");
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			String address = request.getParameter("address");

			String form = request.getParameter("form");
			String studentClass = request.getParameter("class");
			String classHandle = form + " " + studentClass;

			String guardianName = request.getParameter("guardianName");
			String guardianJob = request.getParameter("job");
			
			student.setId(id);
			student.setIc(ic);
			student.setName(name);
			student.setAge(age);
			student.setAddress(address);
			student.setClassName(classHandle);
			student.setGuardianName(guardianName);
			student.setGuardianJob(guardianJob);
			
			try {
				StudentDAO.update(student);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Update Success');");
			pw.println("window.location.href='/SchoolManagement/StudentController?action=ListStudents';");
			pw.println("</script>");
		}
	}

}
