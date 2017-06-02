package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;

public interface iTicketDao {
	public int insert(Ticket ticket);
	public int update(Ticket ticket);
	public int delete(int ID);
	public List<Ticket> select(String condt); 
}
