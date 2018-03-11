package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.dao.OrderdishesDao;
import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.vo.Dishesinfo;
import com.chinasofti.ordersys.vo.Orderdishes;
import com.chinasofti.ordersys.vo.Orderinfo;

/**
 * Servlet implementation class GetOrderDetailServlet
 */
@WebServlet("/GetOrderDetailServlet")
public class GetOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderDetailServlet() {
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
		int orderId=Integer.parseInt(request.getParameter("id"));
		String sumPrice=request.getParameter("sumPrice");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		OrderDao od = new OrderDao();
		OrderdishesDao odd = new OrderdishesDao();
		DishesInfoDao dd = new DishesInfoDao();
		UserDao ud = new UserDao();
		JSONObject object=new JSONObject();
		object.put("sumPrice", sumPrice);
		Orderinfo order = od.getOrderById(orderId);
		object.put("order", order);
		String userAccount = ud.selectUserInfoById(order.getWaiterId()).getUserAccount();
		object.put("userAccount", userAccount);
		List<Orderdishes> list = odd.selectOrderdishes(orderId);
		int n = list.size();
		Orderdishes[] Arr = null;
		Dishesinfo[] Dishes = null;
		if(n>0){
			Arr = new Orderdishes[n];
			Dishes = new Dishesinfo[n];
			list.toArray(Arr);
			for(int i = 0 ; i < n ; i ++){
				Dishes[i]= dd.getDishById(Arr[i].getDishes());
			}
			object.put("Arr", Arr);
			object.put("Dishes", Dishes);
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

}
