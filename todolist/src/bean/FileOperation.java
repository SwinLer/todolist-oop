package bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//文件操作器
public class FileOperation {
	public FileOperation() {}
	//文件创建
	public File createFile(String filename){
		File listfile=new File(filename);
		if(!listfile.exists()) {
			try {
				listfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listfile;
	}
	//文件追加行
	public void appendFile(File listfile,String content) throws IOException{
		FileWriter writer = new FileWriter(listfile, true);
		writer.write(content);
		writer.close();
	}
	//文件间任务转移
	public void moveTask(String source,String order) throws IOException {
		File sf=this.createFile(source);
		File of=this.createFile(order);
		ArrayList<String> temp=new ArrayList<String>();
		temp=this.readFileLine(source);
		for(int i=0;i<temp.size();i++) {
			this.appendFile(of, temp.get(i)+"\n");
		}
		FileWriter writer = new FileWriter(sf);
		writer.write("");
		writer.close();
	}
	//文件间任务复制
	public void copyTask(String source,String order) throws IOException {
		File sf=this.createFile(source);
		File of=this.createFile(order);
		ArrayList<String> temp=new ArrayList<String>();
		temp=this.readFileLine(source);
		for(int i=0;i<temp.size();i++) {
			this.appendFile(of, temp.get(i)+"\n");
		}
	}
	//任务清单文件更改任务类型
	//获取文件内容更改后写入源文件
	public void modifyLine(String file,String line,String newtype) throws IOException {
		File listfile=new File(file);
		ArrayList<String> temp=new ArrayList<String>();
		String str=null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			while((str=br.readLine())!=null) {
				str= str.trim();
				if(!str.startsWith(line)) {
					temp.add(str+"\n");
				}else {
					temp.add(line+"$"+newtype+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int n=0;n<temp.size();n++) {
			if(0==n) {
				FileWriter writer = new FileWriter(listfile);
				writer.write(temp.get(n));
				writer.close();
			}else {
				this.appendFile(listfile, temp.get(n));
			}
		}
	}
	//任务文件更改
	public void modifyTaskLine(String file,String line,String newstatus,String newcontent,
			String newdate) throws IOException {
		File listfile=new File(file);
		ArrayList<String> temp=new ArrayList<String>();
		String str=null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			//寻找更改行
			while((str=br.readLine())!=null) {
				str= str.trim();
				if(!str.startsWith(line)) {
					temp.add(str+"\n");
				}else {
					int index=str.indexOf("$");
					int index0=str.indexOf("$",index+1);
					//判断文件类型获取更改后新内容
					if("临时任务".equals(str.substring(index+1,index0))) {
						temp.add(line+"$临时任务$"+newstatus+"$"+newcontent+"$"+newdate+"\n");
					}else if("周期任务".equals(str.substring(index+1,index0))) {
						int index1=str.indexOf("$",index0+1);
						int index2=str.indexOf("$",index1+1);
						int index3=str.indexOf("$",index2+1);
						temp.add(line+"$周期任务$"+newstatus+"$"+newcontent+"$"+newdate+str.substring(index3+1)+"\n");
					}else {
						temp.add(line+"$长期任务$"+newstatus+"$"+newcontent+"$"+newdate+"\n");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//原文件重新写入新内容
		for(int n=0;n<temp.size();n++) {
			if(0==n) {
				FileWriter writer = new FileWriter(listfile);
				writer.write(temp.get(n));
				writer.close();
			}else {
				this.appendFile(listfile, temp.get(n));
			}
		}
	}
	//文件搜索特定行并返回
	public String searchLine(String file,String linename) throws FileNotFoundException {
		File listfile=new File(file);
		String str;
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			while((str=br.readLine())!=null) {
				str= str.trim();
				if(str.startsWith(linename)) {
					return str;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//读取文件全部内容分行存入ArrayList并返回
	public ArrayList readFileLine(String filename) throws IOException {
		ArrayList<String> al=new ArrayList<String>();
		String str;
		BufferedReader bf = new BufferedReader(new FileReader(filename));
		try {
			while((str=bf.readLine())!=null) {
				al.add(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bf.close();
		return al;
	}
	//文件行计数
	public int countLine(String file,String mark) throws IOException {
		BufferedReader br;
		String regex = mark;
		int count=0;
		Pattern pattern = Pattern.compile(regex);
		
		br = new BufferedReader(new FileReader(file));
		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine()) != null) {
			sb.append(str);
		}
		Matcher matcher = pattern.matcher(sb);
		while(matcher.find()) {
			count++;
		}
		br.close();
		return count;
	}
	//文件删除行
	public void deleteLine(String file,String listname) throws IOException {
		ArrayList<String> temp=new ArrayList<String>();
		String str=null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			while((str=br.readLine())!=null) {
				str= str.trim();
				if(!str.startsWith(listname)) {
					temp.add(str+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File listfile=new File(file);
		
		for(int n=0;n<temp.size();n++) {
			if(0==n) {
				FileWriter writer = new FileWriter(listfile);
				writer.write(temp.get(n));
				writer.close();
				
			}else {
				this.appendFile(listfile, temp.get(n));
			}
		}
	}
	//文件删除
	public void deleteFile(String file) {
		File taskfile=new File(file);
		System.out.println(taskfile.delete());
	}
	//根据名字文件行排序
	public void sortByName(String files,String fileo) throws IOException {
		ArrayList<String> temp=new ArrayList<String>();
		String str=null;
		BufferedReader br = new BufferedReader(new FileReader(files));
		try {
			while((str=br.readLine())!=null) {
				str= str.trim();
				temp.add(str+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(temp);
		File newfile=this.createFile(fileo);
		
		for(int n=0;n<temp.size();n++) {
			if(0==n) {
				FileWriter writer = new FileWriter(newfile);
				writer.write(temp.get(n));
				writer.close();
			}else {
				this.appendFile(newfile, temp.get(n));
			}
		}
	}
	//根据名字长度文件行排序
	public void sortByNameLength(String files,String fileo) throws IOException {
		ArrayList<String> temp=new ArrayList<String>();
		ArrayList<String> temp0=new ArrayList<String>();
		Map<Integer,ArrayList<String>> map=new HashMap<Integer,ArrayList<String>>();
		String str=null;
		String str0=null;
		
		BufferedReader br = new BufferedReader(new FileReader(files));
		try {
			while((str=br.readLine())!=null) {
				str= str.trim();
				temp.add(str+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//桶排
		for(int n=0;n<temp.size();n++) {
			str0=temp.get(n).substring(0, temp.get(n).indexOf("$"));
			ArrayList<String> t=new ArrayList<String>();
			if(map.containsKey(str0.length())) {
				for(int i=0;i<(map.get(str0.length())).size();i++) {
					t.add(map.get(str0.length()).get(i));
				}
			}
			t.add(temp.get(n));
			map.put(str0.length(),t);
		}
		Set<Integer> set=map.keySet();
		Object[] arr=set.toArray();
		Arrays.sort(arr);
		
		for(int n=0;n<map.size();n++) {
			String s;
			for(int i=0;i<map.get(arr[n]).size();i++) {
				temp0.add(map.get(arr[n]).get(i));
			}
		}
		File newfile=this.createFile(fileo);
		for(int n=0;n<temp0.size();n++) {
			if(0==n) {
				FileWriter writer = new FileWriter(newfile);
				writer.write(temp0.get(n));
				writer.close();
			}else {
				this.appendFile(newfile, temp0.get(n));
			}
		}
	}
}
