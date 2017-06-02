package xupt.se.ttms.model;

import java.io.Serializable;

public class PlayInfo implements Serializable{
	private int play_id;

	private String play_name;
	private String play_director;
	private String play_introduce;
   
	private String play_protagonist;
	private float play_price;
	private String play_status;
	private String play_type;
	private int play_lenth;
	public static final String ID="play_id";
	public static final String NAME="play_name";
	public static final String DIRECTOR="play_director";
	public static final String INTRODUCE="play_introduction";
	public static final String PROTAGONIST="play_protagonist";
	public static final String PRICE="play_ticket_price";
	public static final String STATUS="play_status";
	public static final String TYPE="play_type";
	public static final String LENTH="play_length";
	
	public int getPlay_lenth() {
		return play_lenth;
	}
	public void setPlay_lenth(int play_lenth) {
		this.play_lenth = play_lenth;
	}
	public String getPlay_type() {
		return play_type;
	}
	public void setPlay_type(String play_type) {
		this.play_type = play_type;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public String getPlay_name() {
		return play_name;
	}
	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}
	public String getPlay_director() {
		return play_director;
	}
	public void setPlay_director(String play_director) {
		this.play_director = play_director;
	}
	public String getPlay_introduce() {
		return play_introduce;
	}
	public void setPlay_introduce(String play_introduce) {
		this.play_introduce = play_introduce;
	}
	public String getPlay_protagonist() {
		return play_protagonist;
	}
	public void setPlay_protagonist(String play_protagonist) {
		this.play_protagonist = play_protagonist;
	}
	public float getPlay_price() {
		return play_price;
	}
	public void setPlay_price(float play_price) {
		this.play_price = play_price;
	}
	public String getPlay_status() {
		return play_status;
	}
	public void setPlay_status(String play_status) {
		this.play_status = play_status;
	}
	
	
}
