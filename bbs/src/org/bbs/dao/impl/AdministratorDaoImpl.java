package org.bbs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bbs.dao.IAdministratorDao;
import org.bbs.entity.Administrator;
import org.bbs.util.DBUtil;

public class AdministratorDaoImpl implements IAdministratorDao{
	private static String targetTable = "administrator";
	public boolean register(String name, String pwd){
		Administrator admin = new Administrator();
		admin.setUserName(name);
		admin.setUserPwd(pwd);
		return this.addAdministrator(admin);
	}
	public List<Administrator> queryAllAdministrators(){//查询全部用户
		String sql="select * from " + targetTable;
		List<Administrator> admins = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, null);	
		try {
			while(rs.next()){
				Administrator admin = new Administrator();
				admin.setUserID(rs.getInt("userID"));
				admin.setUserName(rs.getString("userName"));
				admin.setUserPwd(rs.getString("userPwd"));
				admins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return admins;
		} catch (Exception e) {
			e.printStackTrace();
			return admins;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		return admins;
	}
	
	public boolean updateAdministratorById(int id, Administrator admin){//根据id找到人后修改参数
		String sql="update " + targetTable + " set userName=?,userPwd=? where userID=?";
		Object[] params = {admin.getUserName(),admin.getUserPwd(), id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean deleteAdministratorById(int id){//根据id删除用户
		String sql="delete from "+ targetTable +" where userID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public boolean addAdministrator(Administrator admin){//增加用户并返回是否成功
		String sql="insert into "+ targetTable +" values(?,?,?)";
		Object[] params = {null,admin.getUserName(),admin.getUserPwd()};
		return DBUtil.executeUpdate(sql, params);
	}
	
	public Administrator queryAdministratorById(int id){//根据id查询并返回信息
		String sql="select * from "+ targetTable +" where userID=?";
		Object[] params = {id};
		List<Administrator> admins = new ArrayList<>();
		Administrator noUser = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Administrator admin = new Administrator();
				admin.setUserID(rs.getInt("userID"));
				admin.setUserName(rs.getString("userName"));
				admin.setUserPwd(rs.getString("userPwd"));
				admins.add(admin);
			}
			if(admins.size()==0){
				return noUser;
			}
			else{
				return admins.get(0);
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
	
	public Administrator queryAdministratorByName(String name){//根据Name查询并返回信息
		String sql="select * from "+ targetTable +" where binary userName=?";
		Object[] params = {name};
		List<Administrator> admins = new ArrayList<>();
		Administrator noUser = null;
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			if(rs.next()){
				Administrator admin = new Administrator();
				admin.setUserID(rs.getInt("userID"));
				admin.setUserName(rs.getString("userName"));
				admin.setUserPwd(rs.getString("userPwd"));
				admins.add(admin);
			}
			if(admins.size()==0){
				return noUser;
			}
			else{
				return admins.get(0);
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
