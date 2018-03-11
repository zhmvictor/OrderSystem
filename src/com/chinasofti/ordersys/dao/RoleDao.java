package com.chinasofti.ordersys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class RoleDao {

	public String getRoleNameById(int roleId){    //错误返回""
		String roleName = "";
		BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "select roleName from roleinfo where roleId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roleId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			roleName = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleName;
	}
	public int getRoleIdByName(String roleName){    //错误返回-1
		int roleId = -1;
		BaseDao db = new BaseDao();
		try {
			Connection conn = db.getConn();
			String sql = "select roleId from roleinfo where roleName = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roleName);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			roleId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleId;
	}
}
