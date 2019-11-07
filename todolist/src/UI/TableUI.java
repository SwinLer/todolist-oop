package UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import bean.FileOperation;
//总列表界面
public class TableUI extends JList{
	private int lineNum;
	private ArrayList al;
	private String filename;
	private String[] dataList;
	
	public TableUI(String filename) {
		this.filename=filename;
		//元素单选
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	//更新列表揭界面
	public void updateTable() {
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
			dataList[i]="清单名称："+(this.al.get(i)+"").substring(0,this.getLocation(i,"$"))+
					"         类型："+(this.al.get(i)+"").substring(this.getLocation(i,"$")+1);
		}
		this.setListData(dataList);
	}
	
	//获取任务状态
	public String getInformation3(String taskname) {
		String str0;
		int index;
		int index0;
		int index1;
		al=this.getInf();
		this.lineNum=this.al.size();
		
		for (int i=0;i<lineNum;i++) {
			if((this.al.get(i)+"").startsWith(taskname)) {
				index=(this.al.get(i)+"").indexOf("$");
				index0=(this.al.get(i)+"").indexOf("$",index+1);
				index1=(this.al.get(i)+"").indexOf("$",index0+1);
				str0=(this.al.get(i)+"").substring(index0+1,index1);
				return str0;
			}
		}
		return null;
	}
	//获取任务备注
	public String getInformation4(String taskname) {
		String str0;
		int index;
		int index0;
		int index1;
		int index2;
		al=this.getInf();
		this.lineNum=this.al.size();
		
		for (int i=0;i<lineNum;i++) {
			if((this.al.get(i)+"").startsWith(taskname)) {
				index=(this.al.get(i)+"").indexOf("$");
				index0=(this.al.get(i)+"").indexOf("$",index+1);
				index1=(this.al.get(i)+"").indexOf("$",index0+1);
				index2=(this.al.get(i)+"").indexOf("$",index1+1);
				str0=(this.al.get(i)+"").substring(index1+1,index2);
				return str0;
			}
		}
		return null;
	}
	//获取临时任务日期
	public String getInformation5(String taskname) {
		String str0;
		int index;
		int index0;
		int index1;
		int index2;
		al=this.getInf();
		this.lineNum=this.al.size();
		
		for (int i=0;i<lineNum;i++) {
			if((this.al.get(i)+"").startsWith(taskname)) {
				index=(this.al.get(i)+"").indexOf("$");
				index0=(this.al.get(i)+"").indexOf("$",index+1);
				index1=(this.al.get(i)+"").indexOf("$",index0+1);
				index2=(this.al.get(i)+"").indexOf("$",index1+1);
				str0=(this.al.get(i)+"").substring(index2+1);
				return str0;
			}
		}
		return null;
	}
	//获取周期任务日期
    public String getInformationc5(String taskname) {
		String str0;
		int index;
		int index0;
		int index1;
		int index2;
		int index3;
		al=this.getInf();
		this.lineNum=this.al.size();
		
		for (int i=0;i<lineNum;i++) {
			if((this.al.get(i)+"").startsWith(taskname)) {
				index=(this.al.get(i)+"").indexOf("$");
				index0=(this.al.get(i)+"").indexOf("$",index+1);
				index1=(this.al.get(i)+"").indexOf("$",index0+1);
				index2=(this.al.get(i)+"").indexOf("$",index1+1);
				index3=(this.al.get(i)+"").indexOf("$",index2+1);
				str0=(this.al.get(i)+"").substring(index2+1,index3);
				return str0;
			}
		}
		return null;
	}
	//获取周期任务重复次数
	public String getInformation6(String taskname) {
		String str0;
		int index;
		int index0;
		int index1;
		int index2;
		int index3;
		int index4;
		al=this.getInf();
		this.lineNum=this.al.size();
		
		for (int i=0;i<lineNum;i++) {
			if((this.al.get(i)+"").startsWith(taskname)) {
				index=(this.al.get(i)+"").indexOf("$");
				index0=(this.al.get(i)+"").indexOf("$",index+1);
				index1=(this.al.get(i)+"").indexOf("$",index0+1);
				index2=(this.al.get(i)+"").indexOf("$",index1+1);
				index3=(this.al.get(i)+"").indexOf("$",index2+1);
				index4=(this.al.get(i)+"").indexOf("$",index3+1);
				str0=(this.al.get(i)+"").substring(index3+1,index4);
				return str0;
			}
		}
		return null;
	}
	//获取周期任务周期
	public String getInformation7(String taskname) {
		String str0;
		int index;
		int index0;
		int index1;
		int index2;
		int index3;
		int index4;
		al=this.getInf();
		this.lineNum=this.al.size();
		
		for (int i=0;i<lineNum;i++) {
			if((this.al.get(i)+"").startsWith(taskname)) {
				index=(this.al.get(i)+"").indexOf("$");
				index0=(this.al.get(i)+"").indexOf("$",index+1);
				index1=(this.al.get(i)+"").indexOf("$",index0+1);
				index2=(this.al.get(i)+"").indexOf("$",index1+1);
				index3=(this.al.get(i)+"").indexOf("$",index2+1);
				index4=(this.al.get(i)+"").indexOf("$",index3+1);
				str0=(this.al.get(i)+"").substring(index4+1);
				return str0;
			}
		}
		return null;
	}
	//获取列表全部内容
	public ArrayList getInf() {
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
		return al;
	}
	//获取特殊字符位置
	public int getLocation(int i,String mark) {
		return (this.al.get(i)+"").indexOf(mark);
	}
}
