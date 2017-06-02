package xupt.se.ttms.idao;
import xupt.se.ttms.dao.*;

public class DAOFactory {
	public static iStudioDAO creatStudioDAO(){
		return new StudioDAO();
	}
	public static iSeatDao creatSeatDao(){
		return new SeatDao();
	}
	public static iEmployeeDao creatEmployeeDao(){
		return new EmployeeDao();
	}
	public static iPlayInfoDao creatPlayInfoDao(){
		return new PlayInfoDao();
	}
	public static iScheduleDao creatScheduleDao(){
		return new ScheduleDao();
	}
	public static iOrderDao creatOrderDao(){
		return new OrderDao();
	}
	public static iTicketDao creatTicketDao(){
		return new TicketDao();
	}
}
