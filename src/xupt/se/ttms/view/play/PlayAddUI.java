package xupt.se.ttms.view.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class PlayAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblRow, lblColumn, lblIntro,lblplay_price,lblplay_status,lblplay_type,lblplay_lenth;
	protected JTextField txtName, txtRow, txtColumn,txtplay_price,txtplay_status,txtplay_type,txtplay_lenth;
	protected JTextArea txtIntro;
	
	public  PlayAddUI() {
		System.out.println(456);
	
		// TODO Auto-generated constructor stub
//		super();
	}
	

	@Override
	protected void initContent(){
		
		this.setTitle("添加剧目");
		System.out.println(123);
		
		lblName = new JLabel("剧目名称:");
		lblName.setBounds(110, 30, 80, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(200, 30, 400, 30);
		contPan.add(txtName);

		lblRow = new JLabel("导演:");
		lblRow.setBounds(110, 80, 80, 30);
		contPan.add(lblRow);
		txtRow = new JTextField();
		txtRow.setBounds(200, 80, 120, 30);
		contPan.add(txtRow);

		lblColumn = new JLabel("主演:");
		lblColumn.setBounds(390, 80, 80, 30);
		contPan.add(lblColumn);
		txtColumn = new JTextField();
		txtColumn.setBounds(480, 80, 120, 30);
		contPan.add(txtColumn);
		
		lblplay_price = new JLabel("推荐价格");
		lblplay_price.setBounds(110, 130, 80, 30);
		contPan.add(lblplay_price);
		txtplay_price = new JTextField();
		txtplay_price.setBounds(200, 130, 120, 30);
		contPan.add(txtplay_price);
		
		lblplay_status = new JLabel("状态:");
		lblplay_status.setBounds(390,130,80,30);
		contPan.add(lblplay_status);
		txtplay_status = new JTextField();
		txtplay_status.setBounds(480, 130, 120, 30);
		contPan.add(txtplay_status);
		
		lblplay_type = new JLabel("类型:");
		lblplay_type.setBounds(110, 180, 80, 30);
		contPan.add(lblplay_type);
		txtplay_type = new JTextField();
		txtplay_type.setBounds(200, 180, 120, 30);
		contPan.add(txtplay_type);
		
		lblplay_lenth = new JLabel("时长:");
		lblplay_lenth.setBounds(390, 180, 80, 30);
		contPan.add(lblplay_lenth);	
		txtplay_lenth = new JTextField();
		txtplay_lenth.setBounds(480, 180, 120, 30);
		contPan.add(txtplay_lenth);
		
		
		
		
		
		lblIntro = new JLabel("剧目简介:");
		lblIntro.setBounds(110, 230, 80, 30);
		contPan.add(lblIntro);
		txtIntro = new JTextArea();
		txtIntro.setBounds(200, 230, 400, 100);
		contPan.add(txtIntro);

		
		btnSave = new JButton("保存");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSaveClicked();
			}
		});
		btnSave.setBounds(110, 350, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(230, 350, 60, 30);
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
//		protected JTextField txtName, txtRow, txtColumn,txtplay_price,txtplay_status,txtplay_type,txtplay_lenth;
//		protected JTextArea txtIntro;
		if (txtName.getText() != null ) {
			
			
			PlayService playService = new PlayService();
			PlayInfo playInfo=new PlayInfo();
			playInfo.setPlay_name(txtName.getText());
			playInfo.setPlay_director(txtRow.getText());
			playInfo.setPlay_protagonist(txtColumn.getText());
			playInfo.setPlay_price(Float.parseFloat(txtplay_price.getText()));
			playInfo.setPlay_status(txtplay_status .getText());
			playInfo.setPlay_type(txtplay_type .getText());
			playInfo.setPlay_lenth(Integer.parseInt(txtplay_lenth .getText()));
			playInfo.setPlay_introduce(txtIntro.getText());
			

	
			
			playService.add(playInfo);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
