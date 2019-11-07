package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
import bean.TemporaryTask;
//添加临时任务界面
public class AddTemporaryTaskUI extends WindowUI{
	private String name;
	private String con;
	private String stat;
	private String datestr;
	private String file;
	public AddTemporaryTaskUI(String title,int height,int width,
			String name,String con,String stat,String file) {
		super(title,height,width);
		this.name=name;
		this.con=con;
		this.stat=stat;
		this.file=file;
	}
	public void addElement() {
		JLabel date=new JLabel("截止日期：");
		JTextField dateField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		date.setBounds(10,50,70,30);
		dateField.setBounds(70,50,150,30);
		submitBtn.setBounds(85, 150, 70, 40);
		
		this.add(date);
		this.add(dateField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
				datestr=dateField.getText();
				//存储任务到文件
				TemporaryTask ttask=new TemporaryTask(name,stat,con,datestr);
				String ttaskstr=objToString(ttask);
				
				FileOperation fo=new FileOperation();
				File f=fo.createFile(file);
				try {
					fo.appendFile(f, ttaskstr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}
	//任务对象转字符串
	public String objToString(TemporaryTask t) {
		String str=t.getName()+"$"+t.getType()+"$"+t.getStatus()
		+"$"+t.getContent()+"$"+t.getDate()+"\n";
		return str;
	}
}

