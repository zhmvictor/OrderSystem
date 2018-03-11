package com.chinasofti.ordersys.servlets.login;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
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
		Userinfo u =(Userinfo) session.getAttribute("USER_INFO");
		int userId = Integer.parseInt(request.getParameter("userId"));//id
		String userAccount = u.getUserAccount();//用户名
		String useroldPass = request.getParameter("userOldPass");//旧密码
	    String userPass = request.getParameter("userPass");//密码
		String faceimg = request.getParameter("faceimg");//头像
		faceimg=faceimg.replace("/img/faces/","");
		String saveUrl = request.getContextPath();
		faceimg=faceimg.replace(saveUrl,"");
    	UserDao ud = new UserDao();

    	List<Userinfo> users = ud.login(userAccount,useroldPass);

		if(users.size()>0) {


		/*HttpSession session=request.getSession();
		User u = (User)session.getAttribute("validaccount");
		u.setImage(saveUrl+newFileName);

//								System.out.println(u.getImage());

		UserDao ud=new UserDao();
		ud.updateuser(u.getUsername(),u.getImage());
		session.setAttribute("validaccount",u);*/

			if (ud.updateUser(userId, userPass, faceimg)) {
				//	String mag = "修改成功！";
				//	request.setAttribute("msg",mag);
				Userinfo user = ud.selectUserInfoById(userId);
				session.setAttribute("USER_INFO", user);
				switch (user.getRole()) {


					case 1:
						request.getRequestDispatcher("pages/admin/main.jsp").forward(request, response);
						break;
					case 2:
						request.getRequestDispatcher("pages/kitchen/kitchenmain.jsp").forward(request, response);
						break;
					case 3:
						request.getRequestDispatcher("pages/waiters/takeorder.jsp").forward(request, response);
						break;
				}
			} else {
				String mag = "修改失败！";
				request.setAttribute("msg", mag);
				request.getRequestDispatcher("pages/users/modifyuser.jsp").forward(request, response);
			}
		}
		else
		{
			String mag = "旧密码错误！";
			request.setAttribute("msg",mag);
			request.getRequestDispatcher("pages/users/modifyuser.jsp").forward(request, response);
		}
	}

}
