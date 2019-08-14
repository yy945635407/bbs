package org.bbs.dao;

import java.util.List;

import org.bbs.entity.History;

public interface IHistoryDao {
	public int getTotalCount();//查询总数据量
	
	public int getTotalCount(int userID);//查询某个用户的数据总量
	
	public int getTotalCount(int userID, int postID);//查询某个用户对某个帖子的历史数据数量
	
	public List<History> queryHistoryByPage(int currentPage,int pageSize);//当前页，每页显示数据条数
	
	public List<History> queryHistoryForSbByPage(int currentPage,int pageSize,int userID);//对某人的分页
	
	public List<History> queryAllHistories();//查询全部历史
	
	public boolean deleteHistoryById(int id);//根据id删除历史
	
	public boolean addHistory(History history);//增加历史并返回是否成功
	
	public History queryHistoryById(int id);//根据id历史并返回信息
}
