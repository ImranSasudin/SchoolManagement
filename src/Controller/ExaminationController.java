package Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Examination;
import Dao.ExaminationDAO;

/**
 * Servlet implementation class ExaminationController
 */
@WebServlet("/ExaminationController")
public class ExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat format;
	DateFormat formatter; 
	Examination exam = new Examination();
       
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("AddExamination")) {
			String examinationName = request.getParameter("name");
			
			formatter = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date d1 = null;
			try {
				d1 = formatter.parse(request.getParameter("date"));
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
	        
	        
		}
	}

}
