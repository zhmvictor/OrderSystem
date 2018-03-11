package com.chinasofti.ordersys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.ordersys.vo.Orderdishes;

public class OrderdishesDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public List<Orderdishes> selectAllDishes(){
        List<Orderdishes> orderdishes =new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from orderdishes";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                od.setDishstatic(rs.getInt(5));
                orderdishes.add(od);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return orderdishes;
    }

    public boolean addOrderdishes(int orderReference, int dishes, int num,int dishstatic) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        try {
            conn = bd.getConn();
            String sql = "insert into orderdishes (orderReference,dishes,num,dishstatic) value(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderReference);
            pstmt.setInt(2, dishes);
            pstmt.setInt(3, num);
            pstmt.setInt(4, dishstatic);
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

    public boolean deleteOrderdishes(int odId){
        boolean flag =false;
        BaseDao bd = new BaseDao();
        try{
            Connection conn = bd.getConn();
            String sql = "delete from orderdishes where odId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, odId);
            int i=pstmt.executeUpdate();
            if(i>0){
                flag=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deletsOrderdishesByOrderId(int orderId){
    	boolean flag =false;
        BaseDao bd = new BaseDao();
        try{
            Connection conn = bd.getConn();
            String sql = "delete from orderdishes where orderReference = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            int i=pstmt.executeUpdate();
            if(i>0){
                flag=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    
    public boolean updateOrderdishes(int orderReference,int dishes,int num,int dishstatic,int odId){
        boolean flag =false;
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "update orderdishes set orderReference=?,dishes=?,num=?,dishstatic=? where odId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderReference);
            pstmt.setInt(2, dishes);
            pstmt.setInt(3, num);
            pstmt.setInt(4, dishstatic);
            pstmt.setInt(5,odId);
            int i=pstmt.executeUpdate();
            if(i>0){
                flag=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }
    public List<Orderdishes> selectSomeDishes(int from,int count){
        List<Orderdishes> orderdishes = new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from orderdishes order by odId asc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, from);
            pstmt.setObject(2, count);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                od.setDishstatic(rs.getInt(5));
                orderdishes.add(od);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }

        return orderdishes;
    }
    public List<Orderdishes> selectOrderdishes(int orderReference){
        List<Orderdishes> orderdishes =new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from orderdishes where orderReference=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderReference);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                od.setDishstatic(rs.getInt(5));
                orderdishes.add(od);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return orderdishes;
    }
    public List<Orderdishes> selectSomeDishesByOrderId(int from,int count,int orderReference){
        List<Orderdishes> orderdishes = new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from orderdishes where orderReference=? order by odId asc limit ?,? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, orderReference);
            pstmt.setObject(2, from);
            pstmt.setObject(3, count);

            rs = pstmt.executeQuery();
            while(rs.next()){
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                od.setDishstatic(rs.getInt(5));
                orderdishes.add(od);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }

        return orderdishes;
    }
    public List<Orderdishes> selectSomeDishesBynoStatic(int from,int count,int dishstatic){
        List<Orderdishes> orderdishes = new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from orderdishes where dishstatic != ? order by odId asc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, dishstatic);
            pstmt.setObject(2, from);
            pstmt.setObject(3, count);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                od.setDishstatic(rs.getInt(5));
                orderdishes.add(od);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return orderdishes;
    }
    public List<Orderdishes> selectByOrderId(int orderReference){
        List<Orderdishes> orderdishes = new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try {
            conn = bd.getConn();
            String sql = "select * from orderdishes where orderReference=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, orderReference);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                orderdishes.add(od);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }

        return orderdishes;
    }
    public boolean setDishstaticById(int odId,int dishstatic){
        boolean flag = false;
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "update orderdishes set dishstatic=? where odId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dishstatic);
            pstmt.setInt(2,odId);
            int i=pstmt.executeUpdate();
            if(i>0){
                flag=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }
    public List<Orderdishes> selectodId(int odId){
        List<Orderdishes> orderdishes =new ArrayList<Orderdishes>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from orderdishes where odId=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, odId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Orderdishes od = new Orderdishes();
                od.setOdId(rs.getInt(1));
                od.setOrderReference(rs.getInt(2));
                od.setDishes(rs.getInt(3));
                od.setNum(rs.getInt(4));
                od.setDishstatic(rs.getInt(5));
                orderdishes.add(od);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return orderdishes;
    }
    public static void main(String[] args) {
        OrderdishesDao od = new OrderdishesDao();
        List<Orderdishes> list = od.selectByOrderId(4);
        for (Orderdishes ord : list) {
            System.out.println(ord.getOrderReference());
            System.out.println(ord.getDishes());
            System.out.println(ord.getNum());
        }
//		boolean add = od.addOrderdishes(3,1,3);
//		boolean d = od.deleteOrderdishes(38);
//		boolean u = od.updateOrderdishes(2, 1, 2,39);
    }
}



