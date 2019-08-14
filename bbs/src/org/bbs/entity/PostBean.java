package org.bbs.entity;

import java.util.ArrayList;
import java.util.List;

public class PostBean {
	private int postID; //当前帖子ID
	private String title; //当前帖子标题
	private OrdinaryUser author; //当前帖子作者
	private String publishTime; //帖子发布时间
	private int likeNum; //当前帖子点赞数量
	private boolean liked; //当前帖子是否被当前访问者点赞
	private boolean collected; //当前帖子是否被当前访问者收藏
	private String content; //当前帖子内容
	private List<CommentBean> commentBeans = new ArrayList<>(); //当前帖子的评论
	public PostBean() {
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
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
	public OrdinaryUser getAuthor() {
		return author;
	}
	public void setAuthor(OrdinaryUser author) {
		this.author = author;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public boolean isCollected() {
		return collected;
	}
	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<CommentBean> getCommentBeans() {
		return commentBeans;
	}
	public void setCommentBeans(List<CommentBean> commentBeans) {
		this.commentBeans = commentBeans;
	}
	
}
