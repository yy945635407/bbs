package org.bbs.entity;

public class Comment {
	private int postID;
	private int commentID;
	private int userID;
	private String content;
	private String publishTime;
	public Comment() {
	}
	public Comment(int postID, int userID, String content, String publishTime) {
		this.postID = postID;
		this.userID = userID;
		this.content = content;
		this.publishTime = publishTime;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	@Override
	public String toString() {
		String string = this.commentID+"--"+this.postID+"--"+this.userID+"--"+this.content+"--"+this.publishTime;
		return string;
	}
}
