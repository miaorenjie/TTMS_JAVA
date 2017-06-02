package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeeDao;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;

public class EmployeeService {
	private iEmployeeDao empDao=DAOFactory.creatEmployeeDao();
	public int add(Employee emp){
		return empDao.insert(emp); 		
	}
	
	public int modify(Employee emp){
		return empDao.update(emp); 		
	}
	
	public int delete(int ID){
		return empDao.delete(ID); 		
	}
	
	public List<Employee> Fetch(String condt){
		return empDao.select(condt);		
	}
	
	public List<Employee> FetchAll(){
		return empDao.select("");		
	}
}
