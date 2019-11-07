package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.WindowSize;
//临时任务界面
public class TemporaryTaskUI extends JFrame{
	private String date;
	private String sta;
	private String con;
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public TemporaryTaskUI(String title,int height,int width,
			String date,String stat,String con) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
		this.date=date;
		this.sta=stat;
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
		JLabel date=new JLabel("截止日期：");
		JLabel status=new JLabel("任务状态：");
		JLabel content=new JLabel("备注：");
		JLabel dateField = new JLabel(this.date);
		JLabel statusField=new JLabel(this.sta);
		JLabel contentField=new JLabel(this.con);
		
		date.setBounds(10,50,70,30);
		dateField.setBounds(70,50,150,30);
		status.setBounds(10,90,70,30);
		statusField.setBounds(70,90,150,30);
		content.setBounds(10,130,70,30);
		contentField.setBounds(70,130,150,30);
		
		this.add(date);
		this.add(status);
		this.add(content);
		this.add(dateField);
		this.add(statusField);
		this.add(contentField);
	}
}
