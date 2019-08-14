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
//（管理员）配合Ajax完成用户名验证（验证想要注册的用户名是否在数据库中）
/**
 * Servlet implementation class ARegisterAjaxServlet
 */
@WebServlet("/ARegisterAjaxServlet")
public class ARegisterAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ARegisterAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletHelper.setEncoding(request, response);
		
		String name = request.getParameter("uname");
		IAdministratorDao userDao = new AdministratorDaoImpl();
		Administrator user = userDao.queryAdministratorByName(name);
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
