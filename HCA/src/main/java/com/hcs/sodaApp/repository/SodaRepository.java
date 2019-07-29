package com.hcs.sodaApp.repository;

import java.util.Set;

import com.hcs.sodaApp.dao.Dao;
import com.hcs.sodaApp.model.Soda;

public class SodaRepository extends Dao {

	public SodaRepository() {
		super();
	}
	
	@Override
	public Soda get(String brand) {
		return super.get(brand);
	}
	
	@Override
	public Set<Soda> findAllBrands(){
		return super.findAllBrands();
	}
	

}
