package xupt.se.ttms.view.sellticket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import xupt.se.ttms.model.Order;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.SeatInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.OrderService;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.ScheduleService;
import xupt.se.ttms.service.SeatService;
import xupt.se.ttms.service.SellTicketService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketService;
import xupt.se.ttms.view.system.MainUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;

public class SellTicketUI extends MainUITmpl {

	private static final long serialVersionUID = -8069838656058091382L;
	private JTabbedPane tabPane;
	private JPanel salePanel;
	private JPanel upPanel;
	private JPanel leftPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	private JButton left;
	private JButton right;
	
	private PlayInfo curPlay;
	private ScheduleInfo curSchedule;
	private DefaultMutableTreeNode curNode;
	private List<PlayInfo> scheduledPlay;
	private JTree tree;
	private JTextArea detail;
	private int[][] ticketArray;
	private int[][] seats;
	private int row,column;
	private ArrayList<SeatInfo>chooseSeatInfo;
	private JPanel sites;
	private JLabel lmainview;
	public static void main(String[] args) {
//		new SellTicketUI().setVisible(true);;

	}
	public SellTicketUI(){}
	
	public SellTicketUI(ScheduleInfo scheduleInfo) {
		// TODO Auto-generated constructor stub
		curSchedule=scheduleInfo;
		System.out.println(curSchedule.getSched_time());
	}
	@Override
	protected void initContent() {
		tabPane = new JTabbedPane();
		tabPane.setBounds(0, 0, 1024, 590);

		salePanel = new JPanel();
		salePanel.setLayout(new BorderLayout());

		
		
		setUpPanel();
//		if(scheduledPlay.size()>0){
////			setLeftPanel(scheduledPlay.get(0).getId(),scheduledPlay.get(0).getName());
//			curPlay = scheduledPlay.get(0);
//		}
//		else
//			setLeftPanel(0,"【无信息】");
		
		setRightPanel();

		tabPane.addTab("正在上映", salePanel);
		tabPane.addTab("即将上映", new JLabel());
		tabPane.addTab("全部电影", new JLabel());
		contPan.add(tabPane);
		contPan.validate();
		initData();
		setMiddlePanel(row, column);

	}
	private void initData()
	{
		int m=0, n=0;
    	ScheduleService scheduleService=new ScheduleService();
    	List<ScheduleInfo>scheduleInfos=scheduleService.FetchAll();
    	chooseSeatInfo=new ArrayList<>();
    	TicketService ticketService=new TicketService();
    	
    	Ticket ticket=SellTicketService.getChooseInfo();
    	ScheduleInfo schedule = null;
    	
    	for(int i=0;i<scheduleInfos.size();i++)
    	{
    		if(scheduleInfos.get(i).getSched_id()==ticket.getSchedule_id())
    			schedule=scheduleInfos.get(i);
    	}
//    	curSchedule = scheduleInfos.get(1);
    	curSchedule=schedule;
    	System.out.println(schedule.getSched_id()+"--");
    	StudioSrv studioSrv=new StudioSrv();
    	Studio studio=studioSrv.FetchId(schedule.getStudio_id()).get(0);
    	SeatService seatService=new SeatService();
    	List<Ticket>tickets=ticketService.Fetch(Ticket.PLAY_ID+" = "+schedule.getPlay_id()+" AND "+Ticket.SCHEDULE_ID+" = "+schedule.getSched_id());
//    	List<Ticket>tickets=new ArrayList<>();
    	m=studio.getRowCount();
    	n=studio.getColCount();
//    	m=6;
//    	n=10;
    	seats=new int[m+1][n+1];
    	ticketArray=new int[m+1][n+1];
//    	for(int i=0;i<m+1;i++)
//    		for(int j=0;j<n+1;j++)
//    		{
//    			seats[i][j]=0;
//    			ticketArray[i][j]=0;
//    		}
   
    	for(int i=0;i<tickets.size();i++)
    	{
    		Ticket temp=tickets.get(i);
    		SeatInfo seatInfo=seatService.FetchId(temp.getSeat_id()).get(0);
    		seats[seatInfo.getSeat_row()][seatInfo.getSeat_column()]=9;
    		ticketArray[seatInfo.getSeat_row()][seatInfo.getSeat_column()]=9;
    	}
    	row=m;
    	column=n;
    	   	
	}

	private void setUpPanel() {
		PlayService service = new PlayService();
//		scheduledPlay = service.selectScheduledPlay("");
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		left = new JButton(new ImageIcon("resource/image/left.png"));
		upPanel.add(left, BorderLayout.WEST);
		right = new JButton(new ImageIcon("resource/image/right.png"));
		upPanel.add(right, BorderLayout.EAST);
		JPanel filmPanel = new JPanel();
		filmPanel.setLayout(new GridLayout(1, 6));
		JButton btn1 = new JButton(new ImageIcon("resource/image/film1.jpg"));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scheduledPlay.size()>0){
//					setLeftPanel(scheduledPlay.get(0).getId(),scheduledPlay.get(0).getName());
					curPlay = scheduledPlay.get(0);
				}
			}
		});
		filmPanel.add(btn1);
		
		JButton btn2 = new JButton(new ImageIcon("resource/image/film2.jpg"));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scheduledPlay.size()>1){
//					setLeftPanel(scheduledPlay.get(1).getId(),scheduledPlay.get(1).getName());
					curPlay = scheduledPlay.get(1);
				}
			}
		});
		filmPanel.add(btn2);

		filmPanel.add(new JButton(new ImageIcon("resource/image/film3.jpg")));
		filmPanel.add(new JButton(new ImageIcon("resource/image/film1.jpg")));
		filmPanel.add(new JButton(new ImageIcon("resource/image/film2.jpg")));
		filmPanel.add(new JButton(new ImageIcon("resource/image/film3.jpg")));
		upPanel.add(filmPanel, BorderLayout.CENTER);
		salePanel.add(upPanel, BorderLayout.NORTH);
	}

	private void setLeftPanel(int play_id, String play_name) {
//		if(leftPanel==null)
//			leftPanel = new JPanel();
//		else
//			leftPanel.removeAll();
//		DefaultMutableTreeNode root = new DefaultMutableTreeNode(play_name);
//		ScheduleService service = new ScheduleService();
//		List<ScheduleInfo> list = service.Fetch("play_id="+play_id);
//		if (list.size() > 0) {
//			List<String> dates = new ArrayList<String>();
//			for (Schedule i : list) {
//				String s = DateFormat.getDateInstance().format(i.getSched_time());
//				if(!dates.contains(s)){
//					dates.add(s);
//				}
//			}
//			for(String i:dates){
//				root.add(new DefaultMutableTreeNode(i));									
//			}
//			for (Schedule i : list) {
//				DefaultMutableTreeNode node = (DefaultMutableTreeNode)root.getFirstChild();
//				for(int j=0; j<dates.size(); j++){
//					if(node.getUserObject().toString().equals(DateFormat.getDateInstance().format(i.getSched_time()))){
//						node.add(new DefaultMutableTreeNode(i));
//						break;
//					}else
//						node = node.getNextSibling();
//				}
//			}
//		}
//		tree = new JTree(root);
//		tree.addTreeSelectionListener(new TreeSelectionListener() {
//			
//		    public void valueChanged(TreeSelectionEvent e) {		    	
//		        DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  
//		        curNode = selectedNode;
//		        getTickets(selectedNode);
//		    }  
//		});  
//		leftPanel.add(tree);
//		salePanel.add(leftPanel, BorderLayout.WEST);
//		leftPanel.updateUI();
	}
	
	private void getTickets(DefaultMutableTreeNode node){
        if(node!=null && node.isLeaf()){
        	
        	//System.out.println(schedule.getSched_id());
//        	TicketService ticketSrv = new TicketService();
////        	SeatSrv seatSrv = new SeatSrv();
//        	StudioSrv studioSrv = new StudioSrv();
//        	List<Ticket> tickets = ticketSrv.Fetch("sched_id = "+ schedule.getSched_id());
//        	for(Ticket t : tickets){
//        		List<Seat> tmp = seatSrv.Fetch("seat_id = " + t.getSeatId());
//        		if(tmp.size()>0){
//        			t.setPlayName(curPlay.getName());
//        			t.setSchedule(curSchedule);
//        			t.setSeat(tmp.get(0));
//        			if(m==0){
//        				List<Studio> studios = studioSrv.Fetch("studio_id = " + tmp.get(0).getStudioId());
//        				if(studios.size()>0){
//        					m = studios.get(0).getRowCount();
//        					n = studios.get(0).getColCount();
//        				}
//        			}
//        			if(handler.isTicketSelected(t)){
//        				t.setStatus(2);
//        			}
//        		}
//        	}
        	setMiddlePanel(6, 10); 
        }
	}

	private void setMiddlePanel(int m, int n) {
		if(middlePanel==null)
			middlePanel = new JPanel();
		else
			middlePanel.removeAll();
		
		lmainview = new JLabel();

		ImageIcon selectsite = new ImageIcon("resource/image/selectsite.png");
		lmainview.setIcon(selectsite);

		sites = new JPanel();
		GridLayout gridLayout = new GridLayout(m+1, n+1);
		gridLayout.setHgap(8);
		gridLayout.setVgap(3);
		sites.setLayout(gridLayout);
		sites.setOpaque(false); // 设置背景为透明
		sites.setBounds(105, 120, 510, 300);

		
		final ImageIcon siteimgwhite = new ImageIcon("resource/image/white.png");
		final ImageIcon siteimggreen = new ImageIcon("resource/image/green.png");
		final ImageIcon siteimgred = new ImageIcon("resource/image/red.jpg");

		Action act = new AbstractAction() {
			private static final long serialVersionUID = -144569051730123316L;

			public void actionPerformed(ActionEvent e) {
				JButton site = (JButton) e.getSource();
				String name = site.getName();
				System.out.println(name);
				String tmp[] = name.split(",");
				int i = Integer.valueOf(tmp[0]);
				int j = Integer.valueOf(tmp[1]);
				if(ticketArray[i][j]==0)
				{
					ticketArray[i][j]=2;
					
					site.setIcon(siteimggreen);
//					detail.setText();
				}else if (ticketArray[i][j]==2) {
					ticketArray[i][j]=0;
					site.setIcon(siteimgwhite);
//					handler.removeTicket(ticketAr ray[i][j]);
//					detail.setText(handler.getInfo());
				}
//				if (ticketArray[i][j].getStatus()==0) {
//					ticketArray[i][j].setStatus(2);
//					site.setIcon(siteimggreen);
//					handler.addTicket(ticketArray[i][j]);
//					detail.setText(handler.getInfo());
//				} else if (ticketArray[i][j].getStatus()==2) {
//					ticketArray[i][j].setStatus(0);
//					site.setIcon(siteimgwhite);
//					handler.removeTicket(ticketArray[i][j]);
//					detail.setText(handler.getInfo());
//				}
			}
		};

		// 座位标示   -1:无座, 0:待销售   1:锁定   2:已选   9:卖出
//		int[][] seats = new int[m+1][n+1];
//		ticketArray = new Ticket[m+1][n+1];
		System.out.println(m+","+n);
//		for (int i = 0; i < m+1; i++) {
//			for (int j = 0; j < n+1; j++) {
//				seats[i][j] = 2;
////				ticketArray[i][j] = null;
//			}
//		}
		
//		for(Ticket t : tickets){
//			seats[t.getSeat().getRow()][t.getSeat().getColumn()] = t.getStatus();
//			ticketArray[t.getSeat().getRow()][t.getSeat().getColumn()] = t;
//		}
		
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				if(i==0){
					if(j==0)
						sites.add(new JLabel("  "));
					else
						sites.add(new JLabel(" " + j + "座"));
				}else if(j==0){
					if(i>0)
						sites.add(new JLabel(i + "排"));
				}else{
					
					if (seats[i][j] == -1) {
						sites.add(new JLabel("  "));
					} else if (seats[i][j] == 0) {
						JButton site = new JButton(act);
						site.setBackground(Color.WHITE);
						site.setIcon(siteimgwhite);
						site.setName(i+","+j);
						sites.add(site);
					} else if (seats[i][j] == 2) {
						System.out.println("chulai");
						JButton site = new JButton(act);
						site.setBackground(Color.WHITE);
						site.setIcon(siteimggreen);
						site.setName(i+","+j);
						sites.add(site);
					} else{
						JButton site = new JButton();
						site.setBackground(Color.WHITE);
						site.setIcon(siteimgred);
						sites.add(site);
					}
				}
			}
		}

		lmainview.add(sites);
		middlePanel.add(lmainview);
	
		salePanel.add(middlePanel, BorderLayout.CENTER);
		middlePanel.updateUI();
		
		
	}

	
	private void setRightPanel() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		detail = new JTextArea("");
		JScrollPane scroll = new JScrollPane(detail);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		rightPanel.add(scroll, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		JButton sale = new JButton("出票");
		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(handler.doSale()){
//					detail.setText("");					
//					getTickets(curNode);
//					JOptionPane.showMessageDialog(null, "出票成功。");
//				}else{
//					JOptionPane.showMessageDialog(null, "出现错误，请重试。");					
//				}
				int ticketNum=0;
				for(int i=1;i<=row;i++)
					for(int j=1;j<=column;j++)
						if(ticketArray[i][j]==2)
							ticketNum++;
				String payment;
				float change;
				float order_price=curSchedule.getSchedule_price()*ticketNum;
				int flag=JOptionPane.showConfirmDialog(null, "您所需支付的金额为"+order_price);
				if(flag==JOptionPane.YES_OPTION)
				{
					payment=JOptionPane.showInputDialog("支付金额");
					if(Float.parseFloat(payment)<order_price)
					{
						JOptionPane.showConfirmDialog(null, "支付失败");
						return;
					}
					else{
						change=Float.parseFloat(payment)-order_price;
						JOptionPane.showConfirmDialog(null, "购买成功,找零："+change+"元");
					}
				}
				else {
					return;
				}
					
				
					
				SeatService seatService=new SeatService();
				TicketService ticketService=new TicketService();
				OrderService orderService=new OrderService();
				ArrayList<Integer>ticketIds=new ArrayList<>();
			
				for(int i=1;i<=row;i++)
					for(int j=1;j<=column;j++)
					{
						if(ticketArray[i][j]==2)
						{
							seats[i][j]=9;
							SeatInfo seatInfo=new SeatInfo();
							seatInfo.setMovie_id(curSchedule.getPlay_id());
							seatInfo.setSeat_row(i);
							seatInfo.setSeat_column(j);
							seatInfo.setStudio_id(curSchedule.getSched_id());
							seatService.add(seatInfo);
							List<SeatInfo>seatInfos=seatService.FetchAll();
							int seatId=seatInfos.get(seatInfos.size()-1).getSeat_id();
							
							Ticket ticket=new Ticket();
							ticket.setPlay_id(curSchedule.getPlay_id());
							ticket.setSeat_id(seatId);
							ticket.setSchedule_id(curSchedule.getSched_id());
							Date date=new Date();
		                    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                    String ticket_date=simpleDateFormat.format(date);
							ticket.setTicket_date(ticket_date);
							ticketService.add(ticket);
							List<Ticket>tickets=ticketService.FetchAll();
							int ticketId=tickets.get(tickets.size()-1).getSeat_id();
							ticketIds.add(ticketId);
							ticketNum++;
						}
					}
				Order order=new Order();
				Date date=new Date();
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ticket_date=simpleDateFormat.format(date);
				order.setOrder_date(ticket_date);
				order.setEmployeeId(LoginedUser.getInstance().getEmployee().getEmpId());
				String ticketIdString="";
				for(int i=0;i<ticketIds.size();i++)
				{
					if(i==ticketIds.size()-1)
						ticketIdString=ticketIdString+ticketIds.get(i);
					else
						ticketIdString=ticketIdString+ticketIds.get(i)+",";
				}
				order.setSale_payment(Float.parseFloat(payment));
				order.setSale_change(change);
				order.setOrder_price(order_price);
				order.setTicket_id(ticketIdString);
				orderService.add(order);
				middlePanel.removeAll();
				sites=null;
				lmainview=null;
				salePanel.remove(middlePanel);
//				salePanel.removeAll();
				setMiddlePanel(row, column);
			}
		
		});
		JButton clear = new JButton("返回");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainUI.panel2.removeAll();
				SellTicketSchedule sellTicketSchedule=new SellTicketSchedule();
				MainUI.panel2.add(sellTicketSchedule);
				MainUI.panel2.repaint();
			}
		});
		buttons.add(sale);
		buttons.add(clear);
		rightPanel.add(buttons, BorderLayout.SOUTH);
		salePanel.add(rightPanel, BorderLayout.EAST);
	}
}
