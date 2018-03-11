package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.vo.Userinfo;

/**
 * Servlet implementation class AdminModifyUserServlet
 */
@WebServlet("/AdminModifyUserServlet")
public class AdminModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Userinfo user =(Userinfo) session.getAttribute("MODIFY_INFO");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userPass = request.getParameter("userPass");
		int role = Integer.parseInt(request.getParameter("role"));
		String faceimg = request.getParameter("faceimg");
		faceimg=faceimg.replace("/img/faces/",""); 
		String saveUrl = request.getContextPath();
		faceimg=faceimg.replace(saveUrl,"");
		
		UserDao ud = new UserDao();
		if(ud.updateAdmin(userId, userPass, role, faceimg)){
			Userinfo users = ud.selectUserInfoById(userId);
			session.setAttribute("MODIFY_INFO", users);
			String mag = "修改成功";
			request.setAttribute("msg",mag);
			request.getRequestDispatcher("pages/admin/useradmin.jsp").forward(request, response);
		}else{
			String mag = "修改失败";
			request.setAttribute("msg",mag);
			request.getRequestDispatcher("pages/admin/modifyuser.jsp").forward(request, response);
		}
		
	}

}
