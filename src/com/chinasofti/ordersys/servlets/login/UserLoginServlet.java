package com.chinasofti.ordersys.servlets.login;

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
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userAccount = request.getParameter("userAccount");
		String userPass = request.getParameter("userPass");
		Userinfo user = new Userinfo();
		UserDao ud = new UserDao();
		List<Userinfo> list = ud.login(userAccount, userPass);
		if(list.size()>0){
			user=list.get(0);
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", user);
			switch(user.getRole()){
			case 1: request.getRequestDispatcher("pages/admin/main.jsp").forward(request, response); break;
			case 2: request.getRequestDispatcher("pages/kitchen/kitchenmain.jsp").forward(request, response);break;
			case 3: request.getRequestDispatcher("ToWaiterMainServlet").forward(request, response);break;
			}
			//request.getRequestDispatcher("ShowAllServlet").forward(request, response);
		}else{
			String msg = "用户名或密码错误！";
			request.setAttribute("ERROR_MSG", msg);
			user.setUserAccount(userAccount);
			user.setUserPass(userPass);
			request.setAttribute("USER_INFO", user);
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
	}

}
