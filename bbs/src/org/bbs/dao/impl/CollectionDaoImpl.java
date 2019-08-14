package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bbs.dao.ICollectionDao;
import org.bbs.entity.Collection;
import org.bbs.util.DBUtil;

public class CollectionDaoImpl implements ICollectionDao{
	private static String targetTable = "collection";
	public int getTotalCount(){//查询总数据量
		String sql = "select count(1) from "+targetTable;
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
	
	public List<Collection> queryCollectionByPage(int currentPage,int pageSize){//当前页，每页显示数据条数
		List<Collection> collections = new ArrayList<>();
		String sql="select * from "+ targetTable +" limit ?,?";
		Object[] params={currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Collection collection = new Collection();
				collection.setCollectionID(rs.getInt("collectionID"));
				collection.setUserID(rs.getInt("userID"));
				collection.setPostID(rs.getInt("postID"));
				collection.setTitle(rs.getString("title"));
				collection.setTag(rs.getString("tag"));
				collections.add(collection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return collections;
	}
	
	public List<Collection> queryCollectionForSbByPage(int currentPage,int pageSize,int userID){//当前页，每页显示数据条数
		List<Collection> collections = new ArrayList<>();
		String sql="select * from "+ targetTable +" where userID=? limit ?,?";
		Object[] params={userID,currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				Collection collection = new Collection();
				collection.setCollectionID(rs.getInt("collectionID"));
				collection.setUserID(rs.getInt("userID"));
				collection.setPostID(rs.getInt("postID"));
				collection.setTitle(rs.getString("title"));
				collection.setTag(rs.getString("tag"));
				collections.add(collection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return collections;
	}
	
	public List<Collection> queryAllCollections(){//查询全部收藏
		String sql="select * from "+ targetTable;
		List<Collection> collections = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, null);	
		try {
			while(rs.next()){
				Collection collection = new Collection();
				collection.setCollectionID(rs.getInt("collectionID"));
				collection.setUserID(rs.getInt("userID"));
				collection.setPostID(rs.getInt("postID"));
				collection.setTitle(rs.getString("title"));
				collection.setTag(rs.getString("tag"));
				collections.add(collection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return collections;
		} catch (Exception e) {
			e.printStackTrace();
			return collections;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return collections;
	}
	
	public boolean deleteCollectionById(int id){//根据id删除收藏
		String sql="delete from "+ targetTable +" where collectionID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addCollection(Collection collection){//增加收藏并返回是否成功
		String sql="insert into "+ targetTable +" values(?,?,?,?,?)";
		Object[] params = {null,collection.getUserID(),collection.getPostID(),collection.getTitle(),collection.getTag()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public Collection queryCollectionById(int id){//根据id查询并返回信息
		String sql="select * from "+ targetTable +" where collectionID=?";
		Object[] params = {id};
		List<Collection> collections = new ArrayList<>();
		Collection noCollection = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Collection collection = new Collection();
				collection.setCollectionID(rs.getInt("collectionID"));
				collection.setUserID(rs.getInt("userID"));
				collection.setPostID(rs.getInt("postID"));
				collection.setTitle(rs.getString("title"));
				collection.setTag(rs.getString("tag"));
				collections.add(collection);
			}
			if(collections.size()==0){
				return noCollection;
			}
			else{
				return collections.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noCollection;
		} catch (Exception e) {
			e.printStackTrace();
			return noCollection;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
	public boolean queryCollected(int userID, int postID){//查询是否收藏
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
		return count>0?true:false;
	}
	public Collection queryCollection(int userID, int postID){//根据user和post查询收藏
		String sql="select * from "+ targetTable +" where userID=? and postID=?";
		Object[] params = {userID, postID};
		List<Collection> collections = new ArrayList<>();
		Collection noCollection = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Collection collection = new Collection();
				collection.setCollectionID(rs.getInt("collectionID"));
				collection.setUserID(rs.getInt("userID"));
				collection.setPostID(rs.getInt("postID"));
				collection.setTitle(rs.getString("title"));
				collection.setTag(rs.getString("tag"));
				collections.add(collection);
			}
			if(collections.size()==0){
				return noCollection;
			}
			else{
				return collections.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noCollection;
		} catch (Exception e) {
			e.printStackTrace();
			return noCollection;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}	
	}
}
