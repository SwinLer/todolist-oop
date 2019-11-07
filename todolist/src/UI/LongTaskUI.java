package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bean.WindowSize;
//长期任务显示界面
public class LongTaskUI extends JFrame{
	private String date;
	private String sta;
	private String con;
	private String file;
	private String name;
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public LongTaskUI(String title,int height,int width,
			String date,String stat,String con) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
		this.date=date;
		this.sta=stat;
		this.con=con;
		this.name=title;
		this.file="list/longtask"+title+".txt";
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
		JPanel panel0=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		
		
		panel0.setPreferredSize(new Dimension(600, 30));
		panel1.setPreferredSize(new Dimension(600, 30));
		panel2.setPreferredSize(new Dimension(600, 30));
		panel3.setPreferredSize(new Dimension(600, 40));
		panel4.setPreferredSize(new Dimension(550, 300));
		
		JLabel date=new JLabel("截止日期：");
		JLabel status=new JLabel("任务状态：");
		JLabel content=new JLabel("备注：");
		JLabel dateField = new JLabel(this.date);
		JLabel statusField=new JLabel(this.sta);
		JLabel contentField=new JLabel(this.con);
		JButton childtbtn=new JButton("添加子任务");
		JButton updatebtn=new JButton("刷新");
		JLabel child=new JLabel("子任务：");
		
		panel0.add(date);
		panel0.add(dateField);
		panel1.add(status);
		panel1.add(statusField);
		panel2.add(content);
		panel2.add(contentField);
		panel3.add(childtbtn);
		panel3.add(updatebtn);

		paintTable(panel4,file);
		
		JPanel panel=new JPanel();
        panel.add(panel0);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        this.setContentPane(panel);
		
        //添加监听器
        //更新列表界面按钮监听
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
	//更新列表
	public void updateTable(JPanel panel4,String file) {
		panel4.removeAll();
		panel4.repaint();
		paintTable(panel4,file);
		panel4.revalidate();
	}
	//绘制列表
	public void paintTable(JPanel panel4,String file) {
		JScrollPane childListPane=new JScrollPane();
        panel4.setLayout(new BorderLayout(0,0));
        panel4.add(childListPane,BorderLayout.CENTER);
        ChildTableUI list=new ChildTableUI(file);
        list.updatecTable();
        childListPane.setViewportView(list);
        
        list.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if(list.getValueIsAdjusting()) {
        			//点击子任务
        			new AddInChildTaskUI(list.getSelectedValue()+"",600,500);
        		}
        	}
        });
	}
}
