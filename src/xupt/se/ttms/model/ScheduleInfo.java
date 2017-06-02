package xupt.se.ttms.model;

public class ScheduleInfo {
	private int sched_id;
	private int studio_id;
	private int play_id;
	private String sched_time;
	private float schedule_price;
	
	public static final String SCHED_ID="sched_id";
	public static final String STUDIO_ID="studio_id";
	public static final String PLAY_ID="play_id";
	public static final String SCHED_TIME="sched_time";
	
	public static final String SCHED_PRICE="schedule_price";
	
	
	public float getSchedule_price() {
		return schedule_price;
	}
	public void setSchedule_price(float schedule_price) {
		this.schedule_price = schedule_price;
	}
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public String getSched_time() {
		return sched_time;
	}
	public void setSched_time(String sched_time) {
		this.sched_time = sched_time;
	}
	
	
}
