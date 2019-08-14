package org.bbs.servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.entity.Collection;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.Page;
import org.bbs.util.ServletHelper;

import net.sf.json.JSONObject;
//查询我的收藏（分页显示）
/**
 * Servlet implementation class QueryCollectionServlet
 */
@WebServlet("/QueryMyCollectionServlet")
public class QueryMyCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryMyCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		ICollectionDao collectionDao = new CollectionDaoImpl();
		
		OrdinaryUser visitor = ServletHelper.getVisitor(request, response);
		if(visitor==null) return;
		int userID = visitor.getUserID();
		int totalCount = collectionDao.getTotalCount(userID);
		
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
		
		List<Collection> collections = collectionDao.queryCollectionForSbByPage(currentPage, pageSize, userID);
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
//		for(Collection c:collections){
//			System.out.println(c);
//		}
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		if(totalCount!=0){
			json.put("info", "yes");
			json.put("collections", collections);
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
