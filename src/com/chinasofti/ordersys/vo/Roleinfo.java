package com.chinasofti.ordersys.vo;

public class Roleinfo {
	private int roleId;//角色编号（1：餐厅管理员   2：后厨人员     3：餐厅服务员）
	private String roleName;//角色名称，以上三个
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
