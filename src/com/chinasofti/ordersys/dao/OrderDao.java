package com.chinasofti.ordersys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.ordersys.vo.Orderinfo;

public class OrderDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<Orderinfo> SelectAllorders(){
		List<Orderinfo> orders = new ArrayList<Orderinfo>();
		BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "select * from orderinfo";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Orderinfo or = new Orderinfo();
				or.setOrderId(rs.getInt(1));
				or.setOrderBeginDate(rs.getString(2));
				or.setOrderEndDate(rs.getString(3));
				or.setWaiterId(rs.getInt(4));
				or.setOrderState(rs.getInt(5));
				or.setTableId(rs.getInt(6));
				orders.add(or);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	//通过订单状态查询
	public List<Orderinfo> getOrdersByState(int orderState){
		List<Orderinfo> orders = new ArrayList<Orderinfo>();
		BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "select * from orderinfo where orderState = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderState);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Orderinfo or = new Orderinfo();
				or.setOrderId(rs.getInt(1));
				or.setOrderBeginDate(rs.getString(2));
				or.setOrderEndDate(rs.getString(3));
				or.setWaiterId(rs.getInt(4));
				or.setOrderState(rs.getInt(5));
				or.setTableId(rs.getInt(6));
				orders.add(or);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<Orderinfo> selectSomeDishesByState(int from,int count,int orderState){
	    List<Orderinfo> orders = new ArrayList<Orderinfo>();
	    BaseDao bd = new BaseDao();
	    try{
	        conn = bd.getConn();
	        String sql = "select * from orderinfo where orderState = ? order by orderId asc limit ?,?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setObject(1, orderState);
	        pstmt.setObject(2, from);
	        pstmt.setObject(3, count);
	        rs = pstmt.executeQuery();
	        while(rs.next()){
	            Orderinfo or = new Orderinfo();
	            or.setOrderId(rs.getInt(1));
	            or.setOrderBeginDate(rs.getString(2));
	            or.setOrderEndDate(rs.getString(3));
	            or.setWaiterId(rs.getInt(4));
	            or.setOrderState(rs.getInt(5));
	            or.setTableId(rs.getInt(6));
	            orders.add(or);
	        }
	    }catch(SQLException e){
	        e.printStackTrace();
	    }finally{
	        bd.closeAll(conn, pstmt, rs);
	    }
	    return orders;
	}
	
	public List<Orderinfo> selectSomeOrderByTime(String begin,String end,int orderState){
		 List<Orderinfo> orders = new ArrayList<Orderinfo>();
		 BaseDao bd = new BaseDao();
		 try{
		        conn = bd.getConn();
		        String sql = "select * from orderinfo where orderState=? and orderEndDate between ? and ?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setObject(1, orderState);
		        pstmt.setString(2, begin);
		        pstmt.setString(3, end);
		        rs = pstmt.executeQuery();
		        while(rs.next()){
		            Orderinfo or = new Orderinfo();
		            or.setOrderId(rs.getInt(1));
		            or.setOrderBeginDate(rs.getString(2));
		            or.setOrderEndDate(rs.getString(3));
		            or.setWaiterId(rs.getInt(4));
		            or.setOrderState(rs.getInt(5));
		            or.setTableId(rs.getInt(6));
		            orders.add(or);
		        }
		    }catch(SQLException e){
		        e.printStackTrace();
		    }finally{
		        bd.closeAll(conn, pstmt, rs);
		    }
		 return orders;
	}
	
	public Orderinfo getOrderById(int orderId){
		Orderinfo or = new Orderinfo();
		BaseDao db = new BaseDao();
		try {
			conn = db.getConn();
			String sql = "select * from orderinfo where orderId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			rs.next();
			or.setOrderId(rs.getInt(1));
			or.setOrderBeginDate(rs.getString(2));
			or.setOrderEndDate(rs.getString(3));
			or.setWaiterId(rs.getInt(4));
			or.setOrderState(rs.getInt(5));
			or.setTableId(rs.getInt(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
        	db.closeAll(conn, pstmt, rs);
        }
		return or;
	}
	//根据桌号查询当前订单号，没有订单返回-1
	public int getNowIdBytable(int tableId){
		int orderId = -1;
		BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "select orderId from orderinfo where tableId = ? and orderState = 0";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tableId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				orderId = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}

	public int getmaxOrderId(){
	    int i=0;
	    BaseDao bd=new BaseDao();
	    try {
	        Connection conn = bd.getConn();
	        String sql = "select max(orderId) from orderinfo";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            i = rs.getInt(1);
	        }
	        }
	    catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return i;
	}

	
    public boolean addOrder(String orderBeginDate,int waiterId,int orderState,int tableId){
    	boolean flag = false;
    	BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "insert into orderinfo(orderBeginDate,waiterId,orderState,tableId) values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderBeginDate);
			pstmt.setInt(2, waiterId);
			pstmt.setInt(3, orderState);
			pstmt.setInt(4, tableId);
			int i = pstmt.executeUpdate();
			if(i>0) {flag = true;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }

    public boolean delOrderById(int orderId){
    	boolean flag = false;
    	BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "delete from orderinfo where orderId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			int i = pstmt.executeUpdate();
			if(i>0) {flag = true;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }

    public boolean updateOrderStateByorderId(int orderId,int orderState){
    	boolean flag = false;
    	BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "update orderinfo set orderState=? where orderId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderState);
            pstmt.setInt(2, orderId);
			int i = pstmt.executeUpdate();
			if(i>0) {flag = true;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }

    public boolean updateEndDateById(int orderId,String orderEndDate){
    	boolean flag = false;
    	BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "update orderinfo set orderEndDate=? where orderId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderEndDate);
            pstmt.setInt(2, orderId);
			int i = pstmt.executeUpdate();
			if(i>0) {flag = true;}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }

/*   public static void main(String[] args) {
	   OrderDao aa = new OrderDao();
	   Orderinfo or = aa.getOrderById(4);
	   System.out.println(or.getTableId());
}*/

}
