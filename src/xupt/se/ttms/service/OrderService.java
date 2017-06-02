package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeeDao;
import xupt.se.ttms.idao.iOrderDao;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Order;

public class OrderService {
	private iOrderDao orderDao=DAOFactory.creatOrderDao();
	public int add(Order order){
		return orderDao.insert(order); 		
	}
	
	public int modify(Order order){
		return orderDao.update(order); 		
	}
	
	public int delete(int ID){
		return orderDao.delete(ID); 		
	}
	
	public List<Order> Fetch(String condt){
		return orderDao.select(condt);		
	}
	
	public List<Order> FetchAll(){
		return orderDao.select("");		
	}
}
