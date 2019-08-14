package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import net.sf.json.JSONObject;
//搜索帖子(搜索结果分页显示)
/**
 * Servlet implementation class QueryPostServlet
 */
@WebServlet("/QueryPostServlet")
public class QueryPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		IPostDao postDao = new PostDaoImpl();
		
		String keyWord = request.getParameter("keyWord");//获取要查询的标题信息
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
		int totalCount = postDao.getTotalCount(keyWord);
		List<Post> posts = postDao.searchPostByTitle(keyWord,currentPage,pageSize);
//		for(Post p:posts){
//			System.out.println(p);
//		}
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setPageSize(pageSize);
		page.setCurrentPage(currentPage);
//		System.out.println(page);
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
//		System.out.println(123);
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
