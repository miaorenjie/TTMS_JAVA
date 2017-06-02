package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.SeatInfo;
import xupt.se.ttms.model.Studio;

public interface iSeatDao {
	public int insert(SeatInfo seat);
	public int update(SeatInfo seat);
	public int delete(int ID);
	public List<SeatInfo> select(String condt); 

}
