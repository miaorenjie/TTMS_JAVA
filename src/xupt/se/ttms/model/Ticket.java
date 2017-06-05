package xupt.se.ttms.model;

public class Ticket {
	
	private int ticket_id;
	private int play_id;
	private int seat_id;
	private int schedule_id;
	private String ticket_date;
	
	public static final String TICKET_ID="ticket_id";
	public static final String PLAY_ID="play_id";
	public static final String SEAT_ID="seat_id";
	public static final String SCHEDULE_ID="schedule_id";
	public static final String TICKET_DATE="ticket_date";

	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	
	
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public String getTicket_date() {
		return ticket_date;
	}
	public void setTicket_date(String ticket_date) {
		this.ticket_date = ticket_date;
	}
	
	
}
