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

/**
 * Servlet implementation class QueryAllPostServlet
 */
@WebServlet("/QueryAllPostServlet")
public class QueryAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAllPostServlet() {
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
		
//		System.out.println(currentPage+"--"+pageSize);
		
		IPostDao postDao = new PostDaoImpl();
		int totalCount = postDao.getTotalCount();
		List<Post> posts = postDao.queryPostByPage(currentPage, pageSize);
		
//		PrintWriter out = response.getWriter();
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("totalPages", totalCount);
//		jsonObject.put("posts", posts);
//		out.print(jsonObject);
//		out.close();
//		for(Post p:posts){
//			System.out.println(p);
//		}
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
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
