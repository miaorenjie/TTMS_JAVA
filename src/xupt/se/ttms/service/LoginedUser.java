package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.User;
import xupt.se.ttms.model.UserRight;

public class LoginedUser extends User{
	private String empName;
	private List<UserRight> usrRights;
	private static LoginedUser uniInstance = null;
	private Employee employee;
	private LoginedUser(){		
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public synchronized static LoginedUser getInstance() {
		if (uniInstance == null) {
			uniInstance = new LoginedUser();
		}
		return uniInstance;
	}
	
	public void setEmpName(String name){
		empName=name;
	}
	
	public String getEmpName(){
		return  empName;
	}
	
	public void setRights(List<UserRight> rights){
		usrRights=rights;
	}
	
	public List<UserRight> getRights(){
		return  usrRights;
	}	

}
