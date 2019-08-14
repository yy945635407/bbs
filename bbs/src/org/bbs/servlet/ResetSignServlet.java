package org.bbs.servlet;
//更改签名
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
@WebServlet("/ResetSignServlet")
public class ResetSignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.setEncoding(request, response);
		
		String nSign = request.getParameter("nsign").replace("\n", "<br/>");//新签名 //将textarea中/n替换为<br/>（前端显示换行）
//		System.out.println(nSign=="/r");
		
		
		IOrdinaryUserDao userDao = new OrdinaryUserDaoImpl();
		
		//获取当前用户
		OrdinaryUser user = ServletHelper.getVisitor(request, response);
		if(user==null) return;
		user.setUserSign(nSign);
		userDao.updateOrdinaryUserById(user.getUserID(), user);
		request.setAttribute("info", "修改成功,点击返回");
		request.getRequestDispatcher("resetsign.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
