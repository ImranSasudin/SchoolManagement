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
import javax.servlet.http.HttpSession;

import Bean.Event;
import Bean.StudentEvent;
import Dao.EventDAO;
import Dao.StudentEventDAO;

/**
 * Servlet implementation class EventController
 */
@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SimpleDateFormat format;
	DateFormat formatter; 
	
	Event event = new Event();
	StudentEvent stuEvent = new StudentEvent();
	
	private static String LIST_EVENT = "/Event/ListEvents.jsp";
	private static String EVENT = "/Event/EventResult.jsp";
	private String forward = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("ListEvent")) {
			request.setAttribute("events" , EventDAO.getAllEvents());
			forward = LIST_EVENT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
 	    view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("AddEvent")) {
			String eventName = request.getParameter("name");
			String eventPlace = request.getParameter("place");
			
			formatter = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date d1 = null;
			try {
				d1 = formatter.parse(request.getParameter("date"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //convert java date to sql date
	        java.sql.Date eventDate = new java.sql.Date(d1.getTime());
	        
	        event.setEventName(eventName);
	        event.setEventPlace(eventPlace);
	        event.setEventDate(eventDate);
	        
	        try {
				EventDAO.add(event);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('Add New Event Success');");
		//	pw.println("window.location.href='/SchoolManagement/ExaminationController?action=ListExaminations';");
			pw.println("</script>");
		}
		else if (action.equalsIgnoreCase("SearchEvent")) {
			String id = request.getParameter("id");
			
			event = EventDAO.getEventByID(id);
			// If event exist
			if(event.isValid()) {
				request.setAttribute("event" , event);
				forward = EVENT;
				
				RequestDispatcher view = request.getRequestDispatcher(forward);
		 	    view.forward(request, response);
			}
			else {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Event does not exist or has already past');");
				pw.println("window.location.href='/SchoolManagement/Event/JoinEvent.jsp';");
				pw.println("</script>");
			}
			
		}
		else if (action.equalsIgnoreCase("JoinEvent")) {
			HttpSession session = request.getSession(true);
			
			String id = (String) session.getAttribute("userIDSession");
			String eventID = request.getParameter("id");
			
			stuEvent = StudentEventDAO.verifyEvent(eventID, id);
			//  Student not join yet, then can insert
			if(!stuEvent.isValid()) {
				stuEvent.setStudentID(id);
				stuEvent.setEventID(eventID);
				
				StudentEventDAO.add(stuEvent);
				
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('Successfully join');");
				pw.println("window.location.href='/SchoolManagement/StudentController?action=Home';");
				pw.println("</script>");
			}
			else {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<script>");
				pw.println("alert('You has already join this event');");
				pw.println("window.location.href='/SchoolManagement/StudentController?action=Home';");
				pw.println("</script>");
			}
			
		}
	}

}
