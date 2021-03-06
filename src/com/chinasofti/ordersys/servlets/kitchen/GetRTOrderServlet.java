package com.chinasofti.ordersys.servlets.kitchen;

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
import com.chinasofti.ordersys.vo.Dishesinfo;
import com.chinasofti.ordersys.vo.Orderdishes;
import com.chinasofti.ordersys.vo.Orderinfo;

/**
 * Servlet implementation class GetRTOrderServlet
 */
@WebServlet("/GetRTOrderServlet")
public class GetRTOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRTOrderServlet() {
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
		OrderdishesDao dd = new OrderdishesDao();
		OrderDao aa = new OrderDao();
		DishesInfoDao cc = new DishesInfoDao();
		JSONObject object=new JSONObject();
		object.put("Arr", "");
		object.put("tableId", "");
		object.put("dishesName", "");
		object.put("num", "");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
        List<Orderdishes> orderdishes = dd.selectAllDishes();
		int intCount = orderdishes.size();
		object.put("dataCount", intCount);
		// 总页数变量		
		int intallPage = 1;
		// 计算总页数
		if ((intCount % everyPageDataCount) == 0) {//11/5=3
			intallPage = intCount / everyPageDataCount;
		} else {
			intallPage = intCount / everyPageDataCount + 1;
		}
		// 防止页码越界
		if (pageIndex < 0) {
			pageIndex = 0;
		} else if (pageIndex >= intallPage) {
			pageIndex = intallPage - 1;
		}
		object.put("pageIndex", pageIndex);
		List<Orderdishes> list = dd.selectSomeDishesBynoStatic(Integer.valueOf(pageIndex * everyPageDataCount), Integer.valueOf(everyPageDataCount),2);
		int count = list.size();
		Orderdishes[] Arr = null;
		int[] tableId = new int[count];
		String[] dishesName = new String[count];
		int[] num = new int[count];
		if (count > 0) {
			// 根据list的大小分配实体类的长度
			Arr = new Orderdishes[count];
			// 给实体类数据赋值
			list.toArray(Arr);
			object.put("Arr", Arr);
			for(int i = 0 ; i < count ; i ++ ){
	        	Orderinfo or = aa.getOrderById(Arr[i].getOrderReference());
	        	tableId[i] = or.getTableId();
	        	Dishesinfo di = cc.getDishById(Arr[i].getDishes());
	        	dishesName[i] = di.getDishesName();
	        	num[i] = Arr[i].getNum();
	        }
			object.put("tableId", tableId);
			object.put("dishesName", dishesName);
			object.put("num", num);
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

}
