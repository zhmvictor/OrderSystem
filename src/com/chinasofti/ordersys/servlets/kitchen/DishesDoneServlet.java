package com.chinasofti.ordersys.servlets.kitchen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.chinasofti.ordersys.dao.OrderdishesDao;
import com.chinasofti.ordersys.vo.Orderdishes;

/**
 * Servlet implementation class DishesDoneServlet
 */
@WebServlet("/DishesDoneServlet")
public class DishesDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishesDoneServlet() {
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
		JSONObject object=new JSONObject();
		int odId=Integer.parseInt(request.getParameter("odId"));
		OrderdishesDao dd = new OrderdishesDao();
		if(dd.selectodId(odId).get(0).getDishstatic()==0){
			dd.setDishstaticById(odId, 1);
			object.put("orderstatic", "1");
		}else{
			dd.setDishstaticById(odId, 2);
			object.put("orderstatic", "2");
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

}
