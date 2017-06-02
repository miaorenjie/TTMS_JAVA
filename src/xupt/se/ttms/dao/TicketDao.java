package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iTicketDao;
import xupt.se.ttms.model.Order;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDao implements iTicketDao{

	@Override
	public int insert(Ticket ticket) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into ticket("+Ticket.PLAY_ID+","
		+Ticket.SCHEDULE_ID+","
					+Ticket.SEAT_ID+","
		+Ticket.TICKET_DATE+","
		+Ticket.TICKET_ID+")"
					+ " values('"
					+ ticket.getPlay_id()
					+ "', '"
					+ ticket.getSchedule_id()
					+ "', '"
					+ ticket.getSeat_id()
					+ "', '"
					+ ticket.getTicket_date()
					+ "', '"
					+ ticket.getTicket_id()
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
	public int update(Ticket ticket) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update sale ticket " + Ticket.PLAY_ID+" ='"+ ticket.getPlay_id() + 
					"', " + Ticket.SCHEDULE_ID+" = '"+ ticket.getSchedule_id() + 
					"', " + Ticket.SEAT_ID+" = '"+ ticket.getSeat_id() + 
					"', " + Ticket.TICKET_DATE+" = '"+ ticket.getTicket_date()+ "' ";

			sql += " where "+Ticket.TICKET_ID+" = " + ticket.getTicket_id();
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
			String sql = "delete from ticket ";
			sql += " where "+Ticket.TICKET_ID+ " = " + ID;
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
	public List<Ticket> select(String condt) {
		// TODO Auto-generated method stub
		List<Ticket> tickets = null;
		tickets=new LinkedList<Ticket>();
		try {
			String sql = "select "+Ticket.PLAY_ID+","
		+Ticket.SCHEDULE_ID+","
		+Ticket.SEAT_ID+","
		+Ticket.TICKET_DATE+","
		+Order.TICKET_ID+ " from ticket";
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
					Ticket ticket=new Ticket();
					ticket.setPlay_id(rst.getInt(Ticket.PLAY_ID));
					ticket.setTicket_date(rst.getString(Ticket.TICKET_DATE));
					ticket.setSeat_id(rst.getString(Ticket.SEAT_ID));
					ticket.setSchedule_id(rst.getInt(Ticket.SCHEDULE_ID));
					ticket.setTicket_id(rst.getInt(Ticket.TICKET_ID));
				
					tickets.add(ticket);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return tickets;
	}

}
