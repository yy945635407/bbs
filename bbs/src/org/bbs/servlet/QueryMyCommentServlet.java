package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.entity.Comment;
import org.bbs.entity.OrdinaryUser;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.util.Page;
import org.bbs.util.ServletHelper;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryMyCommentServlet
 */
@WebServlet("/QueryMyCommentServlet")
public class QueryMyCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryMyCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		ICommentDao commentDao = new CommentDaoImpl();
		
		OrdinaryUser visitor = ServletHelper.getVisitor(request, response);
		if(visitor==null) return;
		int userID = visitor.getUserID();
		int totalCount = commentDao.getTotalCount(userID);
		
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
		
		List<Comment> comments = commentDao.queryCommentForSbByPage(currentPage, pageSize, userID);
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		if(totalCount!=0){
			json.put("info", "yes");
			json.put("comments", comments);
			json.put("page", page);
		}else{
			json.put("info", "no");
			json.put("page", page);
		}
		out.print(json);
		out.close();
		
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
