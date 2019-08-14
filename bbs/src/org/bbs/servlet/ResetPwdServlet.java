package org.bbs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.entity.OrdinaryUser;
import org.bbs.dao.*;
import org.bbs.dao.impl.*;
import org.bbs.util.ServletHelper;
//重设密码
@WebServlet("/ResetPwdServlet")
public class ResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		String oripwd = request.getParameter("oripwd");//用户输入的原密码
		String npwd = request.getParameter("npwd");//新密码
		if(oripwd==null||oripwd.length()==0){//未输入密码
			request.setAttribute("nopwd", "密码不能为空");
			request.getRequestDispatcher("resetpwd.jsp").forward(request, response);
			return;
		}
		if(npwd==null||npwd.length()==0){//未输入密码
			request.setAttribute("nonpwd", "新密码不能为空");
			request.getRequestDispatcher("resetpwd.jsp").forward(request, response);
			return;
		}
		IOrdinaryUserDao userDao = new OrdinaryUserDaoImpl();
		
		//获取当前用户
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		String pwd = user.getUserPwd();//真实原密码
		if(pwd.equals(oripwd)){
			user.setUserPwd(npwd);
			userDao.updateOrdinaryUserById(user.getUserID(), user);
			request.setAttribute("info", "修改成功,点击登陆");
		}else{
			request.setAttribute("oripwderror", "原密码不正确");
		}
		
		request.getRequestDispatcher("resetpwd.jsp").forward(request, response);
		return;
//		System.out.println(user);
//		userDao.updateOrdinaryUserById(id, ordinaryUser);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
