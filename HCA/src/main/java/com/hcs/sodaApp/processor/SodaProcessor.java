package com.hcs.sodaApp.processor;

import java.util.Set;

import com.hcs.sodaApp.model.Soda;
import com.hcs.sodaApp.repository.SodaRepository;

public class SodaProcessor {

	private static SodaProcessor instance;
	private SodaRepository sodaRepository;
	private double amount;
	private Soda selectedSoda;

	private SodaProcessor() {
		this.sodaRepository = new SodaRepository();
		this.amount = 0;
	}

	public static SodaProcessor getInstance() {
		if (instance == null) {
			instance = new SodaProcessor();
		}
		return instance;
	}

	public double insertQuarter(double value) {
		this.amount = this.amount + value;
		return this.amount;
	}

	public void ejectQuarters() {
		this.amount = 0;
		this.selectedSoda = null;
	}

	public void select(Soda soda) {
		this.selectedSoda = soda;
	}

	public boolean dispense() {
		Soda soda = sodaRepository.get(this.selectedSoda.getBrand());
		if (soda != null) {
			amount = amount - soda.getPrice();
			this.selectedSoda = null;
			return true;
		}
		return false;


	}

	public double getCurrentAmount() {
		return this.amount;
	}

	public Set<Soda> getAll() {

		return sodaRepository.findAllBrands();
	}

	public SodaRepository getSodaRepository() {
		return sodaRepository;
	}

	public void setSodaRepository(SodaRepository sodaRepository) {
		this.sodaRepository = sodaRepository;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Soda getSelectedSoda() {
		return selectedSoda;
	}

	public void setSelectedSoda(Soda selectedSoda) {
		this.selectedSoda = selectedSoda;
	}

	public static void setInstance(SodaProcessor instance) {
		SodaProcessor.instance = instance;
	}

}
