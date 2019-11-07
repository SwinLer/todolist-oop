package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
//添加子任务界面
public class AddChildTaskUI extends WindowUI{
	private String file;
	private String namestr;
	public AddChildTaskUI(String title,int height,int width,String file) {
		super(title,height,width);
		this.file=file;
	}
	public void addElement() {
		JLabel name=new JLabel("子任务：");
		JTextField nameField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		name.setBounds(10,50,70,30);
		nameField.setBounds(70,50,150,30);
		submitBtn.setBounds(85, 150, 70, 40);
		
		this.add(name);
		this.add(nameField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
				namestr=nameField.getText()+"\n";
				//存储任务
				FileOperation fo=new FileOperation();
				File f=fo.createFile(file);
				try {
					fo.appendFile(f, namestr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}

