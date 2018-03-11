package com.chinasofti.ordersys.vo;
//菜品信息
public class Dishesinfo {
	private int dishesId;//菜品号
	private String dishesName;//菜品名
	private String dishesDiscript;//菜品简介
	private String dishesImg;//菜品图片
	private String dishesTxt;//菜品说明
	private int recommend;//是否推荐（0,1）
	private double dishesPrice;//菜品单价
	public int getDishesId() {
		return dishesId;
	}
	public void setDishesId(int dishesId) {
		this.dishesId = dishesId;
	}
	public String getDishesName() {
		return dishesName;
	}
	public void setDishesName(String dishesName) {
		this.dishesName = dishesName;
	}
	public String getDishesDiscript() {
		return dishesDiscript;
	}
	public void setDishesDiscript(String dishesDiscript) {
		this.dishesDiscript = dishesDiscript;
	}
	public String getDishesImg() {
		return dishesImg;
	}
	public void setDishesImg(String dishesImg) {
		this.dishesImg = dishesImg;
	}
	public String getDishesTxt() {
		return dishesTxt;
	}
	public void setDishesTxt(String dishesTxt) {
		this.dishesTxt = dishesTxt;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public double getDishesPrice() {
		return dishesPrice;
	}
	public void setDishesPrice(double dishesPrice) {
		this.dishesPrice = dishesPrice;
	}

}
