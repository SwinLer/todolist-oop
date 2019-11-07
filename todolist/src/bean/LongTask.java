package bean;
//长期任务对象
public class LongTask extends Task{
	private String date;
	private String type="长期任务";
	private String childtask;
	
	public LongTask(String name,String status,String content,
			String date,String childtask) {
		super(name,status,content);
		this.date=date;
		this.childtask=childtask;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChildtask() {
		return childtask;
	}

	public void setChildtask(String childtask) {
		this.childtask = childtask;
	}

	public String getType() {
		return type;
	}
}
