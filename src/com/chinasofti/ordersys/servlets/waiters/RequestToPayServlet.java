package com.chinasofti.ordersys.servlets.waiters;

import com.chinasofti.ordersys.dao.OrderDao;
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

/**
 * Servlet implementation class RequestToPayServlet
 */
@WebServlet("/RequestToPayServlet")
public class RequestToPayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestToPayServlet() {
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
        int orderId=Integer.parseInt(request.getParameter("orderId"));
        Date date=new Date(System.currentTimeMillis());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        OrderDao od=new OrderDao();
        JSONObject object = new JSONObject();
        object.put("strMessage", "");

        PrintWriter out=null;
        try {
            out = response.getWriter();
        } catch (IOException e) {

        }
        if(!(od.updateEndDateById(orderId,time)&&od.updateOrderStateByorderId(orderId,1))){
            object.put("strMessage", "结账出错");

        }
        out.print(object.toString());//write
        out.flush();
        out.close();


    }

}
