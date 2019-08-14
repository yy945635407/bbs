package org.bbs.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.entity.Administrator;
import org.bbs.entity.OrdinaryUser;

public class ServletHelper {
	public static void setEncoding(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		response.setCharacterEncoding("utf-8");
	}
	
	public static OrdinaryUser getVisitor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取当前用户
		OrdinaryUser user = (OrdinaryUser) request.getSession().getAttribute("user");
		if (user==null){//登陆超时重新登陆
			request.getSession().setAttribute("timeout", "登陆超时请重新登陆");
			request.getSession().removeAttribute("info");
			response.sendRedirect("signin.jsp");
			return null;
		}else{
			return user;
		}
	}
	
	public static Administrator getAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取当前用户
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
		if (admin==null){//登陆超时重新登陆
			request.getSession().setAttribute("timeout", "登陆超时请重新登陆");
			request.getSession().removeAttribute("info");
			response.sendRedirect("signin.jsp");
			return null;
		}else{
			return admin;
		}
	}
}
