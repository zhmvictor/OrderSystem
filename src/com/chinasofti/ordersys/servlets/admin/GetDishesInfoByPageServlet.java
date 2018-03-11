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
import com.chinasofti.ordersys.vo.Dishesinfo;

/**
 * Servlet implementation class GetDishesInfoByPageServlet
 */
@WebServlet("/GetDishesInfoByPageServlet")
public class GetDishesInfoByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDishesInfoByPageServlet() {
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
		DishesInfoDao dd = new DishesInfoDao();
		JSONObject object=new JSONObject();
		object.put("Arr", "");
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
        List<Dishesinfo> lists = dd.selectAll();
		int intCount = lists.size();
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
		
		List<Dishesinfo> list = dd.selectByPageIndex(Integer.valueOf(pageIndex * everyPageDataCount), Integer.valueOf(everyPageDataCount));
		int count = list.size();
		Dishesinfo[] Arr = null;
		if (count > 0) {
			// 根据list的大小分配实体类的长度
			Arr = new Dishesinfo[count];
			// 给实体类数据赋值
			list.toArray(Arr);
			object.put("Arr", Arr);

		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}
}
