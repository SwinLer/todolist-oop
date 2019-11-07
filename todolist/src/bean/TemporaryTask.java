package bean;
//临时任务对象
public class TemporaryTask extends Task{
	private String date;
	private String type="临时任务";
	public TemporaryTask(String name,String status,String content,String date) {
		super(name,status,content);
		this.date=date;
	}
	public String getType() {
		return type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
