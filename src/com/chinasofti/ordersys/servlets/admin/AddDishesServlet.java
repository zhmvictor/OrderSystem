package com.chinasofti.ordersys.servlets.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;

import com.chinasofti.ordersys.dao.DishesInfoDao;
import com.chinasofti.ordersys.vo.Dishesinfo;

/**
 * Servlet implementation class AddDishesServlet
 */
@WebServlet("/AddDishesServlet")
public class AddDishesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDishesServlet() {
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
        String dishesName = request.getParameter("dishesName");
        String dishesDiscript = request.getParameter("dishesDiscript");
        String dishesImg = request.getParameter("dishesImg");
        String dishesTxt = request.getParameter("dishesTxt");
        String recommend = request.getParameter("recommend");
        System.out.println(recommend);
        double dishesPrice = Double.parseDouble(request.getParameter("dishesPrice"));
        dishesImg = dishesImg.replace("/img/dishes/", "");
        String saveUrl = request.getContextPath();
        dishesImg = dishesImg.replace(saveUrl, "");
        DishesInfoDao dd = new DishesInfoDao();
    /*
        String[] checkvalue=(String[])request.getParameterValues("recommend");*/
        if (recommend == null) {
            //System.out.println("传回来空值");
            String mag = "添加成功！";
            request.setAttribute("msg", mag);
            dd.addDishes(dishesName, dishesDiscript, dishesImg, dishesTxt, 0, dishesPrice);
            request.getRequestDispatcher("/pages/admin/adddishes.jsp").forward(request, response);
        } else {
            String mag = "添加成功！";
            request.setAttribute("msg", mag);
            dd.addDishes(dishesName, dishesDiscript, dishesImg, dishesTxt, Integer.parseInt(recommend), dishesPrice);
            request.getRequestDispatcher("/pages/admin/adddishes.jsp").forward(request, response);
        }
        //System.out.println(recommend);
    }
}



