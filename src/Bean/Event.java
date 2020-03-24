package Bean;

import java.util.Date;

public class Event {
	
	String eventID, eventName, eventPlace, eventDateText;
	Date eventDate;
	boolean valid;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDateText() {
		return eventDateText;
	}

	public void setEventDateText(String eventDateText) {
		this.eventDateText = eventDateText;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
}
