package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.IPostDao;
import org.bbs.dao.impl.PostDaoImpl;
import org.bbs.entity.Post;
import org.bbs.util.Page;
import org.bbs.util.ServletHelper;

import net.sf.json.JSONObject;
//查询未审核的帖子
/**
 * Servlet implementation class QueryAllUnreviewedPostServlet
 */
@WebServlet("/QueryAllUnreviewedPostServlet")
public class QueryAllUnreviewedPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAllUnreviewedPostServlet() {
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
		List<Post> posts = postDao.queryUnReviewedPostByPage(currentPage, pageSize); //查询未审核的帖子
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
//		System.out.println(totalCount);
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		
		if(totalCount!=0){
			json.put("info", "yes");
			json.put("posts", posts);
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
