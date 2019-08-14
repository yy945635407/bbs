package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.entity.Like;
import org.bbs.entity.OrdinaryUser;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.util.ServletHelper;
//取消点赞(查看某一帖子页面)
/**
 * Servlet implementation class RemoveLikeServlet
 */
@WebServlet("/RemoveLikeServlet")
public class RemoveLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveLikeServlet() {
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
		ILikeDao likeDao = new LikeDaoImpl();
		Like like = likeDao.queryLike(postID, userID);
		if(like!=null){
			likeDao.deleteLikeById(like.getLikeID());
		}
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
