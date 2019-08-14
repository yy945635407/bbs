package org.bbs.entity;

import com.sun.org.apache.xpath.internal.axes.SelfIteratorNoPredicate;

public class History {
	private int historyID;
	private int userID;
	private int postID;
	public History() {
	}
	public History(int userID, int postID) {
		this.userID = userID;
		this.postID = postID;
	}
	public int getHistoryID() {
		return historyID;
	}
	public void setHistoryID(int historyID) {
		this.historyID = historyID;
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
	@Override
	public String toString() {
		String string = this.historyID+"--"+this.userID+"--"+this.postID;
		return string;
	}
}
