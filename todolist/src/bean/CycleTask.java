package bean;
//周期任务对象
public class CycleTask extends Task{
	private String date;
	private String times;
	private String cycle;
	private String type="周期任务";
	
	public CycleTask(String name,String status,String content,
			String date,String times,String cycle) {
		super(name,status,content);
		this.date=date;
		this.times=times;
		this.cycle=cycle;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getType() {
		return type;
	}
}
