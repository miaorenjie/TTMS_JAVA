package xupt.se.ttms.service;

import java.util.ArrayList;
import java.util.List;

import xupt.se.ttms.model.Ticket;

public class SellTicketService {
	private static Ticket chooseInfo;
	
	public static Ticket getChooseInfo() {
		if(chooseInfo==null)
			chooseInfo=new Ticket();
		return chooseInfo;
	}
	
	public static void main(String[] args) {
		Ticket ticket=new Ticket();
		ticket.setPlay_id(1);
		ticket.setSchedule_id(1);
		List<Ticket> tickets=getThisSessionTicket(ticket);
		System.out.println(ticket);
		for(int i=0;i<tickets.size();i++)
		{
			System.out.println(tickets.get(i).getTicket_date());
		}
	}
	
	public static boolean isThisSession(Ticket thisSession,Ticket jugeSession)
	{
		if(thisSession.getPlay_id()==jugeSession.getPlay_id()&&thisSession.getSchedule_id()==jugeSession.getSchedule_id())
			return true;
		return false;
	}
	
	public static List<Ticket>getThisSessionTicket(Ticket thisSession)
	{
		List<Ticket>tickets=new TicketService().Fetch(Ticket.PLAY_ID+" = "+thisSession.getPlay_id()+" and "+Ticket.SCHEDULE_ID+" = "+thisSession.getSchedule_id());
		return tickets;
	}
	
}
