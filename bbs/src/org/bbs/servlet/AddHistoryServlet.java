package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.RequestGroupInfo;
import org.bbs.dao.IHistoryDao;
import org.bbs.dao.impl.HistoryDaoImpl;
import org.bbs.entity.History;
import org.bbs.util.ServletHelper;
//添加历史记录
/**
 * Servlet implementation class AddHistoryServlet
 */
@WebServlet("/AddHistoryServlet")
public class AddHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		int postID = Integer.parseInt(request.getParameter("postID"));
		
		History history = new History();
		history.setUserID(userID);
		history.setPostID(postID);
		
		IHistoryDao historyDao = new HistoryDaoImpl();
		historyDao.addHistory(history);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
