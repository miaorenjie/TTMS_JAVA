package xupt.se.ttms.view.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.ScheduleService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class ScheduleAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblRow, lblColumn, lblIntro;
	protected JTextField txtName, txtRow, txtColumn,txtIntro;
	
	public  ScheduleAddUI() {
		System.out.println(456);
		// TODO Auto-generated constructor stub
//		super();
	}
	

	@Override
	protected void initContent(){
		int aa=1;
		this.setTitle("演出厅管理");
		System.out.println(123);
		lblName = new JLabel("影厅名称:");
		lblName.setBounds(140, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(230, 30, 400, 30);
		contPan.add(txtName);

		lblRow = new JLabel("影片名称:");
		lblRow.setBounds(140, 80, 80, 30);
		contPan.add(lblRow);
		txtRow = new JTextField();
		txtRow.setBounds(230, 80, 120, 30);
		contPan.add(txtRow);

		lblColumn = new JLabel("放映时间:");
		lblColumn.setBounds(420, 80, 80, 30);
		contPan.add(lblColumn);
		txtColumn = new JTextField();
		txtColumn.setBounds(510, 80, 120, 30);
	
		contPan.add(txtColumn);
		
		lblIntro = new JLabel("票价:");
		lblIntro.setBounds(140, 130, 80, 30);
		contPan.add(lblIntro);
		txtIntro = new JTextField();
		txtIntro.setBounds(230, 130, 120, 30);
		contPan.add(txtIntro);

		
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
	
	protected void btnSaveClicked(){
		
		if (txtName.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			
			
			ScheduleService scheduleService = new ScheduleService();
			ScheduleInfo scheduleInfo=new ScheduleInfo();
			scheduleInfo.setSched_id(sched_id);
			stuSrv.add(stu);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
