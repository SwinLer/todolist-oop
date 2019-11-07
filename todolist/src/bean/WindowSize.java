package bean;

import java.awt.Dimension;
import java.awt.Toolkit;
//获取窗口大小
public class WindowSize {
	private int screenSizeWidth;
	private int screenSizeHeight;
	
	public WindowSize() {
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenSizeWidth = (int) dimension.getWidth();
		this.screenSizeHeight = (int) dimension.getHeight();
	}
	
	public int getScreenSizeWidth() {
		return this.screenSizeWidth;
	}
	public int screenSizeHeight() {
		return this.screenSizeHeight;
	}
}
