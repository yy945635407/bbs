package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.entity.Collection;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.ServletHelper;
//删除收藏
/**
 * Servlet implementation class RemoveCollectionServlet
 */
@WebServlet("/RemoveCollectionServlet")
public class RemoveCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCollectionServlet() {
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
		ICollectionDao collectionDao = new CollectionDaoImpl();
		Collection collection = collectionDao.queryCollection(userID, postID);
		if(collection!=null){
			collectionDao.deleteCollectionById(collection.getCollectionID());
		}
		request.getRequestDispatcher("QueryPostByID?postID="+postID).forward(request, response);
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
