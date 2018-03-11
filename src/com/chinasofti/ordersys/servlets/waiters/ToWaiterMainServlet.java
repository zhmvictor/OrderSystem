package com.chinasofti.ordersys.servlets.waiters;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.vo.Dishesinfo;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ToWaiterMainServlet
 */
@WebServlet("/ToWaiterMainServlet")
public class ToWaiterMainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToWaiterMainServlet() {
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
        DishesInfoDao dd =new DishesInfoDao();
        response.setCharacterEncoding("utf-8");
        /*JSONObject object=new JSONObject();
        object.put("dish", "");
        int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));



        List<Dishesinfo> dishes = dd.selectAll();
        int intCount=dishes.size();
        object.put("dataCount2", intCount);
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
        object.put("pageIndex2", pageIndex);
        List<Dishesinfo> pageresult=dd.selectByPageIndex(Integer.valueOf(pageIndex * everyPageDataCount),everyPageDataCount);
        Dishesinfo[] arr1 = null;

        if(pageresult.size()>0){
            arr1=new Dishesinfo[pageresult.size()];
            pageresult.toArray(arr1);
            object.put("dish", arr1);
        }
*/
        List<Dishesinfo> dishes = dd.selectAll();
        request.setAttribute("dishes",dishes);
        request.getRequestDispatcher("pages/waiters/takeorder.jsp").forward(request,response);
        /*PrintWriter out = response.getWriter();
        out.write(object.toString());
        out.flush();
        out.close();*/
    }

}
