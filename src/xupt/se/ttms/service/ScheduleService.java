package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayInfoDao;
import xupt.se.ttms.idao.iScheduleDao;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;

public class ScheduleService {
	private iScheduleDao scheduleDao=DAOFactory.creatScheduleDao();
	public int add(ScheduleInfo scheduleInfo){
		return scheduleDao.insert(scheduleInfo); 		
	}
	
	public int modify(ScheduleInfo scheduleInfo){
		return scheduleDao.update(scheduleInfo); 		
	}
	
	public int delete(int ID){
		return scheduleDao.delete(ID); 		
	}
	
	public List<ScheduleInfo> Fetch(String condt){
		return scheduleDao.select(condt);		
	}
	
	public List<ScheduleInfo> FetchAll(){
		return scheduleDao.select("");		
	}
	public List<ScheduleInfo> FetchId(int id){
		
		return scheduleDao.select(ScheduleInfo.SCHED_ID+" = "+id);		
	}
}
