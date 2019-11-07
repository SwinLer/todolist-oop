package bean;
//任务抽象类
public abstract class Task {
	private String name;
	private String status;
	private String content;
	
	public Task(String name,String status,String content) {
		this.name=name;
		this.status=status;
		this.content=content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
