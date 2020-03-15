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
import Bean.StudentGrade;
import Dao.ExaminationDAO;
import Dao.GradeDAO;
import Dao.StudentDAO;
import Dao.StudentGradeDAO;
import Dao.SubjectDAO;

/**
 * Servlet implementation class ExaminationController
 */
@WebServlet("/ExaminationController")
public class ExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat format;
	DateFormat formatter; 
	Examination exam = new Examination();
	StudentGrade stuGrade = new StudentGrade();
	
	private static String LIST_EXAMINATION = "/Examination/ListExaminations.jsp";
	private static String LIST_STUDENTS = "/Examination/StudentList.jsp";
	private static String ADD_GRADE = "/Examination/AddGrade.jsp";
	private static String UPDATE_GRADE = "/Examination/UpdateGrade.jsp";
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
		else if (action.equalsIgnoreCase("AddGrade")) {
			String id = request.getParameter("id");
			String category = request.getParameter("category");
			String name = request.getParameter("name");
			String examinationID = request.getParameter("examID");
			
			request.setAttribute("examination" , ExaminationDAO.getExamination(examinationID));
			request.setAttribute("subjects", SubjectDAO.getAllSubjectsByCategory(category));
			request.setAttribute("grades", GradeDAO.getAllGradesByCategory(category));
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			
			forward = ADD_GRADE;
		}
		else if (action.equalsIgnoreCase("UpdateGrade")) {
			String id = request.getParameter("id");
			String category = request.getParameter("category");
			String name = request.getParameter("name");
			String examinationID = request.getParameter("examID");
			
			request.setAttribute("examination" , ExaminationDAO.getExamination(examinationID));
			request.setAttribute("subjectGrades", StudentGradeDAO.getAllStudentGrades(id, examinationID));
			request.setAttribute("grades", GradeDAO.getAllGradesByCategory(category));
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			
			forward = UPDATE_GRADE;
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
		else if (action.equalsIgnoreCase("AddGrade")) {
			String examinationID = request.getParameter("examinationID");
			String studentID = request.getParameter("studentID");
			
			String subjectId[] = request.getParameterValues("subjectID");
			String gradeId[] = request.getParameterValues("gradeID");
			
			stuGrade.setExaminationID(examinationID);
			stuGrade.setStudentID(studentID);
			
			for(int i = 0; i<subjectId.length; i++) {
				stuGrade.setSubjectID(subjectId[i]);
				stuGrade.setGradeID(gradeId[i]);
				try {
					StudentGradeDAO.addStudentGrade(stuGrade);
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Add Student Grade Success');");
			pw.println("window.location.href='/SchoolManagement/ExaminationController?action=StudentGrade&id="+ examinationID +"';");
			pw.println("</script>");
		}
		else if (action.equalsIgnoreCase("UpdateGrade")) {
			String examinationID = request.getParameter("examinationID");
			String studentID = request.getParameter("studentID");
			
			String subjectId[] = request.getParameterValues("subjectID");
			String gradeId[] = request.getParameterValues("gradeID");
			
			stuGrade.setExaminationID(examinationID);
			stuGrade.setStudentID(studentID);
			
			// Delete current student grade
			try {
				StudentGradeDAO.deleteStudentGrade(stuGrade);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Then add back
			for(int i = 0; i<subjectId.length; i++) {
				stuGrade.setSubjectID(subjectId[i]);
				stuGrade.setGradeID(gradeId[i]);
				try {
					StudentGradeDAO.addStudentGrade(stuGrade);
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Update Student Grade Success');");
			pw.println("window.location.href='/SchoolManagement/ExaminationController?action=StudentGrade&id="+ examinationID +"';");
			pw.println("</script>");
		}
	}

}
