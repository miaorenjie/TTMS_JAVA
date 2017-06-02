package xupt.se.ttms.view.play;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.log.Log;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeService;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.StudioAddUI;;

public class PlayEditUI extends PlayAddUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayInfo playInfo;

//	public static void main(String[] args) {
//		Studio stu=new Studio();
//		new StudioEditUI(stu);
//		System.out.println("123");
//	}
	public PlayEditUI(PlayInfo play){
		
//		initContent();
//		initData(stu);
	}
	
	public void initData(PlayInfo play) {
		if(null== play){
			return;
		}
//		private JLabel lblName, lblRow, lblColumn, lblIntro,lblplay_price,lblplay_status,lblplay_type,lblplay_lenth;
//		protected JTextField txtName, txtRow, txtColumn,txtplay_price,txtplay_status,txtplay_type,txtplay_lenth;
//		protected JTextArea txtIntro;
//		System.out.println(emp.getName());
		txtName.setText(play.getPlay_name());
		txtRow.setText(play.getPlay_director());
		txtColumn.setText(play.getPlay_protagonist());
		txtplay_price.setText(play.getPlay_price()+"");
		txtplay_status.setText(play.getPlay_status());
		txtplay_type.setText(play.getPlay_type());
		txtplay_lenth.setText(play.getPlay_lenth()+"");
		txtIntro.setText(play.getPlay_introduce());
		
	
//		txttel.setText(Integer.toString(stu.getRowCount()));
//		txtRow.invalidate();
//		txtemail.setText(Integer.toString(stu.getColCount()));
//		txtColumn.invalidate();
//		txttype.setText(stu.getIntroduction());
//		txtadress.setText(stu.getIntroduction());
//		txtIntro.invalidate();
		playInfo=play;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null ) {
			PlayService playService = new PlayService();
			PlayInfo play= playInfo;
			
			playInfo.setPlay_name(txtName.getText());
			playInfo.setPlay_director(txtRow.getText());
			playInfo.setPlay_protagonist(txtColumn.getText());
			playInfo.setPlay_price(Float.parseFloat(txtplay_price.getText()));
			playInfo.setPlay_status(txtplay_status .getText());
			playInfo.setPlay_type(txtplay_type .getText());
			playInfo.setPlay_lenth(Integer.parseInt(txtplay_lenth .getText()));
			playInfo.setPlay_introduce(txtIntro.getText());
			
			
				
			playService.modify(play);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}
