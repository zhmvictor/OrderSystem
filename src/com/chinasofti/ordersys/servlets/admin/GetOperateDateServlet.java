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
import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.dao.OrderdishesDao;
import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.vo.Orderdishes;
import com.chinasofti.ordersys.vo.Orderinfo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetOperateDateServlet
 */
@WebServlet("/GetOperateDateServlet")
public class GetOperateDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOperateDateServlet() {
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
		object.put("Sum", "");
		object.put("userAccount", "");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		OrderDao od = new OrderDao();
		OrderdishesDao odd = new OrderdishesDao();
		DishesInfoDao dd = new DishesInfoDao();
		UserDao ud = new UserDao();
		List<Orderinfo> orders = od.selectSomeOrderByTime(begin, end, 2);
		int count = orders.size();
		Orderinfo[] Arr = null;
		double[] Sum = new double[count];
		String[] userAccount = new String[count];
		if(count>0){
			Arr = new Orderinfo[count];
			// 给实体类数据赋值
			orders.toArray(Arr);
			object.put("Arr", Arr);
			for(int i =0 ; i < count ; i++){
				userAccount[i] = ud.selectUserInfoById(Arr[i].getWaiterId()).getUserAccount();
				Sum[i]=0;
				List<Orderdishes> list = odd.selectOrderdishes(Arr[i].getOrderId());
				Orderdishes[] odes = null;
				int n = list.size();
				if(n>0){
					odes = new Orderdishes[n];
					list.toArray(odes);
					for(int j = 0 ; j < n ; j ++){
						Sum[i]+=dd.getPriceById(odes[j].getDishes())*odes[j].getNum();
					}
				}
			}
			object.put("Sum", Sum);
			object.put("userAccount", userAccount);
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

}
