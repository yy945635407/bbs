package org.bbs.dao;

import java.util.List;

import org.bbs.entity.Post;

import com.sun.org.apache.xml.internal.security.utils.UnsyncByteArrayOutputStream;

public interface IPostDao {
	public int getTotalCount();//查询总数据量
	
	public int getTotalCount(int userID);//查询某人的数据总量
	
	public int getTotalCount(String keyword);//查询喊某关键字的帖子的个数
	
	public int getTotalCountNotReviewed();//查询未审核帖子数量
	
	public List<Post> queryPostByPage(int currentPage,int pageSize);//当前页，每页显示数据条数
	
	public List<Post> queryPostForSbByPage(int currentPage,int pageSize,int userID);//当前页，每页显示数据条数
	
	public List<Post> queryUnReviewedPostByPage(int currentPage,int pageSize);//分页显示未审核帖子
	
	public List<Post> queryAllPosts();//查询全部帖子
	
	public boolean deletePostById(int id);//根据id删除帖子
	
	public boolean addPost(Post post);//增加帖子并返回是否成功
	
	public Post queryPostById(int id);//根据id查询并返回信息
	
	public List<Post> searchPostByTitle(String keyWord,int currentPage,int pageSize);//根据title搜索并分页显示
	
	public boolean reviewPost(int postID);//设置指定ID帖子为已审核状态
}
