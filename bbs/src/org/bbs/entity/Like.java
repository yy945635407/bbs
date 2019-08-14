package org.bbs.entity;

public class Like {
	private int likeID;
	private int postID;
	private int userID;
	private String likeTime;
	public Like() {
	}
	public Like(int postID, int userID, String likeTime) {
		this.postID = postID;
		this.userID = userID;
		this.likeTime = likeTime;
	}
	public int getLikeID() {
		return likeID;
	}
	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(String likeTime) {
		this.likeTime = likeTime;
	}
	@Override
	public String toString() {
		String string = this.likeID+"--"+this.postID+"--"+this.userID+"--"+this.likeTime;
		return string;
	}
}
