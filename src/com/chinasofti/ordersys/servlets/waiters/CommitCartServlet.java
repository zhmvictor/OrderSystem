package com.chinasofti.ordersys.servlets.waiters;

import com.chinasofti.ordersys.dao.OrderDao;
import com.chinasofti.ordersys.dao.OrderdishesDao;
import com.chinasofti.ordersys.vo.Userinfo;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CommitCartServlet
 */
@WebServlet("/CommitCartServlet")
public class CommitCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitCartServlet() {
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
        String tableid=request.getParameter("tableid");
        String[] dishes=request.getParameterValues("dishes");
        String[] num= request.getParameterValues("num");
        HttpSession session=request.getSession();
        Userinfo user =( Userinfo) session.getAttribute("USER_INFO");
        int userid = user.getUserId();
        Date date=new Date(System.currentTimeMillis());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
//        System.out.println(time);
        OrderDao od=new OrderDao();
        OrderdishesDao odd =new OrderdishesDao();
        if(od.getNowIdBytable(Integer.parseInt(tableid))==-1)
            od.addOrder(time,userid,0,Integer.parseInt(tableid));

        int orderid = od.getNowIdBytable(Integer.parseInt(tableid));
        for(int i=0;i<dishes.length;i++){
            if(Integer.parseInt(num[i])>0){
                odd.addOrderdishes(orderid,Integer.parseInt(dishes[i]),Integer.parseInt(num[i]),0);

            }
        }
        JSONObject object = new JSONObject();
        object.put("strMessage", "");

        PrintWriter out=null;
        try {
            out = response.getWriter();
        } catch (IOException e) {

        }
        out.print(object.toString());//write
        out.flush();
        out.close();
    }

}
