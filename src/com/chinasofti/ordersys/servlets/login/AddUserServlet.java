package com.chinasofti.ordersys.servlets.login;

import com.chinasofti.ordersys.dao.UserDao;
import com.chinasofti.ordersys.vo.Userinfo;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
        String userAcount = request.getParameter("userAccount");//ID
        String userPass = request.getParameter("userPass");//密码
        String faceimg = request.getParameter("faceimg");//头像
        faceimg=faceimg.replace("/img/faces/","");
        String saveUrl = request.getContextPath();
        faceimg=faceimg.replace(saveUrl,"");
        String roleId = request.getParameter("roleId");//角色
        UserDao ud =new UserDao();
        List<Userinfo> users =ud.SelectByAccount(userAcount);
        if(users.size()>0){
            String mag = "用户名已存在！";
            request.setAttribute("msg", mag);
            request.getRequestDispatcher("pages/admin/adduser.jsp").forward(request, response);
        }
        else{
            if(ud.addAllUser(userAcount,userPass,Integer.parseInt(roleId),0,faceimg)){
                String mag = "添加成功！";
                request.setAttribute("msg", mag);
                request.getRequestDispatcher("pages/admin/adduser.jsp").forward(request, response);

            }
            else{
                String mag = "添加失败！";
                request.setAttribute("msg", mag);
                request.getRequestDispatcher("pages/admin/adduser.jsp").forward(request, response);
            }

        }
    }

}
