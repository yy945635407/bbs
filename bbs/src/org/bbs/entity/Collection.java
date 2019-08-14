package org.bbs.entity;

public class Collection {
	private int collectionID;
	private int userID;
	private int postID;
	private String title;
	private String tag;
	public Collection() {
	}
	public Collection(int userID, int postID, String title, String tag) {
		this.userID = userID;
		this.postID = postID;
		this.title = title;
		this.tag = tag;
	}
	public int getCollectionID() {
		return collectionID;
	}
	public void setCollectionID(int collectionID) {
		this.collectionID = collectionID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		String string = this.collectionID + "--" + this.userID+"--"+this.postID+"--"+this.title+"--"+this.tag;
		return string;
	}
}
