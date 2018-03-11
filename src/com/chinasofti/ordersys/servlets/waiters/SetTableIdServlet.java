package com.chinasofti.ordersys.servlets.waiters;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.dao.OrderdishesDao;
import com.chinasofti.ordersys.vo.Dishesinfo;
import com.chinasofti.ordersys.vo.Orderdishes;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetTableIdServlet
 */
@WebServlet("/SetTableIdServlet")
public class SetTableIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetTableIdServlet() {
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
        response.setCharacterEncoding("utf-8");
        JSONObject object=new JSONObject();
        object.put("myData", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));

        int tableid=Integer.parseInt(request.getParameter("tableid"));



        OrderDao od =new OrderDao();
        int orderid=od.getNowIdBytable(tableid);
        OrderdishesDao odd=new OrderdishesDao();
        List<Orderdishes> orderdishes=odd.selectByOrderId(orderid);

        int intCount=orderdishes.size();
        object.put("dataCount", intCount);
        // 总页数变量
        int intallPage = 1;
        // 计算总页数
        if ((intCount % everyPageDataCount) == 0) {
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
        List<Orderdishes> pageresult=odd.selectSomeDishesByOrderId(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount,orderid);
        Orderdishes[] arr1 = null;

        if(pageresult.size()>0){
            arr1=new Orderdishes[pageresult.size()];
            pageresult.toArray(arr1);
            object.put("myData", arr1);
        }


        DishesInfoDao dd = new DishesInfoDao();

        List<Dishesinfo> dishes= new ArrayList<Dishesinfo>();
        for(Orderdishes o:orderdishes){
            Dishesinfo d= dd.getDishById(o.getDishes());

            dishes.add(d);
        }
        Dishesinfo[] arr2 = null;
        if(dishes.size()>0){
            arr2=new Dishesinfo[dishes.size()];
            dishes.toArray(arr2);
            object.put("myData2", arr2);
        }


        PrintWriter out = response.getWriter();
        out.write(object.toString());
        out.flush();
        out.close();
    }

}
