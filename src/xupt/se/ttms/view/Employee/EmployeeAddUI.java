package xupt.se.ttms.view.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class EmployeeAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lbeid,lbename,lbeuname,lbepass,lbeadress,lbetel,lbeemail,lbetype;
	protected JTextField txtname, txtid, txtUname,txtpass,txttel,txtemail,txttype;
	protected JTextArea txtadress;
	
	public  EmployeeAddUI() {
		System.out.println(456);
		// TODO Auto-generated constructor stub
//		super();
	}
	

	@Override
	protected void initContent(){
		
		this.setTitle("添加员工信息");
		System.out.println(123);
		lbeid = new JLabel("ID:");
		lbeid.setBounds(110, 30, 80, 30);
		contPan.add(lbeid);
		txtid = new JTextField();
		txtid.setBounds(200, 30, 120, 30);
		contPan.add(txtid);

		lbename = new JLabel("姓名:");
		lbename.setBounds(390, 30, 80, 30);
		contPan.add(lbename);
		txtname = new JTextField();
		txtname.setBounds(480, 30, 120, 30);
		contPan.add(txtname);

		lbeuname = new JLabel("用户名:");
		lbeuname.setBounds(110, 80, 80, 30);
		contPan.add(lbeuname);
		txtUname = new JTextField();
		txtUname.setBounds(200, 80, 120, 30);
		contPan.add(txtUname);
		
		lbepass = new JLabel("密码:");
		lbepass.setBounds(390, 80, 80, 30);
		contPan.add(lbepass);
		txtpass = new JTextField();
		txtpass.setBounds(480, 80, 120, 30);
		contPan.add(txtpass);
		
		lbetel = new JLabel("电话:");
		lbetel.setBounds(110, 130, 80, 30);
		contPan.add(lbetel);
		txttel = new JTextField();
		txttel.setBounds(200, 130, 120, 30);
		contPan.add(txttel);
		
		lbeemail = new JLabel("E-mail:");
		lbeemail.setBounds(390, 130, 80, 30);
		contPan.add(lbeemail);
		txtemail = new JTextField();
		txtemail.setBounds(480, 130, 120, 30);
		contPan.add(txtemail);
		
		lbetype = new JLabel("类型:");
		lbetype.setBounds(110, 180, 80, 30);
		contPan.add(lbetype);
		txttype = new JTextField();
		txttype.setBounds(200, 180, 120, 30);
		contPan.add(txttype);
		
		lbeadress = new JLabel("地址:");
		lbeadress.setBounds(390, 180, 80, 30);
		contPan.add(lbeadress);
		txtadress = new JTextArea();
		txtadress.setBounds(480, 180, 200, 100);
		contPan.add(txtadress);

		
		btnSave = new JButton("保存");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSaveClicked();
			}
		});
		btnSave.setBounds(60, 280, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 280, 60, 30);
		contPan.add(btnCancel);

		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"files/imgs/pencil.jpg").getImage());
		imageJP.setBounds(360, 160, 100, 100);
		imageJP.setLayout(null);
		this.add(imageJP);

		
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			System.out.println("添加成功");
			btnSaveClicked();
			
		}
		System.out.println("点击了");
	}
	
	protected void btnSaveClicked(){
//		protected JTextField txtname, txtid, txtUname,txtpass,txttel,txtemail,txttype;
//		protected JTextArea txtadress;
		if (txtname.getText() != null && txtUname.getText() != null
				&& txtpass.getText() != null) {
			
			
			EmployeeService empService = new EmployeeService();
			Employee emp=new Employee();
			emp.setEmpName(txtname.getText());
			emp.setEmpAdress(txtadress.getText());
			emp.setEmpPassword(txtpass.getText());
			emp.setEmpUserName(txtUname.getText());
			emp.setEmpTel(txttel.getText());
			emp.setEmpType(txttype.getText());
			emp.setEmpEmail(txtemail.getText());
			
			empService.add(emp);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
