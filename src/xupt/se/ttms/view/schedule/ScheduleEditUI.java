package xupt.se.ttms.view.schedule;


import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.log.Log;

import xupt.se.ttms.model.PlayInfo;
import xupt.se.ttms.model.ScheduleInfo;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.PlayService;
import xupt.se.ttms.service.ScheduleService;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.studio.StudioAddUI;;

public class ScheduleEditUI extends ScheduleAddUI{
	/**
	 * 
	 */
	private String []playList;
	private String[]studioList;
	private static final long serialVersionUID = 1L;
	private ScheduleInfo scheduleInfo;
	private String playName;
	private String studioName;

//	public static void main(String[] args) {
//		Studio stu=new Studio();
//		new StudioEditUI(stu);
//		System.out.println("123");
//	}
	public ScheduleEditUI(ScheduleInfo stu){
		
//		initContent();
//		initData(stu);
	}
	
	public void initData(ScheduleInfo sch) {
		if(null== sch){
			return;
		}
		
//		scheduleInfo.setSchedule_price(Float.parseFloat(jTextField2.getText()));
//		scheduleInfo.setSched_time(jTextField3.getText());
//		scheduleInfo.setSched_id(studio.getID());
//		scheduleInfo.setPlay_id(playInfo.getPlay_id());
//		System.out.println(stu.getName());
		ScheduleService scheduleService = new ScheduleService();
		ScheduleInfo scheduleInfo=new ScheduleService().FetchId(sch.getSched_id()).get(0);
		
		StudioSrv studioSrv=new StudioSrv();
		List <Studio>list=studioSrv.FetchId(scheduleInfo.getStudio_id());
		Studio studio=list.get(0);
		
		PlayService playService=new PlayService();
		List <PlayInfo>plist=playService.FetchId(scheduleInfo.getPlay_id());
		PlayInfo playInfo=plist.get(0);
		playName=playInfo.getPlay_name();
		studioName=studio.getName();
		
		jTextField3.setText(scheduleInfo.getSched_time());
		jTextField2.setText(scheduleInfo.getSchedule_price()+"");
//		txtName.invalidate();
//		System.out.println(txtName.getText()+"--");
//		txtRow.setText(Integer.toString(stu.getRowCount()));
//		txtRow.invalidate();
//		txtColumn.setText(Integer.toString(stu.getColCount()));
//		txtColumn.invalidate();
//		txtIntro.setText(stu.getIntroduction());
//		txtIntro.invalidate();
		scheduleInfo=sch;
		this.invalidate();
	}
	
	public String[] getStudioList() {
		return studioList;
	}


	public void setStudioList(String[] studioList) {
		this.studioList = studioList;
		jComboBox.removeAllItems();
		for(int i=0;i<studioList.length;i++)
		{
			jComboBox.addItem(studioList[i]);
			if(studioName.equals(studioList[i]))
				jComboBox.setSelectedIndex(i);
		}
		System.out.println(studioList.length+"[[");
		invalidate();
		
	}


	public String[] getPlayList() {
		return playList;
	}


	public void setPlayList(String[] playList) {
		this.playList = playList;
		jComboBox2.removeAllItems();
		for(int i=0;i<playList.length;i++){
			jComboBox2.addItem(playList[i]);
			System.out.println(playName);
			if(playName.equals(playList[i]))
				jComboBox2.setSelectedIndex(i);
		}
		invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		ScheduleService scheduleService = new ScheduleService();
		ScheduleInfo scheduleInfo=new ScheduleInfo();
		
		StudioSrv studioSrv=new StudioSrv();
		List <Studio>list=studioSrv.Fetch("studio_name = "+studioList[jComboBox.getSelectedIndex()]);
		Studio studio=list.get(0);
		
		PlayService playService=new PlayService();
		List <PlayInfo>plist=playService.Fetch("play_name = "+playList[jComboBox2.getSelectedIndex()]);
		PlayInfo playInfo=plist.get(0);
		
		scheduleInfo.setSchedule_price(Float.parseFloat(jTextField2.getText()));
		scheduleInfo.setSched_time(jTextField3.getText());
		scheduleInfo.setSched_id(studio.getID());
		scheduleInfo.setPlay_id(playInfo.getPlay_id());
		scheduleService.add(scheduleInfo);	
	}
	
}
