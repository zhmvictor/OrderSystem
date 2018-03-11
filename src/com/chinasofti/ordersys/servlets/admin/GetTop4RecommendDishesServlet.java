package com.chinasofti.ordersys.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.vo.Dishesinfo;
import com.chinasofti.ordersys.vo.Orderinfo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetTop4RecommendDishesServlet
 */
@WebServlet("/GetTop4RecommendDishesServlet")
public class GetTop4RecommendDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTop4RecommendDishesServlet() {
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
		JSONObject object=new JSONObject();
		object.put("Arr", "");
		DishesInfoDao dd = new DishesInfoDao();
		List<Dishesinfo> dishes = dd.selectTop4();
		int count = dishes.size();
		Dishesinfo[] Arr = null;
		if(count>0){
			Arr = new Dishesinfo[count];
			// 给实体类数据赋值
			dishes.toArray(Arr);
			object.put("Arr", Arr);
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

}
