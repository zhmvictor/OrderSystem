package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.vo.Dishesinfo;
import com.chinasofti.ordersys.vo.Userinfo;

/**
 * Servlet implementation class ModifyDishesServlet
 */
@WebServlet("/ModifyDishesServlet")
public class ModifyDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDishesServlet() {
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
		Dishesinfo dish = (Dishesinfo) session.getAttribute("DISHES_INFO");
		
		//int dishesId = dish.getDishesId();
		int dishesId = Integer.parseInt(request.getParameter("dishesId"));
		String dishesName = request.getParameter("dishesName");//菜品名
		String dishesDiscript = request.getParameter("dishesDiscript");//菜品简介
		String dishesTxt = request.getParameter("dishesTxt");//菜品说明
		String dishesImg = request.getParameter("dishesImg");//菜品图片
		String recommend = request.getParameter("recommend");//是否推荐
		float dishesPrice = Float.parseFloat(request.getParameter("dishesPrice"));
		int state;
		if(recommend==null){
			state=0;
		}
		else{
			state=1;
		}
		String saveUrl = request.getContextPath();
		dishesImg=dishesImg.replace("/img/dishes/", "").replace(saveUrl,"");
		
		DishesInfoDao dd = new DishesInfoDao();
		if(dd.updateDestine(dishesId, dishesName, dishesDiscript, dishesImg, dishesTxt,state, dishesPrice)){
			Dishesinfo dishes = dd.getDishById(dishesId);
			session.setAttribute("DISHES_INFO",dishes);
			String mag = "修改成功";
			request.setAttribute("msg",mag);
			request.getRequestDispatcher("pages/admin/dishesadmin.jsp").forward(request, response);
		}
		else{
			String mag = "修改失败";
			request.setAttribute("msg",mag);
			request.getRequestDispatcher("pages/admin/modifydishes.jsp").forward(request, response);
		}
		
	}

}
