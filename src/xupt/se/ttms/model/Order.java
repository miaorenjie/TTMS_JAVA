package xupt.se.ttms.model;

import java.io.Serializable;

public class Order implements Serializable{
	
	private int order_id;
	private int employeeId;
	private int ticket_id;
	private float order_price;
	private String order_date;
	private float sale_payment;
	private float sale_change;
	
	public static final String ORDER_ID="sale_ID";
	public static final String EMPLOYEE_ID="emp_id";
	public static final String TICKET_ID="ticket_id";
	public static final String ORDER_PRICE="order_price";
	public static final String ORDER_DATE="sale_time";
	public static final String SALE_PAYMENT="sale_payment";
	public static final String SALE_CHANGE="sale_change";
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public float getSale_payment() {
		return sale_payment;
	}
	public void setSale_payment(float sale_payment) {
		this.sale_payment = sale_payment;
	}
	public float getSale_change() {
		return sale_change;
	}
	public void setSale_change(float sale_change) {
		this.sale_change = sale_change;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public float getOrder_price() {
		return order_price;
	}
	public void setOrder_price(float order_price) {
		this.order_price = order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	
}
