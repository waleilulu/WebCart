package model.entity;

public class User {
	
	/*這個User.java，是entity(實體)，實際從資料庫撈出來的數據*/
	private Integer userId;
	private String username;
	private String password;
	private Integer priority;
	
	/*精靈產生: 右鍵➔Source➔getter & setter*/
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
