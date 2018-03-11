package com.chinasofti.ordersys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.chinasofti.ordersys.tools.MD5;
import com.chinasofti.ordersys.vo.Userinfo;

public class UserDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //查询所有
    public List<Userinfo> selectAllUser() {
        List<Userinfo> users = new ArrayList<Userinfo>();
        BaseDao bd = new BaseDao();
        try {
            conn = bd.getConn();
            String sql = "select * from userinfo";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {//分条查询
                Userinfo us = new Userinfo();
                us.setUserId(rs.getInt(1));
                us.setUserAccount(rs.getString(2));
                us.setUserPass(rs.getString(3));
                us.setRole(rs.getInt(4));
                us.setLocked(rs.getInt(5));
                us.setFaceimg(rs.getString(6));
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return users;
    }

    //通过id查询所有
    public Userinfo selectUserInfoById(int userId) {
        Userinfo us = new Userinfo();
        BaseDao bd = new BaseDao();
        try {
            conn = bd.getConn();
            String sql = "select * from userinfo where userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {//分条查询
                us.setUserId(rs.getInt(1));
                us.setUserAccount(rs.getString(2));
                us.setUserPass(rs.getString(3));
                us.setRole(rs.getInt(4));
                us.setLocked(rs.getInt(5));
                us.setFaceimg(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return us;
    }

    //通过id查询用户名,角色,头像
    public Userinfo selectUserRoleById(int userId) {
        Userinfo us = new Userinfo();
        BaseDao bd = new BaseDao();
        try {
            conn = bd.getConn();
            String sql = "select userAccount,role,faceimg from userinfo where userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {//分条查询
                //	us.setUserId(rs.getInt(1));
                us.setUserAccount(rs.getString(1));//用户名
                //	us.setUserPass(rs.getString(3));
                us.setRole(rs.getInt(2));//角色
                //	us.setLocked(rs.getInt(5));
                us.setFaceimg(rs.getString(3));//头像
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return us;
    }

    //分页查询，升序
    public List<Userinfo> UserPage(int pageIndex, int everyPageDataCount) {
        List<Userinfo> result = new ArrayList<Userinfo>();BaseDao bd = new BaseDao();
        try {

            conn = bd.getConn();
            String sql = "select * from userinfo order by userId asc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, Integer.valueOf(pageIndex * everyPageDataCount));
            pstmt.setObject(2, Integer.valueOf(everyPageDataCount));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Userinfo us = new Userinfo();
                us.setUserId(rs.getInt(1));
                us.setUserAccount(rs.getString(2));
                us.setUserPass(rs.getString(3));
                us.setRole(rs.getInt(4));
                us.setLocked(rs.getInt(5));
                us.setFaceimg(rs.getString(6));
                result.add(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return result;
    }

    //按用户名和密码查询,登录
    public List<Userinfo> login(String userAccount, String userPass) {
        List<Userinfo> users = new ArrayList<Userinfo>();
        BaseDao bd = new BaseDao();
        userPass=MD5.getInstance().md5(userPass);
        try {

            conn = bd.getConn();
            String sql = "select * from userinfo where userAccount = ? and userPass = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userAccount);
            pstmt.setString(2, userPass);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Userinfo us = new Userinfo();
                us.setUserId(rs.getInt(1));
                us.setUserAccount(rs.getString(2));
                us.setUserPass(rs.getString(3));
                us.setRole(rs.getInt(4));
                us.setLocked(rs.getInt(5));
                us.setFaceimg(rs.getString(6));
                users.add(us);//添加一条数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return users;
    }

    //添加所有（增加员工）
    public boolean addAllUser( String userAccount, String userPass, int role, int locked, String faceimg) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        userPass=MD5.getInstance().md5(userPass);
        try {
            conn = bd.getConn();
            String sql = "insert into userinfo(userAccount,userPass,role,locked,faceimg) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userAccount);
            pstmt.setString(2, userPass);
            pstmt.setInt(3, role);
            pstmt.setInt(4, locked);
            pstmt.setString(5, faceimg);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    //注册
    public boolean zhuceUser(String userAccount, String userPass, int role, String faceimg) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        userPass=MD5.getInstance().md5(userPass);
        try {
            conn = bd.getConn();
            String sql = "insert into userinfo(userAccount,userPass,role,faceimg) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userAccount);
            pstmt.setString(2, userPass);
            pstmt.setInt(3, role);
            pstmt.setString(4, faceimg);
            int i = pstmt.executeUpdate();//执行失败i=-1
            if (i > 0) {//判断值，是否添加成功
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    //通过id修改
    public boolean updateAllUser(int userId, String userAccount, String userPass, int role, int locked, String faceimg) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        userPass=MD5.getInstance().md5(userPass);
        try {
            conn = bd.getConn();
            String sql = "update userinfo set userAccount=?,userPass=?,role=?,locked=?,faceimg=? where userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userAccount);
            pstmt.setString(2, userPass);
            pstmt.setInt(3, role);
            pstmt.setInt(4, locked);
            pstmt.setString(5, faceimg);
            pstmt.setInt(6, userId);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    //管理员通过id修改用户
    public boolean updateAdmin(int userId, String userPass, int role, String faceimg) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        userPass=MD5.getInstance().md5(userPass);
        try {
            conn = bd.getConn();
            String sql = "update userinfo set userPass=?,role=?,faceimg=? where userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userPass);
            pstmt.setInt(2, role);
            //	pstmt.setInt(3,locked);
            pstmt.setString(3, faceimg);
            pstmt.setInt(4, userId);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    //修改个人信息
    public boolean updateUser(int userId, String userPass, String faceimg) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        userPass=MD5.getInstance().md5(userPass);
        try {
            conn = bd.getConn();
            String sql = "update userinfo set userPass=?,faceimg=? where userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userPass);
            //	pstmt.setInt(2,role);
            //	pstmt.setInt(3,locked);
            pstmt.setString(2, faceimg);
            pstmt.setInt(3, userId);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    //通过id删除
    public boolean deleteUser(int userId) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        try {
            conn = bd.getConn();
            String sql = "delete from userinfo where userId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    public List<Userinfo> SelectByAccount(String userAccount) {
        List<Userinfo> users = new ArrayList<Userinfo>();
        BaseDao bd = new BaseDao();

        try {

            conn = bd.getConn();
            String sql = "select * from userinfo where userAccount = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userAccount);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Userinfo us = new Userinfo();
                us.setUserId(rs.getInt(1));
                us.setUserAccount(rs.getString(2));
                us.setUserPass(rs.getString(3));
                us.setRole(rs.getInt(4));
                us.setLocked(rs.getInt(5));
                us.setFaceimg(rs.getString(6));
                users.add(us);//添加一条数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return users;
    }

    //测试函数
    /*public static void main(String args[]) {
        UserDao sd = new UserDao();
        //添加
        //	boolean a = sd.addUser(2,"m","sdsad",1,0,"sadfes.jpg");
        //	System.out.println(a);
        //更新
//		boolean a = sd.updateUser(2,"a","sdsad",1,0,"sadfes.jpg");
//		System.out.println(a);
        //删除
       *//* boolean c = sd.deleteUser(2);
        System.out.println(c);*//*

        //查询
        List<Userinfo> list = sd.SelectByAccount("waiter1");
        for (Userinfo stu : list) {
            System.out.println(stu.getUserAccount());
        }
        //	Userinfo us = sd.selectInfoById(1);
        //System.out.println(us.getUserId());

    }*/
}
