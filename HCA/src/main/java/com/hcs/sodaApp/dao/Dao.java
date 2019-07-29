package com.hcs.sodaApp.dao;

import java.util.Set;

import com.hcs.sodaApp.model.Soda;

public class Dao extends LocalStorage {

	public Dao() {
		super();
	}
	
	public void initDatabase() {
		
	}
	
	public void save() {
		
	}
	
	public void update() {
		
	}
	
	@Override
	public Soda get(String brand) {
		return super.get(brand);
	}
	
	@Override
	public Set<Soda> findAllBrands(){
		return super.findAllBrands();
	}
	
	
	public void delete() {
		
	}
	
	
	
}
