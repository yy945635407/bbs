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
//登陆
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
	
		IOrdinaryUserDao userDao = new OrdinaryUserDaoImpl();
		
		OrdinaryUser user = userDao.queryOrdinaryUserByName(name);
		
		
		
		PrintWriter out = response.getWriter();
		
		if(user==null){
			request.getSession().setAttribute("info", "用户名不存在");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
		}else{
			if(user.getUserPwd().equals(pwd)){
				//在session域放当前user对象
				request.getSession().setAttribute("user", user);
				request.getSession().setMaxInactiveInterval(600);
//				System.out.println(request.getSession().getMaxInactiveInterval());
				//request.getRequestDispatcher("index.jsp").forward(request, response);
				response.sendRedirect("index.jsp");
			}
			else{
				request.getSession().removeAttribute("info");
				request.getSession().setAttribute("info", "密码错误");
				response.sendRedirect("signin.jsp");
			}
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
