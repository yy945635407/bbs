package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bbs.dao.ILikeDao;
import org.bbs.entity.Like;
import org.bbs.util.DBUtil;

public class LikeDaoImpl implements ILikeDao{
	private static String targetTable = "like_";
	public int getTotalCount(int postID){//查询某帖子的总点赞量
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
	
	public boolean deleteLikeById(int id){//根据id删除点赞
		String sql="delete from "+ targetTable +" where likeID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addLike(Like like){//增加点赞并返回是否成功
		String sql="insert into "+ targetTable+" values(?,?,?,?)";
		Object[] params = {null,like.getPostID(),like.getUserID(),like.getLikeTime()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public Like queryLike(int postID,int userID){//根据id查询点赞并返回信息
		String sql="select * from "+ targetTable +" where postID=? and userID=?";
		Object[] params = {postID,userID};
		List<Like> likes = new ArrayList<>();
		Like noLike = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Like like = new Like();
				like.setLikeID(rs.getInt("likeID"));
				like.setPostID(rs.getInt("postID"));
				like.setUserID(rs.getInt("userID"));
				like.setLikeTime(rs.getString("likeTime"));
				likes.add(like);
			}
			if(likes.size()==0){
				return noLike;
			}
			else{
				return likes.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noLike;
		} catch (Exception e) {
			e.printStackTrace();
			return noLike;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
}
