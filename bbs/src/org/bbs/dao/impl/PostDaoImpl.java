package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bbs.dao.IPostDao;
import org.bbs.entity.Post;
import org.bbs.util.DBUtil;

public class PostDaoImpl implements IPostDao{
	private static String targetTable = "post_test";
	public int getTotalCount(){//查询总数据量
		String sql = "select count(1) from "+ targetTable + " where reviewed=1";
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
	
	public int getTotalCount(String keyWord){
		String sql = "select count(1) from "+ targetTable +" where title like ? and reviewed=1";
		Object[] params = {'%'+keyWord+'%'};
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
	
	public int getTotalCountNotReviewed(){//查询未审核帖子数量
		String sql = "select count(1) from "+ targetTable +" where reviewed=0";
		Object[] params = {};
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
	
	public List<Post> queryPostByPage(int currentPage,int pageSize){//当前页，每页显示数据条数(只显示审核过的帖子)
		List<Post> posts = new ArrayList<>();
		String sql="select * from "+ targetTable +" where reviewed=1 limit ?,?";
		Object[] params={currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Post post = new Post();
				post.setPostID(rs.getInt("postID"));
				post.setUserID(rs.getInt("userID"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPublishTime(rs.getString("publishTime"));
				post.setReviewed(rs.getInt("reviewed"));
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return posts;
	}
	
	public List<Post> queryPostForSbByPage(int currentPage,int pageSize,int userID){//当前页，每页显示数据条数
		List<Post> posts = new ArrayList<>();
		String sql="select * from "+ targetTable +" where userID=? limit ?,?";
		Object[] params={userID,currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Post post = new Post();
				post.setPostID(rs.getInt("postID"));
				post.setUserID(rs.getInt("userID"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPublishTime(rs.getString("publishTime"));
				post.setReviewed(rs.getInt("reviewed"));
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return posts;
	}
	
	public List<Post> queryUnReviewedPostByPage(int currentPage,int pageSize){//分页显示未审核帖子
		List<Post> posts = new ArrayList<>();
		String sql="select * from "+ targetTable +" where reviewed=0 limit ?,?";
		Object[] params={currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Post post = new Post();
				post.setPostID(rs.getInt("postID"));
				post.setUserID(rs.getInt("userID"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPublishTime(rs.getString("publishTime"));
				post.setReviewed(rs.getInt("reviewed"));
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return posts;
	}
	
	public List<Post> queryAllPosts(){//查询全部帖子
		String sql="select * from "+ targetTable;
		List<Post> posts = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, null);	
		try {
			while(rs.next()){
				Post post = new Post();
				post.setPostID(rs.getInt("postID"));
				post.setUserID(rs.getInt("userID"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPublishTime(rs.getString("publishTime"));
				post.setReviewed(rs.getInt("reviewed"));
				posts.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return posts;
		} catch (Exception e) {
			e.printStackTrace();
			return posts;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return posts;
	}
	
	public boolean deletePostById(int id){//根据id删除帖子
		String sql="delete from "+ targetTable +" where postID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addPost(Post post){//增加帖子并返回是否成功
		String sql="insert into "+ targetTable +" values(?,?,?,?,?,?)";
//		System.out.println(post);
		Object[] params = {null,post.getUserID(),post.getTitle(),post.getContent(),post.getPublishTime(), post.getReviewed()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public Post queryPostById(int id){//根据id查询并返回信息
		String sql="select * from "+ targetTable +" where postID=?";
		Object[] params = {id};
		List<Post> posts = new ArrayList<>();
		Post noPost = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Post post = new Post();
				post.setPostID(rs.getInt("postID"));
				post.setUserID(rs.getInt("userID"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPublishTime(rs.getString("publishTime"));
				post.setReviewed(rs.getInt("reviewed"));
				posts.add(post);
			}
			if(posts.size()==0){
				return noPost;
			}
			else{
				return posts.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noPost;
		} catch (Exception e) {
			e.printStackTrace();
			return noPost;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
	
	public List<Post> searchPostByTitle(String keyWord,int currentPage,int pageSize){
		String sql="select * from "+ targetTable +" where title like ? and reviewed=1 limit ?,?";
		Object[] params = {'%'+keyWord+'%', currentPage*pageSize, pageSize};
//		System.out.println(sql);
		List<Post> posts = new ArrayList<>();
		List<Post> noPost = new ArrayList<>();
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Post post = new Post();
				post.setPostID(rs.getInt("postID"));
				post.setUserID(rs.getInt("userID"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPublishTime(rs.getString("publishTime"));
				post.setReviewed(rs.getInt("reviewed"));
//				System.out.println(post);
				posts.add(post);
			}
			if(posts.size()==0){
				return noPost;
			}
			else{
				return posts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noPost;
		} catch (Exception e) {
			e.printStackTrace();
			return noPost;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
	
	public boolean reviewPost(int postID){//设置指定ID帖子为已审核状态
		String sql = "update "+ targetTable +" set reviewed=1 where postID=?";
		Object[] params = {postID};
		return DBUtil.executeUpdate(sql, params);
	}
}
