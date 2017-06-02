package xupt.se.ttms.view.Employee;

import javax.swing.JOptionPane;

import com.mysql.jdbc.log.Log;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.StudioAddUI;;

public class EmployeeEditUI extends EmployeeAddUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Employee employee;

//	public static void main(String[] args) {
//		Studio stu=new Studio();
//		new StudioEditUI(stu);
//		System.out.println("123");
//	}
	public EmployeeEditUI(Employee emp){
		
//		initContent();
//		initData(stu);
	}
	
	public void initData(Employee emp) {
		if(null== emp){
			return;
		}
//		protected JTextField txtname, txtid, txtUname,txtpass,txttel,txtemail,txttype;
//		protected JTextArea txtadress;
//		System.out.println(emp.getName());
		txtname.setText(emp.getEmpName());
		txttel.setText(emp.getEmpTel());
		txtemail.setText(emp.getEmpEmail());
		txttype.setText(emp.getEmpType());
		txtadress.setText(emp.getEmpAdress());
	
//		txttel.setText(Integer.toString(stu.getRowCount()));
//		txtRow.invalidate();
//		txtemail.setText(Integer.toString(stu.getColCount()));
//		txtColumn.invalidate();
//		txttype.setText(stu.getIntroduction());
//		txtadress.setText(stu.getIntroduction());
//		txtIntro.invalidate();
		employee=emp;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtname.getText() != null ) {
			EmployeeService empService = new EmployeeService();
			Employee emp= employee;
			emp.setEmpName(txtname.getText());
			emp.setEmpAdress(txtadress.getText());
//			emp.setEmpPassword(txtpass.getText());
//			emp.setEmpUserName(txtUname.getText());
			emp.setEmpTel(txttel.getText());
			emp.setEmpType(txttype.getText());
			emp.setEmpEmail(txtemail.getText());
			
				
			empService.modify(emp);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}
