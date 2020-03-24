package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bean.Event;
import Connection.ConnectionManager;

public class EventDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stat = null;
	
	static String eventID, eventName, eventPlace;
	static Date eventDate;
	
	// Add New Event
	public static void add(Event bean) throws NoSuchAlgorithmException {
		eventName = bean.getEventName();
		eventPlace = bean.getEventPlace();
		eventDate = bean.getEventDate();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon
					.prepareStatement("insert into event (event_name, event_place, event_date)" + " values(?,?,?)");
			ps.setString(1, eventName);
			ps.setString(2, eventPlace);
			ps.setDate(3, (java.sql.Date) eventDate);
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
	
	// Get all events
	public static List<Event> getAllEvents() {
		List<Event> events = new ArrayList<Event>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select event_id, event_name, event_place, date_format(event_date,\"%D %M, %Y\") from event ");

			while (rs.next()) {
				Event event = new Event();
				event.setEventID(rs.getString(1));
				event.setEventName(rs.getString(2));
				event.setEventDateText(rs.getString(4));
				event.setEventPlace(rs.getString(3));

				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}
	
	// Get all events
	public static Event getEventByID(String id) {
		Event event = new Event();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select event_id, event_name, event_place, date_format(event_date,\"%D %M, %Y\") from event where event_id = '"
							+ id + "' and event_date > sysdate() ");

			if (rs.next()) {
				event.setValid(true);
				event.setEventID(rs.getString(1));
				event.setEventName(rs.getString(2));
				event.setEventDateText(rs.getString(4));
				event.setEventPlace(rs.getString(3));
			} else {
				event.setValid(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return event;
	}
	
	// Get all events
	public static List<Event> getAllJoinedEvents(String id) {
		List<Event> events = new ArrayList<Event>();
		try {
			currentCon = ConnectionManager.getConnection();
			stat = currentCon.createStatement();
			ResultSet rs = stat.executeQuery(
					"select e.event_id, e.event_name, e.event_place, date_format(e.event_date,\"%D %M, %Y\") from event e "
					+ "join studentevent se on (e.event_id = se.event_id) where se.student_id = '"+ id +"'");

			while (rs.next()) {
				Event event = new Event();
				event.setEventID(rs.getString(1));
				event.setEventName(rs.getString(2));
				event.setEventDateText(rs.getString(4));
				event.setEventPlace(rs.getString(3));

				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return events;
	}
}
