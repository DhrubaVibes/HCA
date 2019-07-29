package com.hcs.sodaApp.model;


public class Soda implements Comparable{

	private String type;
	private double price;
	private String brand;
	private int quantity;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	@Override
	public String toString() {
		return "Soda [type=" + type + ", price=" + price + ", brand=" + brand + ", quantity=" + quantity + "]";
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Soda s = (Soda) o;
		return brand.compareTo(s.getBrand());
	}
	
	
	
	
}
