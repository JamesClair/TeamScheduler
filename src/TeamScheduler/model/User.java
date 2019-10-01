/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.model;


/**
 *
 * @author james.clair
 */
public class User {
	private int userId;
	private String userName;
	private String password;
	private int active;
	private String createDate;
	private String createdBy; 
	private String lastUpdate; 
	private String lastUpdateBy; 
	
	public User(int userId, String userName, String password, int active, String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdateBy;
	}

	@Override
	public String toString() {
		return "userId: " + userId +
			", userName: " + userName +
			", password: " + password +
			", active: " + active +
			", createDate: " + createDate +
			", createdBy: " + createdBy +
			", lastUpdate: " + lastUpdate +
			", lastUpdateBy: " + lastUpdateBy;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getActive() {
		return active;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	
}