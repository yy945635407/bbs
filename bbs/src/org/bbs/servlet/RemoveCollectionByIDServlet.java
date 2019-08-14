package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.ICollectionDao;
import org.bbs.dao.impl.CollectionDaoImpl;
import org.bbs.util.ServletHelper;

/**
 * Servlet implementation class RemoveCollectionByIDServlet
 */
@WebServlet("/RemoveCollectionByIDServlet")
public class RemoveCollectionByIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCollectionByIDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		if(ServletHelper.getVisitor(request, response)==null){
			request.getSession().setAttribute("timeout", "登陆超时请重新登陆");
			response.sendRedirect("signin.jsp");
			return;
		}
		int collectionID = Integer.parseInt(request.getParameter("collectionID"));
		ICollectionDao collectionDao = new CollectionDaoImpl();
		collectionDao.deleteCollectionById(collectionID);
//		System.out.println(123);
		request.getRequestDispatcher("managecollection.jsp").forward(request, response);
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
