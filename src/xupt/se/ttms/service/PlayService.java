package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iOrderDao;
import xupt.se.ttms.idao.iPlayInfoDao;
import xupt.se.ttms.model.Order;
import xupt.se.ttms.model.PlayInfo;

public class PlayService {
	private iPlayInfoDao playInfoDao=DAOFactory.creatPlayInfoDao();
	public int add(PlayInfo playInfo){
		return playInfoDao.insert(playInfo); 		
	}
	
	public int modify(PlayInfo playInfo){
		return playInfoDao.update(playInfo); 		
	}
	
	public int delete(int ID){
		return playInfoDao.delete(ID); 		
	}
	
	public List<PlayInfo> Fetch(String condt){
		return playInfoDao.select(condt);		
	}
	
	public List<PlayInfo> FetchAll(){
		return playInfoDao.select("");		
	}
}
