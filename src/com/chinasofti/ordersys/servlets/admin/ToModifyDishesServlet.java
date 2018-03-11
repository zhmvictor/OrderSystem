package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.vo.Dishesinfo;

/**
 * Servlet implementation class ToModifyDishesServlet
 */
@WebServlet("/ToModifyDishesServlet")
public class ToModifyDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToModifyDishesServlet() {
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
		response.setCharacterEncoding("utf-8");
		int dishesId=Integer.parseInt(request.getParameter("dishesId"));
		DishesInfoDao dd = new DishesInfoDao();
		Dishesinfo user = dd.getDishById(dishesId);
		request.setAttribute("DISHES_INFO", user);
		request.getRequestDispatcher("pages/admin/modifydishes.jsp").forward(request, response);
	}

}
