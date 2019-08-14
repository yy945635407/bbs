package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.ICommentDao;
import org.bbs.dao.impl.CommentDaoImpl;
import org.bbs.entity.Comment;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.GetTime;
import org.bbs.util.ServletHelper;

//发布评论
/**
 * Servlet implementation class PublishCommentServlet
 */
@WebServlet("/PublishCommentServlet")
public class PublishCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishCommentServlet() {
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
		String content = request.getParameter("content").replace("\n", "<br/>");//将textarea中/n替换为<br/>（前端显示换行）
		String publishTime = GetTime.getTime();
		
		
		Comment comment = new Comment();
		comment.setPostID(postID);
		comment.setUserID(userID);
		comment.setContent(content);
		comment.setPublishTime(publishTime);
		
		ICommentDao commentDao = new CommentDaoImpl();
		commentDao.addComment(comment);
		
		request.getRequestDispatcher("QueryPostByID?postID="+ postID).forward(request, response);
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
