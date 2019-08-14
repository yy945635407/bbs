package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.entity.OrdinaryUser;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.util.ServletHelper;
//配合Ajax完成用户名验证（验证想要注册的用户名是否在数据库中）
/**
 * Servlet implementation class RegisterAjaxServlet
 */
@WebServlet("/RegisterAjaxServlet")
public class RegisterAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//配合Ajax完成用户名验证
		ServletHelper.setEncoding(request, response);
		
		String name = request.getParameter("uname");
		IOrdinaryUserDao userDao = new OrdinaryUserDaoImpl();
		OrdinaryUser user = userDao.queryOrdinaryUserByName(name);
		PrintWriter out = response.getWriter();
		if(user!=null){
			out.write("用户名已存在");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
