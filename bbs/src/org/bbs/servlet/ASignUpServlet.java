package org.bbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bbs.dao.IAdministratorDao;
import org.bbs.dao.impl.AdministratorDaoImpl;
import org.bbs.entity.Administrator;
import org.bbs.entity.OrdinaryUser;
import org.bbs.util.ServletHelper;
//（管理员）注册
//用户名已被验证过不存在
/**
 * Servlet implementation class ASignUpServlet
 */
@WebServlet("/ASignUpServlet")
public class ASignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ASignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletHelper.setEncoding(request, response);
		
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd1");
		
		IAdministratorDao userDao = new AdministratorDaoImpl();
		boolean rs = userDao.register(name, pwd);
		if(rs==true){
			request.getSession().setAttribute("info", "注册成功,请登陆");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
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
