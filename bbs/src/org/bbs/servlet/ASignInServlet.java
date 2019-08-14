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
//（管理员）登陆
/**
 * Servlet implementation class ASignInServlet
 */
@WebServlet("/ASignInServlet")
public class ASignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ASignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletHelper.setEncoding(request, response);
		
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");

		IAdministratorDao userDao = new AdministratorDaoImpl();
		
		Administrator user = userDao.queryAdministratorByName(name);
		
		
		
		
		if(user==null){
			request.setAttribute("nameerror", "用户名不存在");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
		}else{
			if(user.getUserPwd().equals(pwd)){
				//在session域放当前user对象
				request.getSession().setAttribute("admin", user);//管理员设为admin与user区分开
//				System.out.println(request.getSession().getMaxInactiveInterval());
				response.sendRedirect("aindex.jsp");
			}
			else{
				request.getSession().setAttribute("info", "密码错误");
				request.getRequestDispatcher("signin.jsp").forward(request, response);
			}
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
