package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.Studio;

public interface iPlayInfoDao {
	public int insert(PlayInfo play);
	public int update(PlayInfo play);
	public int delete(int ID);
	public List<PlayInfo> select(String condt);
}
