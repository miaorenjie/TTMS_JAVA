package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSeatDao;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.SeatInfo;
import xupt.se.util.DBUtil;

public class SeatDao implements iSeatDao{
	
	public static void main(String[] args) {
		SeatInfo seat=new SeatInfo();
		seat.setSeat_column(1);
		seat.setSeat_row(1);
		seat.setStudio_id(1);
		new SeatDao().insert(seat);
	}

	@Override
	public int insert(SeatInfo seat) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into seat("+SeatInfo.STUDIO_ID+","+SeatInfo.SEAT_ROW+","+SeatInfo.SEAT_COLUMN+","+SeatInfo.MOVIE_ID+")"
					+ " values('"
					+ seat.getStudio_id()
					+ "', '"
					+ seat.getSeat_row()
					+ "', '"
					+ seat.getSeat_column()
					+ "', '"
					+ seat.getMovie_id()
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
	public int update(SeatInfo seat) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update seat set " + SeatInfo.STUDIO_ID+" ='"+ seat.getStudio_id() + 
					"', " + SeatInfo.SEAT_ROW+" = '"+ seat.getSeat_row() + 
					"', " + SeatInfo.MOVIE_ID+" = '"+ seat.getMovie_id() + 
					"', " + SeatInfo.SEAT_COLUMN+" = '"+ seat.getSeat_column()+ "' ";

			sql += " where "+PlayInfo.ID+" = " + seat.getSeat_id();
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
			String sql = "delete from  seat ";
			sql += " where "+SeatInfo.SEAT_ID+ " = " + ID;
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
	public List<SeatInfo> select(String condt) {
		// TODO Auto-generated method stub
		List<SeatInfo> seatInfos = null;
		seatInfos=new LinkedList<SeatInfo>();
		try {
			String sql = "select "+SeatInfo.SEAT_ID+","+SeatInfo.SEAT_ROW+","+SeatInfo.STUDIO_ID+","+SeatInfo.MOVIE_ID+","
		+SeatInfo.SEAT_COLUMN+ " from seat";
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
					SeatInfo seatInfo=new SeatInfo();
					seatInfo.setSeat_id(rst.getInt(SeatInfo.SEAT_ID));
					seatInfo.setSeat_row(rst.getInt(SeatInfo.SEAT_ROW));
					seatInfo.setStudio_id(rst.getInt(SeatInfo.STUDIO_ID));
					seatInfo.setSeat_column(rst.getInt(SeatInfo.SEAT_COLUMN));
					seatInfo.setMovie_id(rst.getInt(SeatInfo.MOVIE_ID));
					seatInfos.add(seatInfo);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return seatInfos;
	}
	

}
