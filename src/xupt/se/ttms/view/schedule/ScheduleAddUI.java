package xupt.se.ttms.view.schedule;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.ScheduleService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class ScheduleAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕
	private String[]studioList=new String[1];
	private String[]playList=new String[1];
	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblRow, lblColumn, lblIntro;
	protected JTextField txtName, txtRow, txtColumn,txtIntro;
	protected JComboBox jComboBox,jComboBox2;
	protected JLabel jLabel,jLabel2,jLabel3,jLabel4,jLabel5;
	protected JTextField jTextField,jTextField2,jTextField3;
	public  ScheduleAddUI() {
		System.out.println(456);
		// TODO Auto-generated constructor stub
//		super();
	}
	

	@Override
	protected void initContent(){
		int aa=1;
		this.setTitle("演出计划管理");
		System.out.println(123);
		jLabel = new JLabel("厅名:");
		jLabel.setBounds(0, 0, 100, 40);
		jLabel.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jLabel);
//		String str[] = {"苗姐","杜戈洋","李赛","管雪","小明","苏浛"};
		jComboBox = new JComboBox();
		jComboBox.setBounds(100, 0, 100, 40);
		//jComboBox.setForeground(Color.blue);
		jComboBox.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jComboBox);
		
		jLabel2 = new JLabel("影名:");
		jLabel2.setBounds(0, 50, 100, 40);
		jLabel2.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jLabel2);
		String str2[] = {"哈哈","嘿嘿"};
		jComboBox2 = new JComboBox();
		jComboBox2.setBounds(100, 50, 100, 40);
		jComboBox2.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jComboBox2);
		
		jLabel3 = new JLabel("上映时间:");
		jLabel3.setBounds(0, 100, 100, 40);
		jLabel3.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jLabel3);
		jTextField = new JTextField();
		jTextField.setBounds(100, 100, 400,40);
		contPan.add(jTextField);
		
		jLabel4 = new JLabel("价格:");
		jLabel4.setBounds(0, 150, 100, 40);
		jLabel4.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jLabel4);
		jTextField2 = new JTextField();
		jTextField2.setBounds(100,150 , 400, 40);
		contPan.add(jTextField2);
		
		jLabel5 = new JLabel("日期:");
		jLabel5.setBounds(0, 200, 100, 40);
		jLabel5.setFont(new Font("宋体", Font.BOLD, 20));
		contPan.add(jLabel5);
		jTextField3 = new JTextField();
		jTextField3.setBounds(100,200,400,40);
		contPan.add(jTextField3);

		
		btnSave = new JButton("保存");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSaveClicked();
			}
		});
		btnSave.setBounds(250, 280, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(450, 280, 60, 30);
		contPan.add(btnCancel);

		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"files/imgs/pencil.jpg").getImage());
		imageJP.setBounds(200, 160, 100, 100);
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
	
	
	public String[] getStudioList() {
		return studioList;
	}


	public void setStudioList(String[] studioList) {
		this.studioList = studioList;
		jComboBox.removeAllItems();
		for(int i=0;i<studioList.length;i++)
			jComboBox.addItem(studioList[i]);
		System.out.println(studioList.length+"[[");
		invalidate();
		
	}


	public String[] getPlayList() {
		return playList;
	}


	public void setPlayList(String[] playList) {
		this.playList = playList;
		jComboBox2.removeAllItems();
		for(int i=0;i<playList.length;i++)
			jComboBox2.addItem(playList[i]);
		invalidate();
	}


	protected void btnSaveClicked(){
		
		if (jTextField3.getText() != null && jTextField2.getText() != null) {
			
			
			ScheduleService scheduleService = new ScheduleService();
			ScheduleInfo scheduleInfo=new ScheduleInfo();
			
			StudioSrv studioSrv=new StudioSrv();
			System.out.println(studioList[jComboBox.getSelectedIndex()]);
			List <Studio>list=studioSrv.Fetch("studio_name = '"+studioList[jComboBox.getSelectedIndex()]+"'");
			
			Studio studio=list.get(0);
			
			PlayService playService=new PlayService();
			List <PlayInfo>plist=playService.Fetch("play_name = '"+playList[jComboBox2.getSelectedIndex()]+"'");
			PlayInfo playInfo=plist.get(0);
			
			scheduleInfo.setSchedule_price(Float.parseFloat(jTextField2.getText()));
			scheduleInfo.setSched_time(jTextField3.getText());
			scheduleInfo.setStudio_id(studio.getID());
			scheduleInfo.setPlay_id(playInfo.getPlay_id());
			scheduleService.add(scheduleInfo);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
