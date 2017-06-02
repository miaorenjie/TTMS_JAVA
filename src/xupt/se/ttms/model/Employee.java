package xupt.se.ttms.model;

import java.io.Serializable;

public class Employee implements Serializable {
	private int empId;
	private String empName;
	private String empUserName;
	private String empPassword;
	private String empAdress;
	private String empTel;
	private String empEmail;
	private String empType;

	public static final String ID="emp_id";
	public static final String USERNAME="emp_userName";
	public static final String NAME="emp_name";
	public static final String PASSWORD="emp_passWord";
	public static final String ADRESS="emp_addr";
	public static final String TEL="emp_tel_num";
	public static final String EMAIL="emp_email";
	public static final String TYPE="emp_type";
	
	public String getEmpUserName() {
		return empUserName;
	}


	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getEmpPassword() {
		return empPassword;
	}


	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}


	public String getEmpAdress() {
		return empAdress;
	}


	public void setEmpAdress(String empAdress) {
		this.empAdress = empAdress;
	}


	public String getEmpTel() {
		return empTel;
	}


	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}


	public String getEmpEmail() {
		return empEmail;
	}


	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}


	public String getEmpType() {
		return empType;
	}


	public void setEmpType(String empType) {
		this.empType = empType;
	}



}
