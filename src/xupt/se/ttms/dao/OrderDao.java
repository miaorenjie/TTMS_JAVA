package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iOrderDao;
import xupt.se.ttms.model.Order;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.util.DBUtil;

public class OrderDao implements iOrderDao{
	public static void main(String[] args) {
		Order ord=new Order();
		ord.setEmployeeId(2);
		new OrderDao().insert(ord);
		List<Order>orders=new OrderDao().select("");
		for(int i=0;i<orders.size();i++)
		{
			System.out.println(orders.get(i).getOrder_id());
		}
//		System.out.println(new ScheduleDao().insert(sch));
	}

	@Override
	public int insert(Order ord) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into sale("+Order.EMPLOYEE_ID+","
		+Order.ORDER_DATE+","
					+Order.SALE_PAYMENT+","
		+Order.SALE_CHANGE+","
					+Order.TICKET_ID+","
		+Order.ORDER_PRICE+")"
					+ " values('"
					+ ord.getEmployeeId()
					+ "', '"
					+ ord.getOrder_date()
					+ "', '"
					+ ord.getSale_payment()
					+ "', '"
					+ ord.getSale_change()
					+ "', '"
					+ ord.getTicket_id()
					+ "', '"
					+ ord.getOrder_price()
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
	public int update(Order ord) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update sale set " + Order.EMPLOYEE_ID+" ='"+ ord.getEmployeeId() + 
					"', " + Order.ORDER_DATE+" = '"+ ord.getOrder_date() + 
					"', " + Order.SALE_CHANGE+" = '"+ ord.getSale_change() + 
					"', " + Order.TICKET_ID+" = '"+ ord.getTicket_id() + 
					"', " + Order.ORDER_PRICE+" = '"+ ord.getOrder_price() + 
					"', " + Order.SALE_PAYMENT+" = '"+ ord.getSale_payment()+ "' ";

			sql += " where "+Order.ORDER_ID+" = " + ord.getOrder_id();
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
			String sql = "delete from  sale ";
			sql += " where "+Order.ORDER_ID+ " = " + ID;
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
	public List<Order> select(String condt) {
		// TODO Auto-generated method stub
		List<Order> orders = null;
		orders=new LinkedList<Order>();
		try {
			String sql = "select "+Order.ORDER_ID+","
		+Order.EMPLOYEE_ID+","
		+Order.ORDER_DATE+","
		+Order.SALE_PAYMENT+","
		+Order.SALE_CHANGE+","
		+Order.ORDER_PRICE+","
		+Order.TICKET_ID+ " from sale";
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
					Order order=new Order();
					order.setOrder_id(rst.getInt(Order.ORDER_ID));
					order.setOrder_date(rst.getString(Order.ORDER_DATE));
					order.setOrder_price(rst.getFloat(Order.ORDER_PRICE));
					order.setEmployeeId(rst.getInt(Order.EMPLOYEE_ID));
					order.setSale_change(rst.getFloat(Order.SALE_CHANGE));
					order.setSale_payment(rst.getFloat(Order.SALE_PAYMENT));
					order.setTicket_id(rst.getInt(Order.TICKET_ID));
					orders.add(order);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return orders;
	}

}
