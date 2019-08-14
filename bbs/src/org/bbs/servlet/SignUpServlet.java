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
//注册
/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用户名已被验证过不存在
		ServletHelper.setEncoding(request, response);
		
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd1");
		
		IOrdinaryUserDao userDao = new OrdinaryUserDaoImpl();
		boolean rs = userDao.register(name, pwd);
		if(rs==true){
			request.getSession().setAttribute("info", "注册成功,请登陆");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
