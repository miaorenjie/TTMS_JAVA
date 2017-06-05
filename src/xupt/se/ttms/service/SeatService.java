package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDao;
import xupt.se.ttms.idao.iSeatDao;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.SeatInfo;

public class SeatService {
	private iSeatDao seatDao=DAOFactory.creatSeatDao();
	public int add(SeatInfo seatInfo){
		return seatDao.insert(seatInfo); 		
	}
	
	public int modify(SeatInfo seatInfo){
		return seatDao.update(seatInfo); 		
	}
	
	public int delete(int ID){
		return seatDao.delete(ID); 		
	}
	
	public List<SeatInfo> Fetch(String condt){
		return seatDao.select(condt);		
	}
	
	public List<SeatInfo> FetchAll(){
		return seatDao.select("");		
	}
	
	public List<SeatInfo> FetchId(int id){
		
		return seatDao.select(SeatInfo.SEAT_ID+" = "+id);		
	}
}
