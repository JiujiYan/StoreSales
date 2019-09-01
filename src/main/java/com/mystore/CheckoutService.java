package com.mystore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutService {
	
	CheckoutService() {
	}

	private double calculateSubTotalPrice(String name, long count) {
		Product product = SalesHelper.getProductByName(name);
		PromotionPolicy policy = product.getPromotionPolicy();
		
		int leastBuyForBonus = policy.getRegulartBuy() + policy.getBonus();
		int numBonusBatch = (int) (count / leastBuyForBonus);
		int numRegularPrice = (int) (count % leastBuyForBonus);
		
		return product.getPrice() * (numBonusBatch * policy.getRegulartBuy() + numRegularPrice);
	}
	
	public double calculateTotalPrice(String productItems) {
		double totalPrice = 0.0;
		
		Map<String, Long> itemsCountMap = (Map<String, Long>) Arrays.asList(productItems.split(","))
											.stream()
											.collect(Collectors.groupingBy(s -> s.trim(), Collectors.counting()));
		
		for (Map.Entry<String, Long> entry : itemsCountMap.entrySet()) {
			totalPrice += calculateSubTotalPrice(entry.getKey(), entry.getValue());
		}
		
		return totalPrice;
	}
	
	public static void main(String[] args) throws IOException {
		CheckoutService service = new CheckoutService();
		
		//System.out.println(service.calculateSubTotalPrice("Apple", 8));
		//System.out.println(service.calculateSubTotalPrice("Orange", 4));
		
		//String items = "Apple, Apple, Orange, Apple";
		
		//System.out.println(service.calculateTotalPrice(items));
		
		System.out.println("Please input the checkout items:");
		System.out.println("(Format -- item name separated with comma, like 'Apple, Apple, Orange, Apple')");
		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		String input = reader.readLine(); 
		
		System.out.println("Total Price = £" + service.calculateTotalPrice(input));
	}
	
}
