package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bbs.dao.IOrdinaryUserDao;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.*;
public class OrdinaryUserDaoImpl implements IOrdinaryUserDao{
	private static String targetTable = "user_test";
	public boolean register(String name, String pwd){
		OrdinaryUser user = new OrdinaryUser();
		user.setUserName(name);
		user.setUserPwd(pwd);
//		System.out.println(user);
		return this.addOrdinaryUser(user);
	}
	public int getTotalCount(){//查询总数据量
		String sql = "select count(1) from "+ targetTable;
		return DBUtil.getTotalCount(sql);
	}
	
	public List<OrdinaryUser> queryOrdinaryUserByPage(int currentPage,int pageSize){//当前页，每页显示数据条数
		List<OrdinaryUser> users = new ArrayList<>();
		String sql="select * from "+ targetTable +" limit ?,?";
		Object[] params={currentPage*pageSize,pageSize};
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()){
				OrdinaryUser user = new OrdinaryUser();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserSign(rs.getString("userSign"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return users;
	}
	
	public List<OrdinaryUser> queryAllOrdinaryUsers(){//查询全部用户
		String sql="select * from "+ targetTable;
		List<OrdinaryUser> users = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, null);	
		try {
			while(rs.next()){
				OrdinaryUser user = new OrdinaryUser();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserSign(rs.getString("userSign"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return users;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return users;
	}
	
	public boolean updateOrdinaryUserById(int id, OrdinaryUser user){//根据id找到人后修改参数
		String sql="update "+ targetTable +" set userName=?,userPwd=?,userSign=? where userID=?";
		Object[] params = {user.getUserName(),user.getUserPwd(), user.getUserSign(),id};
		return DBUtil.executeUpdate(sql, params);
	}
			
	public boolean deleteOrdinaryUserById(int id){//根据id删除用户
		String sql="delete from "+ targetTable +" where userID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addOrdinaryUser(OrdinaryUser user){//增加用户并返回是否成功
		String sql="insert into "+ targetTable +" values(?,?,?,?)";
		Object[] params = {null,user.getUserName(),user.getUserPwd(),user.getUserSign()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public OrdinaryUser queryOrdinaryUserById(int id){//根据id查询并返回信息
		String sql="select * from "+ targetTable +" where userID=?";
		Object[] params = {id};
		List<OrdinaryUser> users = new ArrayList<>();
		OrdinaryUser noUser = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				OrdinaryUser user = new OrdinaryUser();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserSign(rs.getString("userSign"));
				users.add(user);
			}
			if(users.size()==0){
				return noUser;
			}
			else{
				return users.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noUser;
		} catch (Exception e) {
			e.printStackTrace();
			return noUser;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
	
	public OrdinaryUser queryOrdinaryUserByName(String name){//根据name查询并返回信息
		String sql="select * from "+ targetTable +" where binary userName=?";
		Object[] params = {name};
		List<OrdinaryUser> users = new ArrayList<>();
		OrdinaryUser noUser = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				OrdinaryUser user = new OrdinaryUser();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setUserPwd(rs.getString("userPwd"));
				user.setUserSign(rs.getString("userSign"));
				users.add(user);
			}
			if(users.size()==0){
				return noUser;
			}
			else{
				return users.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return noUser;
		} catch (Exception e) {
			e.printStackTrace();
			return noUser;
		}finally{
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
	}
	}
}
