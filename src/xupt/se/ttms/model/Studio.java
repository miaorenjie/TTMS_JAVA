package xupt.se.ttms.model;

import xupt.se.util.DBUtil;

public class Studio {
	private int id=0      ; 
	private String name="" ;
	private int rowCount=0;
	private int colCount=0;
	private String introduction=""; //���
	
	public static final String STUDIO_ID="studio_id";
	public static final String STUDIO_NAME="studio_name";
	public static final String STUDIO_ROW="studio_row_count";
	public static final String STUDIO_COL="studio_col_count";
	public static final String STUDIO_INTRO="studio_introduction";
//	String sql = "update studio set " + " studio_name ='"
//			+ stu.getName() + "', " + " studio_row_count = "
//			+ stu.getRowCount() + ", " + " studio_col_count = "
//			+ stu.getColCount() + ", " + " studio_introduction = '"
//			+ stu.getIntroduction() + "' ";
//
//	sql += " where studio_id = " + stu.getID();
//	DBUtil db = new DBUtil();
	public void setID(int ID){
		this.id=ID;
	}
	
	public int getID(){
		return id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setRowCount(int count){
		this.rowCount=count;
	}
	
	public int getRowCount(){
		return rowCount;
	}
	public void setColCount(int count){
		this.colCount=count;
	}
	
	public int getColCount(){
		return colCount;
	}
	
	public void setIntroduction(String intro){
		this.introduction=intro;
	}
	
	public String getIntroduction(){
		return introduction;
	}	
	
}
