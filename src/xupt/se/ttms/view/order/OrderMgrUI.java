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
import xupt.se.ttms.model.Order;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.SeatInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.OrderService;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.ScheduleService;
import xupt.se.ttms.service.SeatService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketService;
import xupt.se.ttms.view.tmpl.*;

class OrderTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public OrderTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
//		private int order_id;
//		private int employeeId;
//		private int ticket_id;
//		private float order_price;
//		private String order_date;
//		private float sale_payment;
//		private float sale_change;
//		private int ticket_id;
//		private int play_id;
//		private String seat_id;
//		private int schedule_id;
//		private String ticket_date;
		tabModel.addColumn("id");
		tabModel.addColumn("售票员");
		tabModel.addColumn("电影名称");
		tabModel.addColumn("影厅名称");
		tabModel.addColumn("座位号");
		tabModel.addColumn("时间");
		tabModel.addColumn("支付金额");
		tabModel.addColumn("找零");
		tabModel.addColumn("订单金额");
		tabModel.addColumn("订单时间");
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
        column.setPreferredWidth(20); 
        column = columnModel.getColumn(8);
        column.setPreferredWidth(20); 
        column = columnModel.getColumn(9);
        column.setPreferredWidth(50); 
		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Order getOrder() {
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
			Order order = new Order();
			order.setOrder_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
//			play.setPlay_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
//			play.setPlay_name(jt.getValueAt(rowSel, 1).toString());
//			play.setPlay_director(jt.getValueAt(rowSel, 2).toString());
//			play.setPlay_protagonist(jt.getValueAt(rowSel, 3).toString());
//			play.setPlay_status(jt.getValueAt(rowSel, 4).toString());
//			play.setPlay_type(jt.getValueAt(rowSel, 5).toString());
//			play.setPlay_lenth(Integer.parseInt(jt.getValueAt(rowSel, 6).toString()));
//			play.setPlay_introduce(jt.getValueAt(rowSel, 7).toString());
//			emp.setEmpId(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
//			emp.setEmpName(jt.getValueAt(rowSel, 1).toString());
//			emp.setEmpTel(jt.getValueAt(rowSel, 2).toString());
//			emp.setEmpAdress(jt.getValueAt(rowSel, 3).toString());
//			emp.setEmpEmail(jt.getValueAt(rowSel, 4).toString());
//			emp.setEmpType(jt.getValueAt(rowSel, 5).toString());
//			emp.setEmpUserName(jt.getValueAt(rowSel, 6).toString());
//			emp.setEmpPassword(jt.getValueAt(rowSel, 7).toString());

			return order;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showOrderList(List<Order> orders) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);


			Iterator<Order> itr = orders.iterator();
			while (itr.hasNext()) {
			
				Order order = itr.next();
				String ticketIDs=order.getTicket_id();
				String [] ticketId=ticketIDs.split(",");
				String seatInfo="";
				EmployeeService employeeService=new EmployeeService();
				List<Employee>employees=employeeService.FetchId(order.getEmployeeId());
				Employee employee=employees.get(0);
				TicketService ticketService=new TicketService();
				SeatService seatService=new SeatService();
				int studioId;
				int playId = 0;
				int scheduleId = 0;
				for(int i=0;i<ticketId.length;i++)
				{
					
					List<Ticket>tickets=ticketService.FetchId(Integer.parseInt(ticketId[i]));
					Ticket ticket=tickets.get(0);
					
					
					List<SeatInfo> seat=seatService.FetchId(ticket.getSeat_id());
					SeatInfo realSeat=seat.get(0);
					seatInfo+=(realSeat.getSeat_row()+"排"+realSeat.getSeat_column()+"座 ");
					studioId=realSeat.getStudio_id();
					playId=ticket.getPlay_id();
					scheduleId=ticket.getSchedule_id();
				}
				
				
				PlayService playService=new PlayService();
				List<PlayInfo>playInfos=playService.FetchId(playId);
				PlayInfo playInfo=playInfos.get(0);
				
				ScheduleService scheduleService=new ScheduleService();
				List<ScheduleInfo>scheduleInfos=scheduleService.FetchId(scheduleId);
				ScheduleInfo scheduleInfo=scheduleInfos.get(0);
				
				StudioSrv studioSrv=new StudioSrv();
				List<Studio>studios=studioSrv.FetchId(scheduleInfo.getStudio_id());
				Studio studio=studios.get(0);
				
				Object data[] = new Object[10];
//				tabModel.addColumn("id");
//				tabModel.addColumn("售票员");
//				tabModel.addColumn("电影名称");
//				tabModel.addColumn("影厅名称");
//				tabModel.addColumn("座位号");
//				tabModel.addColumn("时间");
				data[0] = order.getOrder_id()+"";
				data[1] = employee.getEmpName();
				data[2] = playInfo.getPlay_name();
				data[3] = studio.getName();
				data[4] = seatInfo;
				data[5]=scheduleInfo.getSched_time();
				Order realOrder=new OrderService().FetchId(order.getOrder_id()).get(0);
				data[6]=realOrder.getSale_payment()+"";
				data[7]=realOrder.getSale_change()+"";
				data[8]=realOrder.getOrder_price()+"";
				data[9]=realOrder.getOrder_date();
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class OrderMgrUI extends MainUITmpl {
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
	
	OrderTable tms; //显示演出厅列表


	public OrderMgrUI() {
		super();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("订单管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		


		btnDel = new JButton("退票");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		
		tms = new OrderTable(jsc);
		
		showTable();
	}

//	private void btnAddClicked() {
//
//		PlayAddUI addStuUI=null;
//		
//		addStuUI = new PlayAddUI();
//		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		addStuUI.setWindowName("添加剧目");
//		addStuUI.toFront();
//		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		addStuUI.setVisible(true);
//		if (addStuUI.getReturnStatus()) {
//			showTable();
//		}
//	}
//
//	private void btnModClicked() {
//		PlayInfo emp = tms.getPlay();
//		if(null== emp){
//			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
//			return; 
//		}
//		PlayEditUI modStuUI = new PlayEditUI(emp);
//		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		modStuUI.setWindowName("修改员工");
//		modStuUI.initData(emp);
//		modStuUI.toFront();
//		modStuUI.setModal(true);
//		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		modStuUI.setVisible(true);
//
//		if (modStuUI.getReturnStatus()) {
//			showTable();
//		}	
//	}

	private void btnDelClicked() {
		Order order = tms.getOrder();
		if(null== order){
			JOptionPane.showMessageDialog(null, "请选择要退的订单");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认退票？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			OrderService orderService = new OrderService();
			Order thisOrder=orderService.FetchId(order.getOrder_id()).get(0);
			System.out.println(thisOrder.getTicket_id());
			String[] tickets=thisOrder.getTicket_id().split(",");
			TicketService ticketService=new TicketService();
			SeatService seatService=new SeatService();
			
			for(int i=0;i<tickets.length;i++)
			{
				Ticket ticket=ticketService.FetchId(Integer.parseInt(tickets[i])).get(0);
				ticketService.delete(ticket.getTicket_id());
				seatService.delete(ticket.getSeat_id());
			}
			orderService.delete(order.getOrder_id());
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
		List<Order> playInfos = new OrderService().FetchAll();
		
		tms.showOrderList(playInfos);
	}
	

	public static void main(String[] args) {
		OrderMgrUI frmStuMgr = new OrderMgrUI();
		frmStuMgr.setVisible(true);

	}
}
