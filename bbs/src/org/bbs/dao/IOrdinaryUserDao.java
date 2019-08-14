package org.bbs.dao;

import java.util.List;

import org.bbs.entity.OrdinaryUser;

public interface IOrdinaryUserDao {
	public boolean register(String name, String pwd);
	
	public int getTotalCount();//查询总数据量
	
	public List<OrdinaryUser> queryOrdinaryUserByPage(int currentPage,int pageSize);//当前页，每页显示数据条数
	
	public List<OrdinaryUser> queryAllOrdinaryUsers();//查询全部用户
	
	public boolean updateOrdinaryUserById(int id, OrdinaryUser user);//根据id找到人后修改参数
			
	public boolean deleteOrdinaryUserById(int id);//根据id删除用户
	
	public boolean addOrdinaryUser(OrdinaryUser ordinaryUser);//增加用户并返回是否成功
	
	public OrdinaryUser queryOrdinaryUserById(int id);//根据id查询并返回信息
	
	public OrdinaryUser queryOrdinaryUserByName(String name);//根据Name查询并返回信息
}
