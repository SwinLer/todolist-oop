package bean;
//任务清单对象
public class TaskList {
	private String listName;
	private String type;
	
	public TaskList(String listName,String type) {
		this.listName=listName;
		this.type=type;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
