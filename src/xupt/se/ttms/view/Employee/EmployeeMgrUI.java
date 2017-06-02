package xupt.se.ttms.view.Employee;

import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.lang.model.type.TypeKind;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.util.List;
import java.util.Iterator;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.tmpl.*;

class EmployeeTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public EmployeeTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("姓名");
		tabModel.addColumn("电话");
		tabModel.addColumn("地址");
		tabModel.addColumn("邮箱");
		tabModel.addColumn("职位");
		tabModel.addColumn("用户名");
		tabModel.addColumn("密码");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    //隐藏ID这一列
        TableColumn column = columnModel.getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);

        column = columnModel.getColumn(1);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(4);
        column.setPreferredWidth(20);        
        column = columnModel.getColumn(5);
        column.setPreferredWidth(20);  

        column = columnModel.getColumn(6);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        
        column = columnModel.getColumn(7);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Employee getEmployee() {
		int rowSel=jt.getSelectedRow();
//		tabModel.addColumn("id");
//		tabModel.addColumn("姓名");
//		tabModel.addColumn("电话");
//		tabModel.addColumn("地址");
//		tabModel.addColumn("邮箱");
//		tabModel.addColumn("职位");
//		tabModel.addColumn("用户名");
//		tabModel.addColumn("密码");
		if(rowSel>=0){
			Employee emp = new Employee();
			emp.setEmpId(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			emp.setEmpName(jt.getValueAt(rowSel, 1).toString());
			emp.setEmpTel(jt.getValueAt(rowSel, 2).toString());
			emp.setEmpAdress(jt.getValueAt(rowSel, 3).toString());
			emp.setEmpEmail(jt.getValueAt(rowSel, 4).toString());
			emp.setEmpType(jt.getValueAt(rowSel, 5).toString());
			emp.setEmpUserName(jt.getValueAt(rowSel, 6).toString());
			emp.setEmpPassword(jt.getValueAt(rowSel, 7).toString());

			return emp;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showEmployeeList(List<Employee> employees) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

//			tabModel.addColumn("id");
//			tabModel.addColumn("姓名");
//			tabModel.addColumn("电话");
//			tabModel.addColumn("地址");
//			tabModel.addColumn("邮箱");
//			tabModel.addColumn("职位");
//			tabModel.addColumn("用户名");
//			tabModel.addColumn("密码");
			Iterator<Employee> itr = employees.iterator();
			while (itr.hasNext()) {
				Employee employee = itr.next();
				Object data[] = new Object[8];
				data[0] = employee.getEmpId()+"";
				data[1] = employee.getEmpName();
				data[2] = employee.getEmpTel();
				data[3] = employee.getEmpAdress();
				data[4] = employee.getEmpEmail();
				data[5]=employee.getEmpType();
				data[6]=employee.getEmpUserName();
				data[7]=employee.getEmpPassword();
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class EmployeeMgrUI extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;
	
	EmployeeTable tms; //显示演出厅列表


	public EmployeeMgrUI() {
		super();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("员工管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入职员名字:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);

		input = new JTextField();
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new JButton("查找");
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnAdd = new JButton("添加");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		
		tms = new EmployeeTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		EmployeeAddUI addStuUI=null;
		
		addStuUI = new EmployeeAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加员工");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Employee emp = tms.getEmployee();
		if(null== emp){
			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
			return; 
		}
		EmployeeEditUI modStuUI = new EmployeeEditUI(emp);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改员工");
		modStuUI.initData(emp);
		modStuUI.toFront();
		modStuUI.setModal(true);
		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modStuUI.setVisible(true);

		if (modStuUI.getReturnStatus()) {
			showTable();
		}	
	}

	private void btnDelClicked() {
		Employee emp = tms.getEmployee();
		if(null== emp){
			JOptionPane.showMessageDialog(null, "请选择要删除的员工");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			EmployeeService empService = new EmployeeService();
			empService.delete(emp.getEmpId());
			showTable();
		}
	}

	private void btnQueryClicked() {
		if (!input.getText().equals("")) {
			//请自行补充

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<Employee> employees = new EmployeeService().FetchAll();
		
		tms.showEmployeeList(employees);
	}
	

	public static void main(String[] args) {
		EmployeeMgrUI frmStuMgr = new EmployeeMgrUI();
		frmStuMgr.setVisible(true);

	}
}
