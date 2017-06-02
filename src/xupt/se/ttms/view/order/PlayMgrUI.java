package xupt.se.ttms.view.order;

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
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.tmpl.*;

class PlayTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public PlayTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
//		private String play_name;
//		private String play_director;
//		private String play_introduce;
//	   
//		private String play_protagonist;
//		private float play_price;
//		private String play_status;
//		private String play_type;
//		private int play_lenth;
		tabModel.addColumn("id");
		tabModel.addColumn("剧目名称");
		tabModel.addColumn("导演");
		tabModel.addColumn("主演");
		tabModel.addColumn("上映状态");
		tabModel.addColumn("影片类型");
		tabModel.addColumn("时长");
		tabModel.addColumn("简介");
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
        column.setPreferredWidth(20);  
        
        column = columnModel.getColumn(7);
        column.setPreferredWidth(100);  

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public PlayInfo getPlay() {
		int rowSel=jt.getSelectedRow();
//		tabModel.addColumn("id");
//		tabModel.addColumn("剧目名称");
//		tabModel.addColumn("导演");
//		tabModel.addColumn("主演");
//		tabModel.addColumn("上映状态");
//		tabModel.addColumn("影片类型");
//		tabModel.addColumn("时长");
//		tabModel.addColumn("简介");
		if(rowSel>=0){
			PlayInfo play = new PlayInfo();
			play.setPlay_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			play.setPlay_name(jt.getValueAt(rowSel, 1).toString());
			play.setPlay_director(jt.getValueAt(rowSel, 2).toString());
			play.setPlay_protagonist(jt.getValueAt(rowSel, 3).toString());
			play.setPlay_status(jt.getValueAt(rowSel, 4).toString());
			play.setPlay_type(jt.getValueAt(rowSel, 5).toString());
			play.setPlay_lenth(Integer.parseInt(jt.getValueAt(rowSel, 6).toString()));
			play.setPlay_introduce(jt.getValueAt(rowSel, 7).toString());
//			emp.setEmpId(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
//			emp.setEmpName(jt.getValueAt(rowSel, 1).toString());
//			emp.setEmpTel(jt.getValueAt(rowSel, 2).toString());
//			emp.setEmpAdress(jt.getValueAt(rowSel, 3).toString());
//			emp.setEmpEmail(jt.getValueAt(rowSel, 4).toString());
//			emp.setEmpType(jt.getValueAt(rowSel, 5).toString());
//			emp.setEmpUserName(jt.getValueAt(rowSel, 6).toString());
//			emp.setEmpPassword(jt.getValueAt(rowSel, 7).toString());

			return play;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showPlayList(List<PlayInfo> playInfos) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

//			tabModel.addColumn("id");
//			tabModel.addColumn("剧目名称");
//			tabModel.addColumn("导演");
//			tabModel.addColumn("主演");
//			tabModel.addColumn("上映状态");
//			tabModel.addColumn("影片类型");
//			tabModel.addColumn("时长");
//			tabModel.addColumn("简介");
			Iterator<PlayInfo> itr = playInfos.iterator();
			while (itr.hasNext()) {
				PlayInfo playInfo = itr.next();
				Object data[] = new Object[8];
				data[0] = playInfo.getPlay_id()+"";
				data[1] = playInfo.getPlay_name();
				data[2] = playInfo.getPlay_director();
				data[3] = playInfo.getPlay_protagonist();
				data[4] = playInfo.getPlay_status();
				data[5]=playInfo.getPlay_type();
				data[6]=playInfo.getPlay_lenth();
				data[7]=playInfo.getPlay_introduce();
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class PlayMgrUI extends MainUITmpl {
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
	
	PlayTable tms; //显示演出厅列表


	public PlayMgrUI() {
		super();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("剧目管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入剧目名字:", JLabel.RIGHT);
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
		
		tms = new PlayTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		PlayAddUI addStuUI=null;
		
		addStuUI = new PlayAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加剧目");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		PlayInfo emp = tms.getPlay();
		if(null== emp){
			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
			return; 
		}
		PlayEditUI modStuUI = new PlayEditUI(emp);
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
		PlayInfo play = tms.getPlay();
		if(null== play){
			JOptionPane.showMessageDialog(null, "请选择要删除的员工");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			PlayService empService = new PlayService();
			empService.delete(play.getPlay_id());
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
		List<PlayInfo> playInfos = new PlayService().FetchAll();
		
		tms.showPlayList(playInfos);
	}
	

	public static void main(String[] args) {
		PlayMgrUI frmStuMgr = new PlayMgrUI();
		frmStuMgr.setVisible(true);

	}
}
