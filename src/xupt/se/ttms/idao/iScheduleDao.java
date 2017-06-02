package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.ScheduleInfo;

public interface iScheduleDao {
	public int insert(ScheduleInfo sch);
	public int update(ScheduleInfo sch);
	public int delete(int ID);
	public List<ScheduleInfo> select(String condt); 
}
