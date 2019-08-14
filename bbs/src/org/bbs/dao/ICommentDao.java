package org.bbs.dao;

import java.util.List;

import org.bbs.entity.Comment;

public interface ICommentDao {
	public int getTotalCount();//查询总数据量
	
	public int getTotalCount(int userID);//查询某人的数据总量
	
	public int getTotalCountForPost(int postID);//查询某帖子的评论总量
	
	public List<Comment> queryCommentByPage(int currentPage,int pageSize);//当前页，每页显示数据条数
	
	public List<Comment> queryCommentForSbByPage(int currentPage,int pageSize,int userID);//当前页，每页显示数据条数
	
	public List<Comment> queryCommentForPostByPage(int currentPage,int pageSize,int postID);//查询当前帖子所有评论当前页，每页显示数据条数
	
	public List<Comment> queryAllComments();//查询全部评论
	
	public boolean deleteCommentById(int id);//根据id删除评论
	
	public boolean addComment(Comment comment);//增加评论并返回是否成功
	
	public Comment queryCommentById(int id);//根据id查询并返回信息
}
