package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bean.FileOperation;
import bean.WindowSize;
//删除任务界面
public class DeleteTaskUI extends JFrame{
	private String file;
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	public DeleteTaskUI(String title,int height,int width,String file) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
		this.file=file;
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
		submitBtn.addActionListener(new NActionLtn(nameField,file));
	}
}
//提交按钮监听器调用动作
class NActionLtn implements ActionListener{
	private JTextField tf;
	private String name;
	private String file;
	public NActionLtn(JTextField tf,String file) {
		this.tf=tf;
		this.file=file;
	}
	public void actionPerformed(ActionEvent event) {
		this.name=tf.getText();
		FileOperation fc=new FileOperation();
		//删除文件行
		try {
			fc.deleteLine(file, this.name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
