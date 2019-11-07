package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
//更改任务界面
public class ModifyTaskUI extends WindowUI{
	private String name;
	private String newstatus;
	private String newcontent;
	private String newdate;
	private String file;
	
	public ModifyTaskUI(String title,int height,int width,String file) {
		super(title,height,width);
		this.file=file;
	}
	public void addElement() {
		JLabel taskName=new JLabel("要修改的任务名称：");
		JLabel msta=new JLabel("状态修改为：");
		JLabel mcon=new JLabel("备注修改为：");
		JLabel mdate=new JLabel("日期修改为：");
		JTextField nameField = new JTextField();
		JTextField mstaField = new JTextField();
		JTextField mconField = new JTextField();
		JTextField mdateField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		taskName.setBounds(50,50,120,50);
		msta.setBounds(50,110,120,50);
		mcon.setBounds(50,170,120,50);
		mdate.setBounds(50,230,120,50);
		nameField.setBounds(155,50,300,50);
		mstaField.setBounds(155,110,300,50);
		mconField.setBounds(155,170,300,50);
		mdateField.setBounds(155,230,300,50);
		submitBtn.setBounds(200,330,100,50);
		
		this.add(taskName);
		this.add(msta);
		this.add(mcon);
		this.add(mdate);
		this.add(nameField);
		this.add(mstaField);
		this.add(mconField);
		this.add(mdateField);
		this.add(submitBtn);
		

		//添加监听器
		submitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		name=nameField.getText();
        		newstatus=mstaField.getText();
        		newcontent=mconField.getText();
        		newdate=mdateField.getText();
        		FileOperation fo=new FileOperation();
        		try {
					fo.modifyTaskLine(file, name, newstatus,newcontent,newdate);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		});
	}
}
