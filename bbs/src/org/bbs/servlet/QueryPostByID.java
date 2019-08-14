package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.bbs.dao.ICollectionDao;
import org.bbs.dao.impl.OrdinaryUserDaoImpl;
import org.bbs.entity.Administrator;
import org.bbs.entity.Comment;
import org.bbs.entity.CommentBean;
import org.bbs.entity.History;
import org.bbs.entity.OrdinaryUser;
import org.bbs.entity.Post;
import org.bbs.entity.PostBean;
import org.bbs.entity.Recommend;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.util.Page;
import org.bbs.util.ServletHelper;
import org.bbs.util.UserCF;

import com.sun.org.apache.bcel.internal.generic.NEW;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryPostByID
 */
@WebServlet("/QueryPostByID")
public class QueryPostByID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryPostByID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		int currentPage,pageSize;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			currentPage=0;
		}
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			pageSize = 6;
		} 
		int postID = Integer.parseInt(request.getParameter("postID"));
		
		IPostDao postDao = new PostDaoImpl();
		ICommentDao commentDao = new CommentDaoImpl();
		IOrdinaryUserDao ordinaryUserDao = new OrdinaryUserDaoImpl();
		IHistoryDao historyDao = new HistoryDaoImpl();
		ICollectionDao collectionDao = new CollectionDaoImpl();
		ILikeDao likeDao = new LikeDaoImpl();
//		Recommend recommend = new Recommend();
		
		OrdinaryUser user = (OrdinaryUser) request.getSession().getAttribute("user");
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
//		System.out.println(user==null);
//		System.out.println(admin==null);
		if(user==null&&admin==null){
			request.getSession().setAttribute("timeout", "登陆超时请重新登陆");
			response.sendRedirect("signin.jsp");
			return;
		}
		Post post = postDao.queryPostById(postID); //从数据库获取post
		OrdinaryUser author = ordinaryUserDao.queryOrdinaryUserById(post.getUserID());//获取作者
		int totalCount = commentDao.getTotalCountForPost(postID);//评论分页显示总个数
		List<Comment> comments = commentDao.queryCommentForPostByPage(currentPage, pageSize, postID);//获取所有评论信息
		PostBean postBean = new PostBean();//传输到post.jsp的bean
		postBean.setPostID(postID);
		postBean.setTitle(post.getTitle());
		postBean.setAuthor(author);
		postBean.setContent(post.getContent());
		postBean.setPublishTime(post.getPublishTime());
		
		if(user!=null){//当前为用户访问
		boolean collected = collectionDao.queryCollected(user.getUserID(), postID); //查询是否收藏当前帖子
		postBean.setCollected(collected);
		boolean liked = likeDao.queryLike(postID, user.getUserID())==null?false:true; // 查询当前帖子是否被当前访问者点赞
		postBean.setLiked(liked);
//		System.out.println(collected);
//		System.out.println(liked);
		int likeNum = likeDao.getTotalCount(postID);//获取当前帖子的总点赞数目
		postBean.setLikeNum(likeNum);
		
		//添加历史纪录
		History history = new History();
		OrdinaryUser visitor = ServletHelper.getVisitor(request, response);
		if(visitor==null) return;
		history.setPostID(postID);
		history.setUserID(visitor.getUserID());
//		System.out.println(history);
		historyDao.addHistory(history);//增添历史纪录
		
//		//获取推荐列表
//		List<Integer> recommendedPostIDs = recommend.getRecommend(ordinaryUserDao.getTotalCount(), postDao.getTotalCount(), user.getUserID(), 3, 5);
//		List<Post> recommendPosts = new ArrayList<>();
//		for(int i:recommendedPostIDs){
//			recommendPosts.add(postDao.queryPostById(i));
//		}
//		request.setAttribute("recommendPosts", recommendPosts);
		}
		List<CommentBean> commentBeans = new ArrayList<>();
		for(Comment comment:comments){//添加所有评论者信息
			CommentBean commentBean = new CommentBean();
			OrdinaryUser commenter = ordinaryUserDao.queryOrdinaryUserById(comment.getUserID());
			commentBean.setCommenter(commenter);
			commentBean.setContent(comment.getContent());
			commentBean.setPublishTime(comment.getPublishTime());
			commentBeans.add(commentBean);
		}
		postBean.setCommentBeans(commentBeans);
		
		
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);

//		PrintWriter out = response.getWriter();
//		JSONObject json = new JSONObject();
//		
//		if(totalCount!=0){
//			json.put("info", "yes");
//			json.put("postBean", postBean);
//			json.put("page", page);
//		}else{
//			json.put("info", "no");
//			json.put("page", page);
//		}
//		out.print(json);
//		out.close();

//		int[] recommendedPosts =  rec.recommend(5);
		
		request.getSession().setAttribute("isAdmin", admin==null?false:true);
		request.getSession().setAttribute("postBean", postBean);
		request.getSession().setAttribute("page", page);
		
		response.sendRedirect("post.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
