package xupt.se.ttms.view.system;
//界面初步
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.Employee.EmployeeMgrUI;
import xupt.se.ttms.view.order.OrderMgrUI;
import xupt.se.ttms.view.play.PlayMgrUI;
import xupt.se.ttms.view.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.sellticket.SellTicketSchedule;
import xupt.se.ttms.view.sellticket.SellTicketUI;
import xupt.se.ttms.view.studio.*;
import xupt.se.ttms.view.tmpl.LoginView;

public class MainUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel panel2;
	public JButton button1,button2,button3,button4,button5,button6,button7 ,button8,button9;
	public JButton Login;
	private Employee loginUser;
	public static MainUI MainUI;
	@SuppressWarnings("deprecation")
	public MainUI() {
		super();
		MainUI=this;
		Toolkit tk=Toolkit.getDefaultToolkit();    
		Image img=tk.getImage("高清.jpg");          
		setIconImage(img);
		this.setTitle("万达影城管理系统");
		this.setSize(1200, 730);
		this.setResizable(false);
		this.setLocation(80,20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		JPanel panel1,panel3;
		
		loginUser=LoginedUser.getInstance().getEmployee();
		
		panel1 = new JPanel();//左侧栏
		panel2 = new JPanel();//内容区
		panel2.setBounds(0,0,1024,700);
		panel2.setLayout(null);
		
		//panel2.setBackground(Color.BLACK);
		
		//panel2.setLocation(50,50);
		panel3 = new JPanel(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        ImageIcon img = new ImageIcon("星空.jpg");
		        g.drawImage(img.getImage(),0,0,null);
		         }
		      };//上栏//背景图
		
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, panel1, panel2);
		
		splitPane1.setDividerLocation(168);// 设置splitPane1的分隔线位置
		
		splitPane1.setOneTouchExpandable(false);//设置JSplitPane是否可以展开或收起
		splitPane1.setDividerSize(1);// 设置分隔线宽度的大小，以pixel为计算单位。
		splitPane1.setEnabled(false);  //固定分割线
		
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, panel3, splitPane1);
		splitPane2.setDividerLocation(100);
		splitPane2.setOneTouchExpandable(false);
		splitPane2.setDividerSize(1);
		splitPane1.setEnabled(false); 
		
	
		
		contentPane.add(splitPane2);
//上		
		panel3.setLocation(0,0);
		panel3.setSize(1200,100);
		
		panel3.setLayout(null);
	
		
		button3 = new JButton("关于我们");
		button2 = new JButton("电影资讯");
		button1 = new JButton("首页");
//		button1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				btnSaveClicked();
//			}
			
			
				// TODO Auto-generated method stub
//				}
//			);
		
		panel3.add(button1);
		panel3.add(button2);
		panel3.add(button3);
		
		button1.setText("首页");
		button1.setBounds(10, 70,60, 20);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.addActionListener(this);
		
		
		button2.setText("电影资讯");
		button2.setBounds(80, 70, 86, 20);
		//button2.setLocation(5,28);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.addActionListener(this);

		
		button3.setText("关于我们");
		button3.setBounds(180,70, 86, 20);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.addActionListener(this);

//左		
		panel1.setLocation(0,35);
		panel1.setSize(35,600);
		panel1.setLayout(null);
		//panel1.setBackground(Color.cyan);//背景颜色
		
		button4 = new JButton();
		button5 = new JButton();
		button6 = new JButton();
		button7 = new JButton();
		button8 = new JButton();
		button9 = new JButton();


		panel1.add(button4);
		panel1.add(button5);
		panel1.add(button6);
		panel1.add(button7);
		panel1.add(button8);
		panel1.add(button9);

		
		button4.setText("员工管理");
		button4.setBounds(10, 30,100, 20);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button4.addActionListener(this);

		
		button5.setText("演出厅管理");
		button5.setBounds(10, 80, 100, 20);
		button5.setContentAreaFilled(false);
		button5.setBorderPainted(false);
		button5.addActionListener(this);
		
		button6.setText("剧目管理");
		button6.setBounds(10, 130, 100, 20);
		button6.setContentAreaFilled(false);
		button6.setBorderPainted(false);
		button6.addActionListener(this);

		
		button7.setText("售票管理");
		button7.setBounds(10, 180, 100,20);
		button7.setContentAreaFilled(false);
		button7.setBorderPainted(false);
		button7.addActionListener(this);

		
		button8.setText("销售管理");
		button8.setBounds(10, 230, 100, 20);
		button8.setContentAreaFilled(false);
		button8.setBorderPainted(false);
		button8.addActionListener(this);
		
		button9.setText("演出计划");
		button9.setBounds(10, 280, 100, 20);
		button9.setContentAreaFilled(false);
		button9.setBorderPainted(false);
		button9.addActionListener(this);

		
//登陆，注册
		JLabel sname = new JLabel("用户名称：");
		sname.setBounds(1050,10,100,20);
		panel3.add(sname);
		
		JLabel name = new JLabel("用户名");
		name.setBounds(1120,10,100,20);
		name.setText(loginUser.getEmpName());
		panel3.add(name);
		
		JLabel sjob = new JLabel("用户职称：");		
		sjob.setBounds(1050,40,100,20);
//		sjob.setText(LoginedUser.getInstance().getEmployee().getEmpName());
		panel3.add(sjob);
		
		JLabel job = new JLabel("职称");
		job.setBounds(1120,40,100,20);
		job.setText(loginUser.getEmpType());
		panel3.add(job);
		
		
		
		Login = new JButton("退出");
		panel3.add(Login);
		//Login.setText("登陆");
		Login.setBounds(1120, 70, 60, 20);
		Login.setContentAreaFilled(false);
		Login.setBorderPainted(false);
		Login.addActionListener(this);

		
//		SetCos = new JButton("注册");
//		panel3.add(SetCos);
		//SetCos.setText("注册");
//		SetCos.setBounds(1120, 70, 60, 20);
//		SetCos.setContentAreaFilled(false);
//		SetCos.setBorderPainted(false);
		
//内容区
		
			
		
		this.show();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new MainUI();
	}

	//@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
//		if (e.getSource()==button1) {//首页
			
//		}	
		
//		if (e.getSource()==button2) {//资讯
			
//		}
		
//		if (e.getSource()==button3) {//关于我们
			
//		}
		
//		if (e.getSource()==Login) {//登陆
		//panel3,
//		}
		
		if (e.getSource()==button4) {//员工
			if(loginUser.getEmpType().equals("售票员"))
			{
				JOptionPane.showMessageDialog(null, "你没有权限进行此操作！");
				return;
			}
			panel2.removeAll();
			EmployeeMgrUI b=new EmployeeMgrUI();
			b.setBounds(0,0,1024,700);
			b.setLayout(null);
			panel2.add(b);
			panel2.invalidate();
			panel2.repaint();
		}

		if (e.getSource()==button5) {//演出厅
		
			if(loginUser.getEmpType().equals("售票员"))
			{
				JOptionPane.showMessageDialog(null, "你没有权限进行此操作！");
				return;
			}
			panel2.removeAll();
			
			StudioMgrUI b=new StudioMgrUI();
			b.setBounds(0,0,1024,700);
			b.setLayout(null);
			panel2.add(b);
			panel2.invalidate();
			panel2.repaint();
		}
		
		if (e.getSource()==button6) {//剧目
			if(loginUser.getEmpType().equals("售票员"))
			{
				JOptionPane.showMessageDialog(null, "你没有权限进行此操作！");
				return;
			}
			System.out.println(123);
			panel2.removeAll();
			
			PlayMgrUI b=new PlayMgrUI();
			b.setBounds(0,0,1024,700);
			b.setLayout(null);
			panel2.add(b);
			panel2.invalidate();
			panel2.repaint();
			
			
		}

		if (e.getSource()==button7) {//售票
			System.out.println(123);
			panel2.removeAll();
			
			SellTicketSchedule b=new SellTicketSchedule();
			b.setBounds(0,0,1024,700);
			b.setLayout(null);
			panel2.add(b);
			panel2.invalidate();
			panel2.repaint();
		}
		
		if (e.getSource()==button8) {//销售
			panel2.removeAll();
			
			OrderMgrUI b=new OrderMgrUI();
			b.setBounds(0,0,1024,700);
			b.setLayout(null);
			panel2.add(b);
			panel2.invalidate();
			panel2.repaint();
		}

		if (e.getSource()==button9) {//演出计划
	//		System.out.println(123);
			if(loginUser.getEmpType().equals("售票员"))
			{
				JOptionPane.showMessageDialog(null, "你没有权限进行此操作！");
				return;
			}
			panel2.removeAll();
			
			ScheduleMgrUI b=new ScheduleMgrUI();
			b.setBounds(0,0,1024,700);
			b.setLayout(null);
			panel2.add(b);
			panel2.invalidate();
			panel2.repaint();
		}
		if(e.getSource()==Login)
		{
			MainUI.setVisible(false);
			LoginView.win.setVisible(true);
		}

	
	}
		

	
}

