package UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import bean.FileOperation;
//子任务列表界面
public class ChildTableUI extends TableUI{
	private int lineNum;
	private ArrayList al;
	private String filename;
	private String[] dataList;
	
	public ChildTableUI(String filename) {
		super(filename);
		this.filename=filename;
	}
	//更新子任务列表
	public void updatecTable() {
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
		
		this.lineNum=this.al.size();
		this.dataList=new String[lineNum];
		System.out.println(lineNum+".");
		for (int i=0;i<lineNum;i++) {
			dataList[i]=this.al.get(i)+"";
		}
		this.setListData(dataList);
	}
}
