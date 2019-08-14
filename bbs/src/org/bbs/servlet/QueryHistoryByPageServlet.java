package org.bbs.servlet;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.bbs.dao.IHistoryDao;
import org.bbs.dao.impl.HistoryDaoImpl;
import org.bbs.entity.History;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.Page;
import org.bbs.util.ServletHelper;
//查询某人的历史（分页显示）
/**
 * Servlet implementation class QueryHistoryByPageServlet
 */
@WebServlet("/QueryHistoryByPageServlet")
public class QueryHistoryByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryHistoryByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		IHistoryDao historyDao = new HistoryDaoImpl();

		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		int userID = user.getUserID();
		int totalCount = historyDao.getTotalCount(userID);
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<History> histories = historyDao.queryHistoryForSbByPage(currentPage, pageSize, userID);
	
//		for(History history:histories){
//			System.out.println(history);
//		}
		
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);

		request.setAttribute("histories", histories);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("history.jsp").forward(request, response);
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
