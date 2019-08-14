package org.bbs.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.IPostDao;
import org.bbs.dao.impl.PostDaoImpl;
import org.bbs.entity.OrdinaryUser;
import org.bbs.entity.Post;
import org.bbs.util.GetTime;
import org.bbs.util.ServletHelper;
//发布帖子
/**
 * Servlet implementation class PublishPostServlet
 */
@WebServlet("/PublishPostServlet")
public class PublishPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);

		//验证登陆
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		int userID = user.getUserID();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//获取当前系统时间为发布时间
		String publishTime = GetTime.getTime();
		
		Post post = new Post();
		post.setUserID(userID);
		post.setTitle(title);
		post.setContent(content);
		post.setPublishTime(publishTime);
		post.setReviewed(0);//默认未审核，需由管理员审核后发布
		IPostDao postDao = new PostDaoImpl();
//		System.out.println(post);
		if(postDao.addPost(post) == true){//添加成功
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
