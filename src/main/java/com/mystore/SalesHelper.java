package com.mystore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//This class is used to load data into memory. 
//In real world, most probably read from database and maintain in cache for frequent use.

public class SalesHelper {

	private static List<String> products = new ArrayList<>();
	
	private static List<String> promotionPolicies = new ArrayList<>();
	
	private static List<Product> productList = new ArrayList<>();
	
	//init Product and Promotion Policy
	static {	
		products = readDataFile("src/main/resources/products");
		promotionPolicies = readDataFile("src/main/resources/promotion_policies");	
		createProductList();
	}
	
	private static List<String> readDataFile(String path) {
		List<String> retVal = new ArrayList<>();
		
		try {
			Files.lines(Paths.get(path))
				.forEach(line -> retVal.add(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//remove header
		retVal.remove(0);
		
		return retVal;
	}
	
	private static void createProductList() {
		
		products.stream().forEach(
			p -> {		
				String[] tokens = p.split(" ");
				int id = Integer.parseInt(tokens[0]);
				String name = tokens[1];
				double price = Double.parseDouble(tokens[2]);
				PromotionPolicy policy =  getPromotionPolicyById(id);
					
				productList.add(new Product(id, name, price, policy));
			}		
		);
	}
	
	private static PromotionPolicy getPromotionPolicyById(int id) {
		
		for(String policy : promotionPolicies) {
			String[] tokens = policy.split(" ");
			int _id = Integer.parseInt(tokens[0]);
			int regulartBuy = Integer.parseInt(tokens[1]);
			int bonus = Integer.parseInt(tokens[2]);
			if(_id == id) 
				return new PromotionPolicy(regulartBuy, bonus);
		}
		
		return new PromotionPolicy(1, 0);
	}
	
	//
	public static Product getProductByName(String name) {
		return productList.stream()
			.filter(product -> product.getName().equals(name))
			.findFirst()
			.orElse(null);
	}
	
	public static void main(String[] args) {
		
		System.out.println(productList.size());
		
		Product apple = getProductByName("Apple");
		System.out.println(apple.getId());
		System.out.println(apple.getName());
		System.out.println(apple.getPrice());
		System.out.println(apple.getPromotionPolicy().getRegulartBuy() + " " + apple.getPromotionPolicy().getBonus());
		
	}
	
}
