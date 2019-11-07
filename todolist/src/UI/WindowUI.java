package UI;

import javax.swing.JFrame;

import bean.WindowSize;
//窗口抽象类
public abstract class WindowUI extends JFrame{
	private int windowWidth;
	private int windowHeight;
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public WindowUI(String title,int height,int width) {
		super(title);
		this.setSize(height, width);//窗口大小
		this.setLayout(null);//默认布局设空
		this.setResizable(false);//不可缩放
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
	//窗口具体元素添加
	public abstract void addElement();
}
