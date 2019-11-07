package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bean.WindowSize;
//显示周期任务界面
public class CycleTaskUI extends JFrame{
	private String date;
	private String sta;
	private String times;
	private String cycle;
	private String con;
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public CycleTaskUI(String title,int height,int width,
			String date,String stat,String times,String cycle,String con) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
		this.date=date;
		this.sta=stat;
		this.times=times;
		this.cycle=cycle;
		this.con=con;
		init();
		addElement();
		this.setVisible(true);
	}
	
	public void init() {
		//程序窗口居中
		WindowSize wsize=new WindowSize();
		screenSizeWidth=wsize.getScreenSizeWidth();
		screenSizeHeight=wsize.screenSizeHeight();
		windowWidth=this.getWidth();
		windowHeight=this.getHeight();
		this.setLocation(screenSizeWidth/2-windowWidth/2,screenSizeHeight/2-windowHeight/2);
	}
	
	public void addElement() {
		JLabel date=new JLabel("执行日期：");
		JLabel status=new JLabel("任务状态：");
		JLabel time=new JLabel("重复次数：");
		JLabel cyc=new JLabel("重复周期：");
		JLabel content=new JLabel("备注：");
		JLabel dateField = new JLabel(this.date);
		JLabel statusField=new JLabel(this.sta);
		JLabel timeField=new JLabel(this.times);
		JLabel cycField=new JLabel(this.cycle);
		JLabel contentField=new JLabel(this.con);
		//组件位置大小控制
		date.setBounds(10,50,70,30);
		dateField.setBounds(70,50,150,30);
		status.setBounds(10,90,70,30);
		statusField.setBounds(70,90,150,30);
		time.setBounds(10,130,70,30);
		timeField.setBounds(70,130,150,30);
		cyc.setBounds(10,170,70,30);
		cycField.setBounds(70,170,150,30);
		content.setBounds(10,210,70,30);
		contentField.setBounds(70,210,150,30);
		
		this.add(date);
		this.add(status);
		this.add(time);
		this.add(cyc);
		this.add(content);
		this.add(dateField);
		this.add(statusField);
		this.add(timeField);
		this.add(cycField);
		this.add(contentField);
	}
}
