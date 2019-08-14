package org.bbs.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.ILikeDao;
import org.bbs.dao.impl.LikeDaoImpl;
import org.bbs.entity.Like;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.GetTime;
import org.bbs.util.ServletHelper;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
//点赞(在查看帖子页面进行)
/**
 * Servlet implementation class AddLikeServlet
 */
@WebServlet("/AddLikeServlet")
public class AddLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		int postID = Integer.parseInt(request.getParameter("postID"));
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		int userID = user.getUserID();
		//获取当前系统时间为发布时间
		String likeTime = GetTime.getTime();
		
		Like like = new Like();
		like.setPostID(postID);
		like.setUserID(userID);
		like.setLikeTime(likeTime);
		
		ILikeDao likeDao = new LikeDaoImpl();
		likeDao.addLike(like);
		
		request.getRequestDispatcher("QueryPostByID?postID="+postID).forward(request, response);
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
