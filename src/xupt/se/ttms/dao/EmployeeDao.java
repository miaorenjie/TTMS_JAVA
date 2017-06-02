package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iEmployeeDao;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

public class EmployeeDao implements iEmployeeDao{

	public static void main(String[] args) {
		Employee employee=new Employee();
		employee.setEmpAdress("1");
		employee.setEmpEmail("1");
		employee.setEmpName("3");
		employee.setEmpPassword("1");
		employee.setEmpTel("1");
		employee.setEmpType("1");
		employee.setEmpUserName("1");
		employee.setEmpId(1);
		new EmployeeDao().insert(employee);
		List <Employee>employees=new EmployeeDao().select("");
		for(int i=0;i<employees.size();i++)
		{
			System.out.println(employees.get(i).getEmpName());
		}
	}
	@Override
	public int insert(Employee emp) {
		// TODO Auto-generated method stub
		String sql = "insert into employee( emp_name, emp_tel_num, emp_addr, emp_email, emp_userName,"
				+ " emp_passWord,emp_type)"
				+ " values('"
				+ emp.getEmpName()
				+ "', '"
				+ emp.getEmpTel()
				+ "', '" 
				+ emp.getEmpAdress()
				+ "', '" 
				+ emp.getEmpEmail()
				+ "', '" 
				+ emp.getEmpUserName()
				+ "', '" 
				+ emp.getEmpPassword()
				+ "', '" 
				+ emp.getEmpType()
				+ "' )";
		System.out.println(sql);
		DBUtil db = new DBUtil();
		db.openConnection();
		ResultSet rst;
		try {
			rst = db.getInsertObjectIDs(sql);
			db.close(rst);
			db.close();
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	@Override
	public int update(Employee emp) {
		// TODO Auto-generated method stub
		
		int rtn=0;
		try {
			String sql = "update employee set " + " emp_name ='"
					+ emp.getEmpName() + "', " 
					+ " emp_tel_num = '"
					+ emp.getEmpTel() + "', " 
					+ " emp_addr = '"
					+ emp.getEmpAdress() + "', "
					+ " emp_email ='"
					+ emp.getEmpEmail() + "',"
					+ " emp_userName ='"
					+ emp.getEmpUserName() + "',"
					+ " emp_passWord = '"
					+ emp.getEmpPassword() + "',"
					+ " emp_type = '"
					+ emp.getEmpType() + "' ";

			sql += " where emp_id = " + emp.getEmpId();
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
			String sql = "delete from  employee ";
			sql += " where emp_id = " + ID;
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
	public List<Employee> select(String condt) {
		// TODO Auto-generated method stub
		List<Employee> empList = null;
		empList=new LinkedList<Employee>();
		try {
			String sql = "select emp_id,emp_name, emp_tel_num,emp_addr,emp_email,emp_userName,"
				+ "emp_passWord,emp_type from employee ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Employee emp=new Employee();
					emp.setEmpId(rst.getInt("emp_id"));
					emp.setEmpAdress(rst.getString("emp_addr"));
					emp.setEmpEmail(rst.getString(Employee.EMAIL));
					emp.setEmpName(rst.getString(Employee.NAME));
					emp.setEmpTel(rst.getString(Employee.TEL));
					emp.setEmpUserName(Employee.USERNAME);
					emp.setEmpPassword(rst.getString(Employee.PASSWORD));
					emp.setEmpType(rst.getString(Employee.TYPE));
					empList.add(emp);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return empList;
	}

}
