package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.idao.iTicketDao;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;

public class TicketService {
	private iTicketDao ticketDao=DAOFactory.creatTicketDao();
	
	public int add(Ticket stu){
		return ticketDao.insert(stu); 		
	}
	
	public int modify(Ticket stu){
		return ticketDao.update(stu); 		
	}
	
	public int delete(int ID){
		return ticketDao.delete(ID); 		
	}
	
	public List<Ticket> Fetch(String condt){
		return ticketDao.select(condt);		
	}
	
	public List<Ticket> FetchAll(){
		return ticketDao.select("");		
	}
	
	public List<Ticket> FetchId(int id){
		
		return ticketDao.select(Ticket.TICKET_ID+" = "+id);		
	}
}

