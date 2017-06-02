package xupt.se.ttms.model;

import java.io.Serializable;

public class SeatInfo implements Serializable{

	private int seat_id;
	private int studio_id;
	private int seat_row;
	private int schedule_id;
	private int seat_column;
	private int movie_id;

	public static final String SCHEDULE_ID="schedule_id	";
	public static final String SEAT_ID="seat_id";
	public static final String STUDIO_ID="studio_id";
	public static final String SEAT_ROW="seat_row";
	public static final String SEAT_COLUMN="seat_column";
	public static final String MOVIE_ID="movie_id";
	
	
	public int getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}
	public int getSeat_column() {
		return seat_column;
	}
	public void setSeat_column(int seat_column) {
		this.seat_column = seat_column;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	
	
	
}
