package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
import bean.LongTask;
//添加长期任务界面
public class AddLongTaskUI extends WindowUI{
	private String con;
	private String stat;
	private String datestr;
	private String childstr;
	private String file;
	
	public AddLongTaskUI(String title,int height,int width,
			String name,String con,String stat,String file) {
		super(title,height,width);
		this.con=con;
		this.stat=stat;
		this.file=file;
	}
	public void addElement() { 
		JLabel date=new JLabel("截止日期：");
		JTextField dateField = new JTextField();
		JButton subBtn = new JButton("提交");
		
		date.setBounds(10,50,70,30);
		dateField.setBounds(75,50,170,30);
		subBtn.setBounds(100, 170, 70, 40);
		
		this.add(date);
		this.add(dateField);
		this.add(subBtn);
		
		//添加监听器
		subBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
				datestr=dateField.getText();
				//存储任务
				LongTask ltask=new LongTask(file,stat,con,datestr,childstr);
				String ltaskstr=objToString(ltask);
				
				FileOperation fo=new FileOperation();
				File f=fo.createFile(file);
				try {
					fo.appendFile(f, ltaskstr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		});
	}
	//任务对象转字符串
	public String objToString(LongTask l) {
		String str=l.getName()+"$"+l.getType()+"$"+l.getStatus()
		+"$"+l.getContent()+"$"+l.getDate()+"\n";
		return str;
	}
}
