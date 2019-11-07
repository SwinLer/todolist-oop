package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bean.FileOperation;
import bean.WindowSize;
//单个任务清单界面
public class TaskListUI extends JFrame{
	private String titleName;
	private String file;
	private String searchname;
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public TaskListUI(String title,int height,int width,String file) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
		this.titleName="list/"+file+".txt";
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
		//窗口划分
		JPanel panel0=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		panel0.setPreferredSize(new Dimension(600, 50));
		panel1.setPreferredSize(new Dimension(600, 50));
		panel2.setPreferredSize(new Dimension(600, 50));
		panel3.setPreferredSize(new Dimension(600, 40));
		panel4.setPreferredSize(new Dimension(550, 260));
		
		//功能按钮
		JLabel sort=new JLabel("任务排序方法：");
		JRadioButton sort0=new JRadioButton("按时间排序",true);
		JRadioButton sort1=new JRadioButton("按名称排序");
		JRadioButton sort2=new JRadioButton("按名称长度排序");
		JButton sortbtn=new JButton("排序");
		ButtonGroup sortGroup=new ButtonGroup();
		sortGroup.add(sort0);
		sortGroup.add(sort1);
		sortGroup.add(sort2);
		JButton creatbtn=new JButton("创建任务");
        JButton deletebtn=new JButton("删除任务");
        JButton modifybtn=new JButton("修改任务");
        JButton updatebtn=new JButton("刷新列表");
        JLabel search=new JLabel("输入查找任务名称：");
        JTextField nameField = new JTextField(25);
        JButton submitbtn=new JButton("提交");
        
        JLabel label0=new JLabel("1:任务名称                                        ");
        JLabel label1=new JLabel("2:任务类型                                         ");
        JLabel label2=new JLabel("3:任务状态");
        
        panel0.add(sort);
        panel0.add(sort0);
        panel0.add(sort1);
        panel0.add(sort2);
        panel0.add(sortbtn);
        panel1.add(creatbtn);
        panel1.add(deletebtn);
        panel1.add(modifybtn);
        panel1.add(updatebtn);
        panel2.add(search);
        panel2.add(nameField);
        panel2.add(submitbtn);
        panel3.add(label0);
        panel3.add(label1);
        panel3.add(label2);
        
        paintTable(panel4,titleName);
        
        JPanel panel=new JPanel();
        panel.add(panel0);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        this.setContentPane(panel);
        
        //添加监听
        //删除任务按钮监听
        deletebtn.addActionListener(new DAtionLtn(titleName));
        //创建任务按钮监听
        creatbtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event) {
        		new CreateTaskUI("添加任务",500,330,titleName);
        	}});
        //更改任务文件监听
        modifybtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		new ModifyTaskUI("修改任务",500,500,titleName);
        	}
        });
        //排序按钮组监听
        sortbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		Enumeration<AbstractButton> elements = sortGroup.getElements();
                while (elements.hasMoreElements()) {
                    AbstractButton button = (AbstractButton) elements
                            .nextElement();
                    //获取选中按钮分类排序更新
                    if (button.isSelected()) {
                    	if(button.getText().equals("按时间排序")) {
                    		updateTable(panel4,titleName);
                    	}
                    	if(button.getText().equals("按名称排序")) {
                    		FileOperation fo=new FileOperation();
                    		try {
								fo.sortByName(titleName,"list/"+file+"sortbyname.txt");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		updateTable(panel4,"list/"+file+"sortbyname.txt");
                    	}
                    	if(button.getText().equals("按名称长度排序")) {
                    		FileOperation fo=new FileOperation();
                    		try {
								fo.sortByNameLength(titleName,"list/"+file+"sortbynamelength.txt");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		updateTable(panel4,"list/"+file+"sortbynamelength.txt");
                    	}
                        break;
                    }
                }
        	}
        });
        //更新列表按钮监听
        updatebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		panel4.removeAll();
        		panel4.repaint();
        		paintTable(panel4,titleName);
        		panel4.revalidate();
        	}
        });
        //任务搜索按钮监听
        submitbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		searchname=nameField.getText();
        		FileOperation fo=new FileOperation();
        		try {
					String str=fo.searchLine(titleName, searchname);
					int index=str.indexOf("$");
					int index0=str.indexOf("$", index+1);
					String type=str.substring(index+1,index0);
					if(type.equals("临时任务")) {
						int index1=str.indexOf("$", index0+1);
						int index2=str.indexOf("$", index1+1);
						new TemporaryTaskUI(str.substring(0,index),300,300,str.substring(index2+1),
								str.substring(index0+1,index1),str.substring(index1+1,index2));
					}else if(type.equals("周期任务")) {
						int index1=str.indexOf("$", index0+1);
						int index2=str.indexOf("$", index1+1);
						int index3=str.indexOf("$", index2+1);
						int index4=str.indexOf("$", index3+1);
						new CycleTaskUI(str.substring(0,index),300,400,str.substring(index2+1,index3),
								str.substring(index0+1,index1),str.substring(index3+1,index4),
								str.substring(index4+1),str.substring(index1+1,index2));
					}else {
						int index1=str.indexOf("$", index0+1);
						int index2=str.indexOf("$", index1+1);
						new LongTaskUI(str.substring(0,index),600,550,str.substring(index2+1),
								str.substring(index0+1,index1),str.substring(index1+1,index2));
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	//绘制列表
	public void paintTable(JPanel panel4,String file) {
		JScrollPane taskListPane=new JScrollPane();
        panel4.setLayout(new BorderLayout(0,0));
        panel4.add(taskListPane,BorderLayout.CENTER);
        TaskTableUI list=new TaskTableUI(file);
        list.updateTaskTable();
        taskListPane.setViewportView(list);
        
        list.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if(list.getValueIsAdjusting()) {
        			//点击具体任务跳出的窗口设置
        			int index=(list.getSelectedValue()+"").indexOf("2:");
        			int index0=(list.getSelectedValue()+"").indexOf("       3:");
        			String str=(list.getSelectedValue()+"").substring(index,index0);
        			System.out.println(str);
        			if(str.equals("2:临时任务")) {
        				showTemTask(list,index);
        			}else if(str.equals("2:周期任务")) {
        				showCycTask(list,index,index0);
        			}else if(str.equals("2:长期任务")) {
        				showLongTask(list,index);
        			}
        		}
        	}
        });
	}
	//显示临时任务界面
	public void showTemTask(TaskTableUI list,int index) {
		String sta=list.getInformation3((list.getSelectedValue()+"").substring(2,index-6));
		String con=list.getInformation4((list.getSelectedValue()+"").substring(2,index-6));
		String date=list.getInformation5((list.getSelectedValue()+"").substring(2,index-6));
		new TemporaryTaskUI((list.getSelectedValue()+"").substring(2,index-6),300,300,date,sta,con);
    }
	//显示循环任务界面
	public void showCycTask(TaskTableUI list,int index,int index0) {
		String sta=list.getInformation3((list.getSelectedValue()+"").substring(2,index-6));
		String con=list.getInformation4((list.getSelectedValue()+"").substring(2,index-6));
		String date=list.getInformationc5((list.getSelectedValue()+"").substring(2,index-6));
		String times=list.getInformation6((list.getSelectedValue()+"").substring(2,index-6));
		String cycle=list.getInformation7((list.getSelectedValue()+"").substring(2,index-6));
		new CycleTaskUI((list.getSelectedValue()+"").substring(2,index-6),300,400,date,sta,times,cycle,con);
	}
	//显示长期任务界面
	public void showLongTask(TaskTableUI list,int index) {
		String sta=list.getInformation3((list.getSelectedValue()+"").substring(2,index-6));
		String con=list.getInformation4((list.getSelectedValue()+"").substring(2,index-6));
		String date=list.getInformation5((list.getSelectedValue()+"").substring(2,index-6));
		new LongTaskUI((list.getSelectedValue()+"").substring(2,index-6),600,520,date,sta,con);
	}
}
//删除任务监听动作
class DAtionLtn implements ActionListener{
	private String file;
	public DAtionLtn(String file) {
		this.file=file;
	}
	public void actionPerformed(ActionEvent event) {
		new DeleteTaskUI("删除任务",250,300,file);
	}
}

