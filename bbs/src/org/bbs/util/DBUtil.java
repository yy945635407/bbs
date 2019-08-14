package org.bbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBUtil {
	final static String URL = "jdbc:MySQL://localhost:3306/bbs?characterEncoding=utf-8";
	final static String USERNAME="root";
	final static String PWD="admin";
	public static Connection connection=null;
	public static PreparedStatement pstmt=null;
	//查询总数
	public static int getTotalCount(String sql){
		int count = -1;
		ResultSet rs = null;
		try {
			connection = getConnection();
			pstmt= createPrepareStatement(sql, null);
		
			rs= pstmt.executeQuery();
		if(rs.next()){
			count = rs.getInt(1);//数据数目
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return count;
	}
	public static void closeAll(ResultSet rs, Statement stmt,Connection connection){
		
			try {
				if(rs!=null)	rs.close();
				if(stmt!=null)	stmt.close();
				if(connection!=null)	connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
	}
	
	public static PreparedStatement createPrepareStatement(String sql, Object[] params) throws SQLException{
		pstmt = connection.prepareStatement(sql);
		if(params!=null){
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL,USERNAME,PWD);
	}
	
	public static boolean executeUpdate(String sql,Object[] params){//通用增删改
		
		try {
			connection = getConnection();
			
			pstmt = createPrepareStatement(sql,params);
			
			int rs = pstmt.executeUpdate();
			
			return rs!=0?true:false;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ResultSet executeQuery(String sql,Object[] params){//通用查,返回结果集合
		ResultSet rs=null;
		try {
			connection = getConnection();
			
			pstmt = createPrepareStatement(sql,params);
//			System.out.println(pstmt);
			rs = pstmt.executeQuery();
//			System.out.println(pstmt);
			return rs;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
