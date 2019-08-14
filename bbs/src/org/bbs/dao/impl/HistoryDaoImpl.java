package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bbs.dao.IHistoryDao;
import org.bbs.entity.History;
import org.bbs.util.DBUtil;


public class HistoryDaoImpl implements IHistoryDao{
	private static String targetTable = "history_test";
	public int getTotalCount(){//查询总数据量
		String sql = "select count(1) from "+ targetTable;
		return DBUtil.getTotalCount(sql);
	}
	
	public int getTotalCount(int userID){//查询某人的总数据量
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
	
	public int getTotalCount(int userID, int postID){//查询某人对某个帖子的历史数据量
		String sql = "select count(1) from "+ targetTable +" where userID=? and postID=?";
		Object[] params = {userID, postID};
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
	
	
	public List<History> queryHistoryByPage(int currentPage,int pageSize){//当前页，每页显示数据条数
		List<History> histories = new ArrayList<>();
		String sql="select * from "+ targetTable +" limit ?,?";
		Object[] params={currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				History history = new History();
				history.setHistoryID(rs.getInt("historyID"));
				history.setUserID(rs.getInt("userID"));
				history.setPostID(rs.getInt("postID"));
				histories.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return histories;
	}
	
	public List<History> queryHistoryForSbByPage(int currentPage,int pageSize,int userID){//当前页，每页显示数据条数，目标用户
		List<History> histories = new ArrayList<>();
		String sql="select * from "+ targetTable +" where userID=? limit ?,?";
		Object[] params={userID,currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				History history = new History();
				history.setHistoryID(rs.getInt("historyID"));
				history.setUserID(rs.getInt("userID"));
				history.setPostID(rs.getInt("postID"));
				histories.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return histories;
	}
	public List<History> queryAllHistories(){//查询全部历史
		String sql="select * from "+ targetTable;
		List<History> histories = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, null);	
		try {
			while(rs.next()){
				History history = new History();
				history.setHistoryID(rs.getInt("historyID"));
				history.setUserID(rs.getInt("userID"));
				history.setPostID(rs.getInt("postID"));
				histories.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return histories;
		} catch (Exception e) {
			e.printStackTrace();
			return histories;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return histories;
	}
	
	public boolean deleteHistoryById(int id){//根据id删除历史
		String sql="delete from "+ targetTable +" where historyID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addHistory(History history){//增加历史并返回是否成功
		String sql="insert into "+ targetTable +" values(?,?,?)";
		Object[] params = {null,history.getUserID(),history.getPostID()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public History queryHistoryById(int id){//根据id历史并返回信息
		String sql="select * from "+ targetTable +" where historyID=?";
		Object[] params = {id};
		List<History> histories = new ArrayList<>();
		History noHistory = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				History history = new History();
				history.setHistoryID(rs.getInt("historyID"));
				history.setUserID(rs.getInt("userID"));
				history.setPostID(rs.getInt("postID"));
				histories.add(history);
			}
			if(histories.size()==0){
				return noHistory;
			}
			else{
				return histories.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noHistory;
		} catch (Exception e) {
			e.printStackTrace();
			return noHistory;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
}
