package xupt.se.ttms.view.sellticket;

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

import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.ScheduleService;
import xupt.se.ttms.service.SellTicketService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.system.MainUI;
import xupt.se.ttms.view.tmpl.*;

class ScheduleTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public ScheduleTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("电影名");
		tabModel.addColumn("演出厅名");
		tabModel.addColumn("上映时间");
		tabModel.addColumn("价格");
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
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(4);
        column.setPreferredWidth(500);        

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public ScheduleInfo getScheduleInfo() {
		int rowSel=jt.getSelectedRow();
			
		if(rowSel>=0){
			ScheduleInfo stud = new ScheduleInfo();
			stud.setSched_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setSched_time(jt.getValueAt(rowSel, 3).toString());
			stud.setSchedule_price(Float.parseFloat(jt.getValueAt(rowSel, 4).toString()));
			return stud;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showScheduleList(List<ScheduleInfo> scheduleInfos) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<ScheduleInfo> itr = scheduleInfos.iterator();
			while (itr.hasNext()) {
				ScheduleInfo stu = itr.next();
				Object data[] = new Object[5];
				
				StudioSrv studioSrv=new StudioSrv();
				System.out.println(stu.getStudio_id()+"鹰眼");
				List<Studio>studio=studioSrv.FetchId(stu.getStudio_id());
		
				PlayService playService=new PlayService();
				List<PlayInfo>play=playService.FetchId(stu.getPlay_id());
				
				data[0] = Integer.toString(stu.getSched_id());
				data[1] = play.get(0).getPlay_name();
				data[2] = studio.get(0).getName();
				data[3] = stu.getSched_time();
				data[4] = stu.getSchedule_price()+"";
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class SellTicketSchedule extends MainUITmpl {
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
	
	ScheduleTable tms; //显示演出厅列表


	public SellTicketSchedule() {
		super();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("请选择演出计划", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入演出计划名称:", JLabel.RIGHT);
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

		btnAdd = new JButton("购买");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		
		contPan.add(ca1);
		
		tms = new ScheduleTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {
		
		ScheduleInfo scheduleInfo = tms.getScheduleInfo();
		if(scheduleInfo==null)
		{
			JOptionPane.showMessageDialog(null, "请选择要购买的演出计划");
			return; 
		}
		MainUI.panel2.removeAll();
		ScheduleService scheduleService=new ScheduleService();
		scheduleInfo=scheduleService.FetchId(scheduleInfo.getSched_id()).get(0);
		SellTicketService.getChooseInfo().setSchedule_id(scheduleInfo.getSched_id());
		SellTicketUI sellTicketUI=new SellTicketUI();
		MainUI.panel2.add(sellTicketUI);
		MainUI.panel2.repaint();
//		ScheduleAddUI addStuUI=null;
//		StudioSrv studioSrv=new StudioSrv();
//		List<Studio>studios=studioSrv.FetchAll();
//		String []studioName=new String[studios.size()];
//		for(int i=0;i<studios.size();i++)
//			studioName[i]=studios.get(i).getName();
//		System.out.println(studios.size()+"..");
//		
//		PlayService playService=new PlayService();
//		List<PlayInfo>playInfos=playService.FetchAll();
//		String []playName=new String[playInfos.size()];
//		for(int i=0;i<playInfos.size();i++)
//			playName[i]=playInfos.get(i).getPlay_name();
//		
//		addStuUI = new ScheduleAddUI();
//		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		addStuUI.setPlayList(playName);
//		addStuUI.setStudioList(studioName);
//		System.out.println(studioName.length+"--");
//		addStuUI.setWindowName("添加演出计划");
//		addStuUI.toFront();
//		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		addStuUI.setVisible(true);
//		if (addStuUI.getReturnStatus()) {
//			showTable();
//		}
	}

	private void btnModClicked() {
//		ScheduleInfo scheduleInfo = tms.getScheduleInfo();
//		if(null== scheduleInfo){
//			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
//			return; 
//		}
//		
//		StudioSrv studioSrv=new StudioSrv();
//		List<Studio>studios=studioSrv.FetchAll();
//		String []studioName=new String[studios.size()];
//		for(int i=0;i<studios.size();i++)
//			studioName[i]=studios.get(i).getName();
//		System.out.println(studios.size()+"..");
//		
//		PlayService playService=new PlayService();
//		List<PlayInfo>playInfos=playService.FetchAll();
//		String []playName=new String[playInfos.size()];
//		for(int i=0;i<playInfos.size();i++)
//			playName[i]=playInfos.get(i).getPlay_name();
//		
//		ScheduleEditUI modStuUI = new ScheduleEditUI(scheduleInfo);
//		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		modStuUI.setWindowName("修改演出厅");
//		modStuUI.initData(scheduleInfo);
//		modStuUI.setPlayList(playName);
//		modStuUI.setStudioList(studioName);
//		modStuUI.toFront();
//		modStuUI.setModal(true);
//		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		modStuUI.setVisible(true);
//
//		if (modStuUI.getReturnStatus()) {
//			showTable();
//		}	
	}

	private void btnDelClicked() {
//		Studio stud = tms.getStudio();
//		if(null== stud){
//			JOptionPane.showMessageDialog(null, "请选择要删除的演出厅");
//			return; 
//		}		
//		
//		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
//		if (confirm == JOptionPane.YES_OPTION) {
//			StudioSrv stuSrv = new StudioSrv();
//			stuSrv.delete(stud.getID());
//			showTable();
//		}
	}

	private void btnQueryClicked() {
		if (!input.getText().equals("")) {
			//请自行补充

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<ScheduleInfo> stuList = new ScheduleService().FetchAll();
		
		tms.showScheduleList(stuList);
	}
	

	public static void main(String[] args) {
//		ScheduleMgrUI frmStuMgr = new ScheduleMgrUI();
//		frmStuMgr.setVisible(true);

	}
}
