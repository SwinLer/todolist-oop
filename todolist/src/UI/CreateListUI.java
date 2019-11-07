package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
import bean.TaskList;
//创建清单界面
public class CreateListUI extends WindowUI{
	public CreateListUI(String title,int height,int width) {
		super(title,height,width);
	}
	public void addElement() {
		JLabel listName=new JLabel("清单名称：");
		JLabel listType=new JLabel("清单类型：");
		JTextField nameField = new JTextField();
		JTextField typeField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		listName.setBounds(50,100,100,50);
		listType.setBounds(50,150,100,50);
		nameField.setBounds(100,100,300,50);
		typeField.setBounds(100,150,300,50);
		submitBtn.setBounds(200,250,100,50);
		
		this.add(listName);
		this.add(listType);
		this.add(nameField);
		this.add(typeField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new subActionLtn(nameField,typeField));
		
	}
}

//提交按钮动作
class subActionLtn implements ActionListener{
	private JTextField f1;
	private JTextField f2;
	private final String filename="list/list.txt";
	private String content;
	
	public subActionLtn(JTextField namefield,JTextField typefield) {
		this.f1=namefield;
		this.f2=typefield;
	}
	public void actionPerformed(ActionEvent event) {
		TaskList taskList=new TaskList(f1.getText(),f2.getText());
		//新建清单以字符串写入文件
		content=taskList.getListName()+"$"+taskList.getType()+"\n";
		FileOperation filecontrol=new FileOperation();
		File listfile=filecontrol.createFile(filename);
		try {
			filecontrol.appendFile(listfile, content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
