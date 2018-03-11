package com.chinasofti.ordersys.dao;

import com.chinasofti.ordersys.vo.Dishesinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DishesInfoDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public boolean addDishes(String dishesName, String dishesDiscript, String dishesImg, String dishesTxt, int recommend, double dishesPrice) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        try {

            conn = bd.getConn();
            String sql = "insert into dishesinfo(dishesName,dishesDiscript,dishesImg,dishesTxt,recommend,dishesPrice) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dishesName);
            pstmt.setString(2, dishesDiscript);
            pstmt.setString(3, dishesImg);
            pstmt.setString(4, dishesTxt);
            pstmt.setInt(5, recommend);
            pstmt.setDouble(6, dishesPrice);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    public boolean deleteDishes(int dishesId) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        try {

            conn = bd.getConn();
            String sql = "delete from dishesinfo where dishesId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dishesId);

            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    public boolean updateDestine(int dishesId, String dishesName, String dishesDiscript, String dishesImg, String dishesTxt, int recommend, double dishesPrice) {
        boolean flag = false;
        BaseDao bd = new BaseDao();
        try {

            conn = bd.getConn();
            String sql = "update dishesinfo set dishesName = ?,dishesDiscript = ?,dishesImg = ? ,dishesTxt=?, recommend =?,dishesPrice=? where dishesId = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dishesName);
            pstmt.setString(2, dishesDiscript);
            pstmt.setString(3, dishesImg);
            pstmt.setString(4, dishesTxt);
            pstmt.setInt(5, recommend);
            pstmt.setDouble(6, dishesPrice);
            pstmt.setInt(7, dishesId);

            int i = pstmt.executeUpdate();
            if (i > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return flag;
    }

    public List<Dishesinfo> selectAll() {
        List<Dishesinfo> dishes = new ArrayList<Dishesinfo>();
        BaseDao bd = new BaseDao();
        try {

            conn = bd.getConn();
            String sql = "select * from dishesinfo";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Dishesinfo di = new Dishesinfo();
                di.setDishesId(rs.getInt(1));
                di.setDishesName(rs.getString(2));
                di.setDishesDiscript(rs.getString(3));
                di.setDishesImg(rs.getString(4));
                di.setDishesTxt(rs.getString(5));
                di.setRecommend(rs.getInt(6));
                di.setDishesPrice(rs.getFloat(7));
                dishes.add(di);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return dishes;
    }

    public List<Dishesinfo> selectTop4(){
    	List<Dishesinfo> dishes = new ArrayList<Dishesinfo>();
        BaseDao bd = new BaseDao();
        try {

            conn = bd.getConn();
            String sql = "select * from dishesinfo where recommend=1 limit 4";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Dishesinfo di = new Dishesinfo();
                di.setDishesId(rs.getInt(1));
                di.setDishesName(rs.getString(2));
                di.setDishesDiscript(rs.getString(3));
                di.setDishesImg(rs.getString(4));
                di.setDishesTxt(rs.getString(5));
                di.setRecommend(rs.getInt(6));
                di.setDishesPrice(rs.getFloat(7));
                dishes.add(di);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return dishes;
    }
    
    public double getPriceById(int dishesId){
    	BaseDao bd =new BaseDao();
    	double price = 0;
    	try {

            conn =bd.getConn();
            String sql = "select dishesPrice from dishesinfo where dishesId = ?";
            pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1, dishesId);
            rs=pstmt.executeQuery();
            if(rs.next()){
            	price = rs.getDouble(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
    	return price;
    }
    
    public Dishesinfo getDishById(int dishesId){
        BaseDao bd =new BaseDao();
        Dishesinfo di =null;
        try {

            conn =bd.getConn();
            String sql = "select * from dishesinfo where dishesId = ?";
            pstmt =conn.prepareStatement(sql);
            pstmt.setInt(1, dishesId);
            rs=pstmt.executeQuery();
            if(rs.next()){
                di =new Dishesinfo();
                di.setDishesId(rs.getInt(1));
                di.setDishesName(rs.getString(2));
                di.setDishesDiscript(rs.getString(3));
                di.setDishesImg(rs.getString(4));
                di.setDishesTxt(rs.getString(5));
                di.setRecommend(rs.getInt(6));
                di.setDishesPrice(rs.getFloat(7));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }
        return di;
    }
    public List<Dishesinfo> selectByPageIndex(int from,int count){
        List<Dishesinfo> dishes = new ArrayList<Dishesinfo>();
        BaseDao bd = new BaseDao();
        try{
            conn = bd.getConn();
            String sql = "select * from dishesinfo order by dishesId asc limit ?,?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, from);
            pstmt.setObject(2, count);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Dishesinfo di =new Dishesinfo();
                di.setDishesId(rs.getInt(1));
                di.setDishesName(rs.getString(2));
                di.setDishesDiscript(rs.getString(3));
                di.setDishesImg(rs.getString(4));
                di.setDishesTxt(rs.getString(5));
                di.setRecommend(rs.getInt(6));
                di.setDishesPrice(rs.getFloat(7));
                dishes.add(di);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            bd.closeAll(conn, pstmt, rs);
        }

        return dishes;
    }
/*    public static void main(String[] args) {
        DishesInfoDao dd = new DishesInfoDao();
//        dd.addDishes("test","测试菜品","1.jpg","测试",1,25);
//        dd.deleteDishes(7);
//        dd.updateDestine(8,"test22","测试菜品","1.jpg","测试",1,25);
        List<Dishesinfo> dishes = dd.selectAll();
        for (Dishesinfo d : dishes)
            System.out.println(d.getDishesName());
//        Dishesinfo dish=dd.getDishById(1);System.out.println(dish.getDishesName());
    }*/
}
