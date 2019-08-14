package org.bbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.entity.Post;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.util.Page;
import org.bbs.util.ServletHelper;
/**
 * Servlet implementation class ShowUnreviewedPostServlet
 */
@WebServlet("/ShowUnreviewedPostServlet")
public class ShowUnreviewedPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUnreviewedPostServlet() {
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
		IPostDao postDao = new PostDaoImpl();
		int totalCount = postDao.getTotalCountNotReviewed();
		List<Post> posts = postDao.queryUnReviewedPostByPage(currentPage, pageSize);
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
		request.setAttribute("posts", posts);
		request.setAttribute("page", page);
		request.getRequestDispatcher("unreviewedpost.jsp").forward(request, response);
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
