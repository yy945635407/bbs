package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.ILikeDao;
import org.bbs.dao.impl.LikeDaoImpl;
import org.bbs.util.ServletHelper;
//查询某个帖子的点赞总数
/**
 * Servlet implementation class QueryLikenumServlet
 */
@WebServlet("/QueryLikenumServlet")
public class QueryLikenumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryLikenumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		ILikeDao likeDao = new LikeDaoImpl();
		int postID = Integer.parseInt(request.getParameter("postID"));
		int likenum = likeDao.getTotalCount(postID);
		request.setAttribute("likenum", likenum);
		request.getRequestDispatcher("post.jsp").forward(request, response);
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
