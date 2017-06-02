package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iScheduleDao;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.util.DBUtil;

public class ScheduleDao implements iScheduleDao{

	public static void main(String[] args) {
		ScheduleInfo sch=new ScheduleInfo();
		sch.setPlay_id(2);
		sch.setSched_time("123");
		sch.setStudio_id(9);
		new ScheduleDao().insert(sch);
		List <ScheduleInfo>employees=new ScheduleDao().select("");
		for(int i=0;i<employees.size();i++)
		{
			System.out.println(employees.get(i).getStudio_id());
		}
		//System.out.println(new ScheduleDao().insert(sch));
	}
	
	@Override
	public int insert(ScheduleInfo sch) {//play表和studio表都不能为空索引
		// TODO Auto-generated method stub
		try {
			String sql = "insert into schedule("+ScheduleInfo.PLAY_ID+","+ScheduleInfo.SCHED_TIME+","+ScheduleInfo.SCHED_PRICE+","+ScheduleInfo.STUDIO_ID+")"
					+ " values('"
					+ sch.getPlay_id()
					+ "', '"
					+ sch.getSched_time()
					+ "', '"
					+ sch.getSchedule_price()
					+ "', '"
					+ sch.getStudio_id()
					+ "' )";
			System.out.println(sql);
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(ScheduleInfo sch) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update schedule set " + ScheduleInfo.PLAY_ID+" ='"+ sch.getPlay_id() + 
					"', " + ScheduleInfo.SCHED_TIME+" = '"+ sch.getSched_time() + 
					"', " + ScheduleInfo.SCHED_PRICE+" = '"+ sch.getSchedule_price() + 
					"', " + ScheduleInfo.STUDIO_ID+" = '"+ sch.getStudio_id() + "' ";

			sql += " where "+ScheduleInfo.SCHED_ID+" = " + sch.getSched_id();
			System.out.println(sql);
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int ID) {
		// TODO Auto-generated method stub
		int rtn=0;		
		try{
			String sql = "delete from  schedule ";
			sql += " where "+ScheduleInfo.SCHED_ID+ " = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<ScheduleInfo> select(String condt) {
		// TODO Auto-generated method stub
		List<ScheduleInfo> scheduleInfos = null;
		scheduleInfos=new LinkedList<ScheduleInfo>();
		try {
			String sql = "select "+ScheduleInfo.SCHED_ID+","+ScheduleInfo.SCHED_PRICE+","+ScheduleInfo.PLAY_ID+","+ScheduleInfo.SCHED_TIME+","
		+ScheduleInfo.STUDIO_ID+ " from schedule";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			System.out.println(sql);
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					ScheduleInfo scheduleInfo=new ScheduleInfo();
					scheduleInfo.setSched_id(rst.getInt(ScheduleInfo.SCHED_ID));
					scheduleInfo.setSched_time(rst.getString(ScheduleInfo.SCHED_TIME));
					scheduleInfo.setStudio_id(rst.getInt(ScheduleInfo.STUDIO_ID));
					scheduleInfo.setPlay_id(rst.getInt(ScheduleInfo.PLAY_ID));
					scheduleInfo.setSchedule_price(rst.getInt(ScheduleInfo.SCHED_PRICE));
					scheduleInfos.add(scheduleInfo);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return scheduleInfos;
	}


	

}
