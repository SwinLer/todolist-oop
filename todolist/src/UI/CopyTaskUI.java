package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
//清单间复制任务界面
public class CopyTaskUI extends WindowUI{
	public CopyTaskUI(String title,int height,int width) {
		super(title,height,width);
	}
	public void addElement() {
		JLabel source=new JLabel("源清单：");
		JTextField sourceField = new JTextField();
		JLabel order=new JLabel("目的清单：");
		JTextField orderField = new JTextField();
		JButton submitBtn = new JButton("提交");
		
		source.setBounds(10,50,70,30);
		sourceField.setBounds(70,50,150,30);
		order.setBounds(10,100,70,30);
		orderField.setBounds(70,100,150,30);
		submitBtn.setBounds(85, 160, 70, 40);
		
		this.add(source);
		this.add(sourceField);
		this.add(order);
		this.add(orderField);
		this.add(submitBtn);
		
		//添加监听器
		submitBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
				String source="list/"+sourceField.getText()+".txt";
				String order="list/"+orderField.getText()+".txt";
				FileOperation fo=new FileOperation();
				try {
					fo.copyTask(source, order);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
