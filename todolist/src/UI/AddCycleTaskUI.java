package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.CycleTask;
import bean.FileOperation;
//添加周期任务界面
public class AddCycleTaskUI extends WindowUI{
	private String name;
	private String con;
	private String stat;
	private String datestr;
	private String timestr;
	private String cyclestr;
	private String file;
	
	public AddCycleTaskUI(String title,int height,int width,
			String name,String con,String stat,String file) {
		super(title,height,width);
		this.name=name;
		this.con=con;
		this.stat=stat;
		this.file=file;
	}
	public void addElement() { 
		JLabel date=new JLabel("执行日期：");
		JLabel times=new JLabel("重复次数：");
		JLabel cycle=new JLabel("重复周期：");
		JTextField dateField = new JTextField();
		JTextField timesField = new JTextField();
		JTextField cycleField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		date.setBounds(30,50,70,30);
		dateField.setBounds(90,50,150,30);
		times.setBounds(30,90,70,30);
		timesField.setBounds(90,90,150,30);
		cycle.setBounds(30,130,70,30);
		cycleField.setBounds(90,130,150,30);
		submitBtn.setBounds(110, 200, 70, 40);
		
		this.add(date);
		this.add(times);
		this.add(cycle);
		this.add(dateField);
		this.add(timesField);
		this.add(cycleField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		datestr=dateField.getText();
        		timestr=timesField.getText();
        		cyclestr=cycleField.getText();
        		//存储任务
        		CycleTask ctask=new CycleTask(name,stat,con,datestr,timestr,cyclestr);
        		String ctaskstr=objToString(ctask);
        		
        		FileOperation fo=new FileOperation();
				File f=fo.createFile(file);
				try {
					fo.appendFile(f, ctaskstr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		});
	
	}
	//对象转字符串
	public String objToString(CycleTask c) {
		String str=c.getName()+"$"+c.getType()+"$"+c.getStatus()+"$"+c.getContent()
		+"$"+c.getDate()+"$"+c.getTimes()+"$"+c.getCycle()+"\n";
		return str;
	}
}
