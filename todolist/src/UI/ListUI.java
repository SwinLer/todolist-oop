package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bean.FileOperation;
//主界面
public class ListUI extends WindowUI{
	public ListUI(String title,int height,int width) {
		super(title,height,width);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addElement() {
		//窗口划分
		JPanel panel0=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel3=new JPanel();
		panel0.setPreferredSize(new Dimension(600, 50));
		panel1.setPreferredSize(new Dimension(600, 50));
		panel3.setPreferredSize(new Dimension(550, 300));
		
		//功能按钮
		JLabel sort=new JLabel("清单排序方法：");
		JRadioButton sort0=new JRadioButton("按时间排序",true);
		JRadioButton sort1=new JRadioButton("按名称排序");
		ButtonGroup sortGroup=new ButtonGroup();
		sortGroup.add(sort0);
        sortGroup.add(sort1);
        JButton sortbtn=new JButton("排序");
        JButton creatbtn=new JButton("创建清单");
        JButton deletebtn=new JButton("删除清单");
        JButton modifybtn=new JButton("修改清单");
        JButton updatebtn=new JButton("刷新清单");
        JButton movebtn=new JButton("任务转移");
        JButton copybtn=new JButton("任务复制");
        
        panel0.add(sort);
        panel0.add(sort0);
        panel0.add(sort1);
        panel0.add(sortbtn);
        panel1.add(creatbtn);
        panel1.add(deletebtn);
        panel1.add(modifybtn);
        panel1.add(updatebtn);
        panel1.add(movebtn);
        panel1.add(copybtn);
        
        paintTable(panel3,"list/list.txt");
        
        JPanel panel=new JPanel();
        panel.add(panel0);
        panel.add(panel1);
        panel.add(panel3);
        this.setContentPane(panel);
        
        //添加按钮监听器
        
        //创建清单按钮监听
        creatbtn.addActionListener(new AddActionLtn());
        //删除清单按钮监听
        deletebtn.addActionListener(new DeleteActionLtn());
        //更改清单按钮监听
        modifybtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		new ModifyListUI("修改清单",500,500,"list/list.txt");
        	}
        });
        //排序清单按钮组监听
        sortbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		Enumeration<AbstractButton> elements = sortGroup.getElements();
                while (elements.hasMoreElements()) {
                    AbstractButton button = (AbstractButton) elements
                            .nextElement();
                    if (button.isSelected()) {
                    	if(button.getText().equals("按时间排序")) {
                    		updateTable(panel3,"list/list.txt");
                    	}
                    	if(button.getText().equals("按名称排序")) {
                    		FileOperation fo=new FileOperation();
                    		try {
								fo.sortByName("list/list.txt","list/sortbynamelist.txt");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		updateTable(panel3,"list/sortbynamelist.txt");
                    	}
                        
                        break;
                    }
                }
        	}
        });
        //更新列表按钮监听
        updatebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		updateTable(panel3,"list/list.txt");
        	}
        });
        //任务转移按钮监听
        movebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		new MoveTaskUI("任务转移",270,300);
        	}
        });
        //任务复制按钮监听
        copybtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		new CopyTaskUI("任务复制",270,300);
        	}
        });
	}
	//更新列表
	public void updateTable(JPanel panel3,String file) {
		panel3.removeAll();
		panel3.repaint();
		paintTable(panel3,file);
		panel3.revalidate();
	}
	//绘制列表
	public void paintTable(JPanel panel3,String file) {
		JScrollPane taskListPane=new JScrollPane();
        panel3.setLayout(new BorderLayout(0,0));
        panel3.add(taskListPane,BorderLayout.CENTER);
        ListTableUI list=new ListTableUI(file);
        list.updateTable();
        taskListPane.setViewportView(list);
        
        list.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if(list.getValueIsAdjusting()) {
        			new TaskListUI(list.getSelectedValue()+"",600,550,
        					(list.getSelectedValue()+"").substring((list.getSelectedValue()+"").indexOf("清单名称：")+5,
        							(list.getSelectedValue()+"").indexOf("         类型：") ));
        		}
        	}
        });
	}
}

//添加按钮事件监听动作
class AddActionLtn implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		new CreateListUI("添加任务清单",500,500);
	}
}
class DeleteActionLtn implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		new DeleteListUI("删除任务清单",250,300);
	}
	
}



