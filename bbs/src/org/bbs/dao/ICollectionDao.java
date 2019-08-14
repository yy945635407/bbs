package org.bbs.dao;

import java.util.List;

import org.bbs.entity.Collection;

public interface ICollectionDao {
	public int getTotalCount();//查询总数据量
	
	public int getTotalCount(int userID);//查询某个用户的数据总量
	
	public List<Collection> queryCollectionByPage(int currentPage,int pageSize);//当前页，每页显示数据条数
	
	public List<Collection> queryCollectionForSbByPage(int currentPage,int pageSize,int userID);
	
	public List<Collection> queryAllCollections();//查询全部收藏
	
	public boolean deleteCollectionById(int id);//根据id删除收藏
	
	public boolean addCollection(Collection collection);//增加收藏并返回是否成功
	
	public Collection queryCollectionById(int id);//根据id查询并返回信息
	
	public boolean queryCollected(int userID, int postID);//查询是否收藏
	
	public Collection queryCollection(int userID, int postID);//根据user和post查询收藏
}
