package com.mystore;

public class Product {
	
	private int id;
	
	private String name;
	
	private double price;
	
	private PromotionPolicy promotionPolicy;

	public Product() { }
	
	public Product(int id, String name, double price, PromotionPolicy promotionPolicy) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.promotionPolicy = promotionPolicy;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public PromotionPolicy getPromotionPolicy() {
		return promotionPolicy;
	}

	public void setPromotionPolicy(PromotionPolicy promotionPolicy) {
		this.promotionPolicy = promotionPolicy;
	}
}
