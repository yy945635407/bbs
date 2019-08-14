package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.ICollectionDao;
import org.bbs.dao.IPostDao;
import org.bbs.dao.impl.CollectionDaoImpl;
import org.bbs.dao.impl.PostDaoImpl;
import org.bbs.entity.Collection;
import org.bbs.entity.OrdinaryUser;
import org.bbs.entity.Post;
import org.bbs.util.ServletHelper;

/**
 * Servlet implementation class AddCollectionServlet
 */
@WebServlet("/AddCollectionServlet")
public class AddCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		IPostDao postDao = new PostDaoImpl();
		ICollectionDao collectionDao = new CollectionDaoImpl();
		
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		int userID = user.getUserID();
		String tag = request.getParameter("tag");//选择的已存在标签
//		System.out.println(tag);
		int postID = Integer.parseInt(request.getParameter("postID"));
//		System.out.println(postID);
		Post post = postDao.queryPostById(postID);
		String title = post.getTitle();
		
		Collection collection = new Collection();
		collection.setPostID(postID);
		collection.setUserID(userID);
		collection.setTag(tag);
		collection.setTitle(title);
		
		if(collectionDao.addCollection(collection)==true){
			request.getRequestDispatcher("QueryPostByID?postID="+postID).forward(request, response);
		}
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
