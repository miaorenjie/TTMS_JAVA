package xupt.se.ttms.view.studio;


import javax.swing.JOptionPane;

import com.mysql.jdbc.log.Log;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.StudioAddUI;;

public class StudioEditUI extends StudioAddUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Studio stud;

//	public static void main(String[] args) {
//		Studio stu=new Studio();
//		new StudioEditUI(stu);
//		System.out.println("123");
//	}
	public StudioEditUI(Studio stu){
		
//		initContent();
//		initData(stu);
	}
	
	public void initData(Studio stu) {
		if(null== stu){
			return;
		}
		System.out.println(stu.getName());
		txtName.setText(stu.getName());
		txtName.invalidate();
		System.out.println(txtName.getText()+"--");
		txtRow.setText(Integer.toString(stu.getRowCount()));
		txtRow.invalidate();
		txtColumn.setText(Integer.toString(stu.getColCount()));
		txtColumn.invalidate();
		txtIntro.setText(stu.getIntroduction());
		txtIntro.invalidate();
		stud=stu;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			StudioSrv stuSrv = new StudioSrv();
			Studio stu= stud;
			stu.setName(txtName.getText());
			stu.setRowCount(Integer.parseInt(txtRow.getText()));
			stu.setColCount(Integer.parseInt(txtColumn.getText()));
			stu.setIntroduction(txtIntro.getText());
			stuSrv.modify(stu);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}
