package UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import bean.FileOperation;
//任务列表界面
public class TaskTableUI extends TableUI{
	private int lineNum;
	private ArrayList al;
	private String filename;
	private String[] dataList;
	
	public TaskTableUI(String filename) {
		super(filename);
		this.filename=filename;
	}
	//更新任务列表
	public void updateTaskTable() {
		String str0;
		String str1;
		String str2;
		int index;
		int index0;
		int index1;
		FileOperation filecon=new FileOperation();
		try {
			filecon.createFile(filename);
			this.al=filecon.readFileLine(filename);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取界面需显示字符串
		this.lineNum=this.al.size();
		this.dataList=new String[lineNum];
		System.out.println(lineNum+".");
		for (int i=0;i<lineNum;i++) {
			index=(this.al.get(i)+"").indexOf("$");
			str0=(this.al.get(i)+"").substring(0,index);
			index0=(this.al.get(i)+"").indexOf("$",index+1);
			index1=(this.al.get(i)+"").indexOf("$",index0+1);
			str1=(this.al.get(i)+"").substring(index+1,index0);
			str2=(this.al.get(i)+"").substring(index0+1,index1);
			dataList[i]="1:"+str0+"      2:"+str1+"       3:"+str2;
		}
		this.setListData(dataList);
	}
}
