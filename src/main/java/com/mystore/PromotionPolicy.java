package com.mystore;

public class PromotionPolicy {
	
	private int regulartBuy;
	
	private int bonus;
	
	public PromotionPolicy() { }
	
	public PromotionPolicy(int regulartBuy, int bonus) { 
		this.regulartBuy = regulartBuy;
		this.bonus = bonus;
	}

	public int getRegulartBuy() {
		return regulartBuy;
	}

	public void setRegulartBuy(int regulartBuy) {
		this.regulartBuy = regulartBuy;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
