package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
//删除任务清单界面
public class DeleteListUI extends WindowUI{
	public DeleteListUI(String title,int height,int width) {
		super(title,height,width);
	}
	public void addElement() {
		JLabel name=new JLabel("删除清单名称：");
		JTextField nameField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		name.setBounds(10,50,70,30);
		nameField.setBounds(70,50,150,30);
		submitBtn.setBounds(85, 150, 70, 40);
		
		this.add(name);
		this.add(nameField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new ActionLtn(nameField));
	}
}
//监听器调用动作
class ActionLtn implements ActionListener{
	private JTextField tf;
	private String name;
	public ActionLtn(JTextField tf) {
		this.tf=tf;
	}
	public void actionPerformed(ActionEvent event) {
		this.name=tf.getText();
		FileOperation fc=new FileOperation();
		
		try {
			fc.deleteLine("list/list.txt", this.name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
