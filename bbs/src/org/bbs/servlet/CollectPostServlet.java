package org.bbs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.ICollectionDao;
import org.bbs.dao.impl.CollectionDaoImpl;
import org.bbs.entity.Collection;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.ServletHelper;
//向收藏帖子跳转（传递postID）
/**
 * Servlet implementation class CollectPostServlet
 */
@WebServlet("/CollectPostServlet")
public class CollectPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		int userID = user.getUserID();
		int postID = Integer.parseInt(request.getParameter("postID"));
		ICollectionDao collectionDao = new CollectionDaoImpl();
		int collectionNum = collectionDao.getTotalCount(userID);
//		System.out.println(collectionNum);
		List<Collection> collections = collectionDao.queryCollectionForSbByPage(0, collectionNum, userID);//得到当前用户的所有收藏
		List<String> tags = new ArrayList<>();//当前用户的所有标签
		for(Collection c:collections){
			String tag = c.getTag();
			if(!tags.contains(tag)) tags.add(tag);
//			System.out.println(tag);
		}
		request.setAttribute("tags", tags); //当前用户的所有收藏标签
		request.setAttribute("postID", postID); //当前帖子ID
		
		request.getRequestDispatcher("addcollection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
