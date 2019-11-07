package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
//更改清单界面
public class ModifyListUI extends WindowUI{
	private String listname;
	private String newtype;
	private String file;
	
	public ModifyListUI(String title,int height,int width,String file) {
		super(title,height,width);
		this.file=file;
	}
	public void addElement() {
		JLabel listName=new JLabel("要修改的清单名称：");
		JLabel mlistType=new JLabel("类型修改为：");
		JTextField nameField = new JTextField();
		JTextField mtypeField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		listName.setBounds(50,100,120,50);
		mlistType.setBounds(50,200,100,50);
		nameField.setBounds(155,100,265,50);
		mtypeField.setBounds(120,200,300,50);
		submitBtn.setBounds(200,300,100,50);
		
		this.add(listName);
		this.add(mlistType);
		this.add(nameField);
		this.add(mtypeField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		listname=nameField.getText();
        		newtype=mtypeField.getText();
        		FileOperation fo=new FileOperation();
        		try {
					fo.modifyLine(file, listname, newtype);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		});
	}
}
