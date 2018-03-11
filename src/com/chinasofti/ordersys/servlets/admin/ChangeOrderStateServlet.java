package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.dao.OrderdishesDao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ChangeOrderStateServlet
 */
@WebServlet("/ChangeOrderStateServlet")
public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeOrderStateServlet() {
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
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int button = Integer.parseInt(request.getParameter("button"));
		OrderdishesDao odd = new OrderdishesDao();
		OrderDao od = new OrderDao();
		JSONObject object=new JSONObject();
		object.put("Mes", "");
		if(button == 1){
			odd.deletsOrderdishesByOrderId(orderId);
			od.delOrderById(orderId);
			object.put("Mes", "OK");
		}else if(button == 2){
			od.updateOrderStateByorderId(orderId, 2);
			object.put("Mes", "OK");
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

}
