package org.bbs.entity;


public class Post {
	private int postID;
	private int userID;
	private String title;
	private String content;
	private String publishTime;
	private int reviewed;
	public Post() {
	}
	public Post(int userID, String title, String content, String publishTime) {
		this.userID = userID;
		this.title = title;
		this.content = content;
		this.publishTime = publishTime;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public int getReviewed(){
		return this.reviewed;
	}
	public void setReviewed(int reviewed){
		this.reviewed = reviewed;
	}
	@Override
	public String toString() {
		String string = this.postID+"--"+this.userID+"--"+this.title+"--"+this.content+"--"+this.publishTime+"--"+this.reviewed;
		return string;
	}
}
