package org.bbs.dao;

import java.util.List;

import org.bbs.entity.Administrator;

public interface IAdministratorDao {
	public boolean register(String name, String pwd);
	
	public List<Administrator> queryAllAdministrators();//查询全部用户
	
	public boolean updateAdministratorById(int id, Administrator admin);//根据id找到人后修改参数
	
	public boolean deleteAdministratorById(int id);//根据id删除用户
	
	public boolean addAdministrator(Administrator admin);//增加用户并返回是否成功
	
	public Administrator queryAdministratorById(int id);//根据id查询并返回信息
	
	public Administrator queryAdministratorByName(String name);//根据Name查询并返回信息
}
