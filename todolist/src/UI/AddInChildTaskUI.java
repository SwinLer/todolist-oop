package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bean.WindowSize;
//子任务添加子任务
public class AddInChildTaskUI extends JFrame{
	private String file;
	private String name;
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public AddInChildTaskUI(String title,int height,int width) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
		this.name=title;
		this.file="list/childtask"+title+".txt";
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
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		
		
		panel3.setPreferredSize(new Dimension(600, 40));
		panel4.setPreferredSize(new Dimension(550, 300));
		
		JButton childtbtn=new JButton("添加子任务");
		JButton updatebtn=new JButton("刷新");
		
		panel3.add(childtbtn);
		panel3.add(updatebtn);

		paintTable(panel4,file);
		
		JPanel panel=new JPanel();
        panel.add(panel3);
        panel.add(panel4);
        this.setContentPane(panel);
		
        //添加监听器
        //更新界面按钮监听
        updatebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		updateTable(panel4,file);
        	}
        });
        //添加子任务按钮监听
        childtbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		new AddChildTaskUI(name,300,300,file);
        	}
        });
	}
	//更新列表界面
	public void updateTable(JPanel panel4,String file) {
		panel4.removeAll();
		panel4.repaint();
		paintTable(panel4,file);
		panel4.revalidate();
	}
	//读取文件绘制列表
	public void paintTable(JPanel panel4,String file) {
		JScrollPane childListPane=new JScrollPane();
        panel4.setLayout(new BorderLayout(0,0));
        panel4.add(childListPane,BorderLayout.CENTER);
        ChildTableUI list=new ChildTableUI(file);
        list.updatecTable();
        childListPane.setViewportView(list);
        //列表行监听
        list.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if(list.getValueIsAdjusting()) {
        			//点击子任务弹出窗口
        			new AddInChildTaskUI(list.getSelectedValue()+"",600,500);
        		}
        	}
        });
	}
}
