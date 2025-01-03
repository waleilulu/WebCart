package model.dto;

public class UserDto {
	
	/*UserDto.java這個資料是要給前端的*/
	/*這裡的命名也可以改變，增加安全性*/
	private Integer userId;
	private String username;
	//private String password; //在UserDto這邊不給前端password
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
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
}
