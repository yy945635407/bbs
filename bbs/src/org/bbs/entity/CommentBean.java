package org.bbs.entity;

public class CommentBean {
	private OrdinaryUser commenter;
	private String content;
	private String publishTime;
	public CommentBean() {
	}
	public OrdinaryUser getCommenter() {
		return commenter;
	}
	public void setCommenter(OrdinaryUser commenter) {
		this.commenter = commenter;
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
		String string = commenter+"--"+content+"--"+publishTime;
		return string;
	}
}
