package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Order;

public interface iOrderDao {
	public int insert(Order ord);
	public int update(Order ord);
	public int delete(int ID);
	public List<Order> select(String condt);
}
