package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Examination;
import Dao.ExaminationDAO;
import Dao.StudentDAO;

/**
 * Servlet implementation class ExaminationController
 */
@WebServlet("/ExaminationController")
public class ExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat format;
	DateFormat formatter; 
	Examination exam = new Examination();
	
	private static String LIST_EXAMINATION = "/Examination/ListExaminations.jsp";
	private static String LIST_STUDENTS = "/Examination/StudentList.jsp";
	private String forward = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExaminationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("ListExaminations")) {
			request.setAttribute("exams" , ExaminationDAO.getAllExaminations());
			forward = LIST_EXAMINATION;
		}
		else if (action.equalsIgnoreCase("StudentGrade")) {
			String examinationID = request.getParameter("id");
			
			request.setAttribute("students1" , StudentDAO.getAllStudentGrade1());
			request.setAttribute("students1cgpa" , StudentDAO.getAllStudentGradeCgpa1(examinationID));
			
			request.setAttribute("students2" , StudentDAO.getAllStudentGrade2());
			request.setAttribute("students2cgpa" , StudentDAO.getAllStudentGradeCgpa2(examinationID));
			
			request.setAttribute("examination" , ExaminationDAO.getExamination(examinationID));
			forward = LIST_STUDENTS;
			
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
 	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("AddExamination")) {
			String examinationName = request.getParameter("name");
			
			formatter = new SimpleDateFormat("yyyy-MM");
	        java.util.Date d1 = null;
			try {
				d1 = formatter.parse(request.getParameter("month"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //convert java date to sql date
	        java.sql.Date examinationDate = new java.sql.Date(d1.getTime());
	        
	        exam.setExaminationName(examinationName);
	        exam.setExaminationDate(examinationDate);
	        
	        try {
				ExaminationDAO.add(exam);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Add New Examination Success');");
			pw.println("window.location.href='/SchoolManagement/ExaminationController?action=ListExaminations';");
			pw.println("</script>");
		}
	}

}
