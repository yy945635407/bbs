package org.bbs.entity;

public class User {
	private int userID;
	private String userName;
	private String userPwd;
	public User() {
	}
	public User(String userName, String userPwd) {
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	@Override
	public String toString() {
		return this.getUserID()+"--"+this.getUserName()+"--"+this.getUserPwd();
	}
	
}
