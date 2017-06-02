package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iPlayInfoDao;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

public class PlayInfoDao implements iPlayInfoDao{

	public static void main(String[] args) {
		PlayInfo playInfo=new PlayInfo();
		playInfo.setPlay_id(2);
		playInfo.setPlay_director("4456");
		new PlayInfoDao().insert(playInfo);
		List <PlayInfo>playInfos=new PlayInfoDao().select("");
		for(int i=0;i<playInfos.size();i++)
		{
			System.out.println(playInfos.get(i).getPlay_id());
		}
		new PlayInfoDao().update(playInfo);
	}
	@Override
	public int insert(PlayInfo play) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into play(play_name, play_introduction, "
					+ "play_length,play_ticket_price,play_status,play_director,play_protagonist ,play_type)"
					+ " values('"
					+ play.getPlay_name()
					+ "', '"
					+ play.getPlay_introduce()
					+ "', '"
					+ play.getPlay_lenth()
					+ "', '"
					+ play.getPlay_price()
					+ "', '"
					+ play.getPlay_status()
					+ "', '"
					+ play.getPlay_director()
					+ "',' "
					+ play.getPlay_protagonist()
					+ "', '"
					+ play.getPlay_type()
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
	public int update(PlayInfo play) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update play set " + PlayInfo.INTRODUCE+" ='"+ play.getPlay_introduce() + 
					"', " + PlayInfo.DIRECTOR+" = '"+ play.getPlay_director() + 
					"', " + PlayInfo.LENTH+" = '"+ play.getPlay_lenth() + 
					"', "+ PlayInfo.NAME+" = '"+ play.getPlay_name()
					+ "', " + PlayInfo.PRICE+" = '"+ play.getPlay_price() 
					+ "', " + PlayInfo.PROTAGONIST+" = '"+ play.getPlay_protagonist() 
					+ "', " + PlayInfo.STATUS+" = '"+ play.getPlay_status() 
					+ "', " + PlayInfo.TYPE+" = '"+ play.getPlay_type() + "' ";

			sql += " where "+PlayInfo.ID+" = " + play.getPlay_id();
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
			String sql = "delete from  play ";
			sql += " where "+PlayInfo.ID+ " = " + ID;
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
	public List<PlayInfo> select(String condt) {
		// TODO Auto-generated method stub
		List<PlayInfo> playInfos = null;
		playInfos=new LinkedList<PlayInfo>();
		try {
			String sql = "select "+PlayInfo.ID+","+PlayInfo.INTRODUCE+","+PlayInfo.DIRECTOR+","
		+PlayInfo.LENTH+","+PlayInfo.NAME+","+PlayInfo.PRICE+","
					+PlayInfo.PROTAGONIST+","+PlayInfo.STATUS+","+PlayInfo.TYPE+ " from play ";
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
					PlayInfo playInfo=new PlayInfo();
					playInfo.setPlay_id(rst.getInt(PlayInfo.ID));
					playInfo.setPlay_director(rst.getString(PlayInfo.DIRECTOR));
					playInfo.setPlay_introduce(rst.getString(PlayInfo.INTRODUCE));
					playInfo.setPlay_lenth(rst.getInt(PlayInfo.LENTH));
					playInfo.setPlay_name(rst.getString(PlayInfo.NAME));
					playInfo.setPlay_price(rst.getFloat(PlayInfo.PRICE));
					playInfo.setPlay_protagonist(rst.getString(PlayInfo.PROTAGONIST));
					playInfo.setPlay_status(rst.getString(PlayInfo.STATUS));
					playInfo.setPlay_type(rst.getString(PlayInfo.TYPE));
					
					playInfos.add(playInfo);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return playInfos;
	}

}
