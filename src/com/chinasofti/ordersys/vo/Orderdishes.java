package com.chinasofti.ordersys.vo;
//管理订单菜品信息
public class Orderdishes {
	private int odId;//管理订单编号
	private int orderReference;//订单号
	private int dishes;//菜品号
	private int num;//菜品数量
	private int dishstatic;
	public int getOdId() {
		return odId;
	}
	public void setOdId(int odId) {
		this.odId = odId;
	}
	public int getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(int orderReference) {
		this.orderReference = orderReference;
	}
	public int getDishes() {
		return dishes;
	}
	public void setDishes(int dishes) {
		this.dishes = dishes;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getDishstatic() {
		return dishstatic;
	}
	public void setDishstatic(int dishstatic) {
		this.dishstatic = dishstatic;
	}
    

}
