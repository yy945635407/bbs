package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.IOrdinaryUserDao;
import org.bbs.dao.IPostDao;
import org.bbs.dao.impl.OrdinaryUserDaoImpl;
import org.bbs.dao.impl.PostDaoImpl;
import org.bbs.entity.OrdinaryUser;
import org.bbs.entity.Post;
import org.bbs.entity.Recommend;
import org.bbs.util.ServletHelper;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/RecommendServlet")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用于Ajax获取推荐列表
		ServletHelper.setEncoding(request, response);
		
		IOrdinaryUserDao ordinaryUserDao = new OrdinaryUserDaoImpl();
		IPostDao postDao = new PostDaoImpl();
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		Recommend recommend = new Recommend();
		
		//获取推荐列表
		List<Integer> recommendedPostIDs = recommend.getRecommend(ordinaryUserDao.getTotalCount(), postDao.getTotalCount(), user.getUserID(), 3, 5);
		
//		for(int i:recommendedPostIDs){
//			System.out.println(i);
//		}
		
		List<Post> recommendPosts = new ArrayList<>();
		for(int i:recommendedPostIDs){
			recommendPosts.add(postDao.queryPostById(i));
		}
		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("info", "yes");
		json.put("recommendPosts", recommendPosts);
//		System.out.println(123);
		out.print(json);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
