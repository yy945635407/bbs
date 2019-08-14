package org.bbs.dao;

import java.util.List;

import org.bbs.entity.Like;

public interface ILikeDao {
	public int getTotalCount(int postID);//查询某帖子的总点赞量
	
	public boolean deleteLikeById(int id);//根据id删除点赞
	
	public boolean addLike(Like like);//增加点赞并返回是否成功
	
	public Like queryLike(int postID,int userID);//查询user是否点赞post
}
