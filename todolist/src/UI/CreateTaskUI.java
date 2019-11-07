package UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bean.WindowSize;
//创建任务界面
public class CreateTaskUI extends JFrame{
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	private String file;
	public CreateTaskUI(String title,int height,int width,String file) {
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
	
	public String getFile() {
		return file;
	}

	public void addElement() {
		JPanel panel0=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		panel0.setPreferredSize(new Dimension(500, 50));
		panel1.setPreferredSize(new Dimension(500, 50));
		panel2.setPreferredSize(new Dimension(500, 50));
		panel3.setPreferredSize(new Dimension(500, 50));
		panel4.setPreferredSize(new Dimension(500, 50));
		
		JLabel taskName=new JLabel("任务名称：");
		JLabel taskType=new JLabel("任务类型：");
		JLabel taskContent=new JLabel("任务备注：");
		JLabel taskStatus=new JLabel("任务状态：");
		
		JTextField nameField=new JTextField(25);
		JTextField contenteField=new JTextField(25);
		
		JRadioButton type0=new JRadioButton("临时任务");
		JRadioButton type1=new JRadioButton("周期任务");
		JRadioButton type2=new JRadioButton("长期任务");
		ButtonGroup typeGroup=new ButtonGroup();
		typeGroup.add(type0);
		typeGroup.add(type1);
		typeGroup.add(type2);
		
		JRadioButton status0=new JRadioButton("未完成");
		JRadioButton status1=new JRadioButton("已完成");
		ButtonGroup statusGroup=new ButtonGroup();
		statusGroup.add(status0);
		statusGroup.add(status1);
		
		JButton subbtn=new JButton("提交");
		
		panel0.add(taskName);
		panel0.add(nameField);
		panel1.add(taskType);
		panel1.add(type0);
		panel1.add(type1);
		panel1.add(type2);
		panel2.add(taskStatus);
		panel2.add(status0);
		panel2.add(status1);
		panel3.add(taskContent);
		panel3.add(contenteField);
		panel4.add(subbtn);
		
		JPanel panel=new JPanel();
		panel.add(panel0);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        this.setContentPane(panel);
        
        //添加事件监听
        subbtn.addActionListener(new CreateBtnLtn(nameField,
        		contenteField,typeGroup,statusGroup,this.file));
	}
}

//监听器行为
class CreateBtnLtn implements ActionListener{
	private JTextField nameField;
	private JTextField contenteField;
	private ButtonGroup typeGroup;
	private ButtonGroup statusGroup;
	private String name;
	private String con;
	private String type;
	private String stat;
	private String file;
	
	public CreateBtnLtn (JTextField nameField,JTextField contenteField,
			ButtonGroup typeGroup,ButtonGroup statusGroup,String file) {
		this.nameField=nameField;
		this.contenteField=contenteField;
		this.typeGroup=typeGroup;
		this.statusGroup=statusGroup;
		this.file=file;
	}
	
	public void actionPerformed(ActionEvent event) {
		this.name=nameField.getText();
		this.con=contenteField.getText();
		AbstractButton selectedTypeBtn=getRadioButton(typeGroup);
		AbstractButton selectedStatBtn=getRadioButton(statusGroup);
		this.stat=selectedStatBtn.getText();
		
        if(selectedTypeBtn.getText().equals("临时任务")) {
        	//生成添加临时任务界面
        	new AddTemporaryTaskUI("临时任务",250,300,this.name,this.con,this.stat,file);
        	
        }else if(selectedTypeBtn.getText().equals("周期任务")) {
        	//生成添加周期任务界面
        	new AddCycleTaskUI("周期任务",300,350,this.name,this.con,this.stat,file);
        	
        }else {
        	//生成添加长期任务界面
        	new AddLongTaskUI("长期任务",300,300,this.name,this.con,this.stat,file);
        }
	}
	//获取单选按钮组被选中按钮
	public AbstractButton getRadioButton(ButtonGroup group) {
		Enumeration<AbstractButton> elements = group.getElements();
		AbstractButton button;
        while (elements.hasMoreElements()) {
            button = (AbstractButton) elements
                    .nextElement();
            if (button.isSelected()) {
            	return button;
            }
        }
        return null;
	}
	
}
