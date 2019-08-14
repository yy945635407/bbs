package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bbs.dao.ICommentDao;
import org.bbs.entity.Comment;
import org.bbs.util.DBUtil;


public class CommentDaoImpl implements ICommentDao{
	private static String targetTable = "comment_test";
	public int getTotalCount(){//查询总数据量
		String sql = "select count(1) from "+ targetTable;
		return DBUtil.getTotalCount(sql);
	}
	
	public int getTotalCount(int userID){
		String sql = "select count(1) from "+ targetTable +" where userID=?";
		Object[] params = {userID};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		int count = 0;
		try{
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return count;
	}
	
	public int getTotalCountForPost(int postID){//查询某帖子的评论总量
		String sql = "select count(1) from "+ targetTable +" where postID=?";
		Object[] params = {postID};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		int count = 0;
		try{
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return count;
	}
	
	public List<Comment> queryCommentByPage(int currentPage,int pageSize){//当前页，每页显示数据条数
		List<Comment> comments = new ArrayList<>();
		String sql="select * from "+ targetTable +" limit ?,? order by publishTime desc";
		Object[] params={currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Comment comment = new Comment();
				comment.setCommentID(rs.getInt("commentID"));
				comment.setPostID(rs.getInt("postID"));
				comment.setUserID(rs.getInt("userID"));
				comment.setContent(rs.getString("content"));
				comment.setPublishTime(rs.getString("publishTime"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return comments;
	}
	
	public List<Comment> queryCommentForSbByPage(int currentPage,int pageSize,int userID){//当前页，每页显示数据条数
		List<Comment> comments = new ArrayList<>();
		String sql="select * from "+ targetTable +" where userID=? limit ?,?";
		Object[] params={userID,currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Comment comment = new Comment();
				comment.setCommentID(rs.getInt("commentID"));
				comment.setPostID(rs.getInt("postID"));
				comment.setUserID(rs.getInt("userID"));
				comment.setContent(rs.getString("content"));
				comment.setPublishTime(rs.getString("publishTime"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return comments;
	}
	
	public List<Comment> queryCommentForPostByPage(int currentPage,int pageSize,int postID){//当前页，每页显示数据条数
		List<Comment> comments = new ArrayList<>();
		String sql="select * from "+ targetTable +" where postID=? order by publishTime desc limit ?,? ";//按时间顺序显示
		Object[] params={postID,currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Comment comment = new Comment();
				comment.setCommentID(rs.getInt("commentID"));
				comment.setPostID(rs.getInt("postID"));
				comment.setUserID(rs.getInt("userID"));
				comment.setContent(rs.getString("content"));
				comment.setPublishTime(rs.getString("publishTime"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return comments;
	}
	
	public List<Comment> queryAllComments(){//查询全部评论
		String sql="select * from "+ targetTable;
		List<Comment> comments = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, null);	
		try {
			while(rs.next()){
				Comment comment = new Comment();
				comment.setCommentID(rs.getInt("commentID"));
				comment.setPostID(rs.getInt("postID"));
				comment.setUserID(rs.getInt("userID"));
				comment.setContent(rs.getString("content"));
				comment.setPublishTime(rs.getString("publishTime"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			return comments;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return comments;
	}
	
	public boolean deleteCommentById(int id){//根据id删除评论
		String sql="delete from "+ targetTable +" where commentID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addComment(Comment comment){//增加评论并返回是否成功
		String sql="insert into "+ targetTable +" values(?,?,?,?,?)";
		Object[] params = {null,comment.getPostID(),comment.getUserID(),comment.getContent(),comment.getPublishTime()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public Comment queryCommentById(int id){//根据id查询并返回信息
		String sql="select * from "+ targetTable +" where commentID=?";
		Object[] params = {id};
		List<Comment> comments = new ArrayList<>();
		Comment noComment = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Comment comment = new Comment();
				comment.setCommentID(rs.getInt("commentID"));
				comment.setPostID(rs.getInt("postID"));
				comment.setUserID(rs.getInt("userID"));
				comment.setContent(rs.getString("content"));
				comment.setPublishTime(rs.getString("publishTime"));
				comments.add(comment);
			}
			if(comments.size()==0){
				return noComment;
			}
			else{
				return comments.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noComment;
		} catch (Exception e) {
			e.printStackTrace();
			return noComment;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
}
