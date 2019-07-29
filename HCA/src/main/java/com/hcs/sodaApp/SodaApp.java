package com.hcs.sodaApp;

import com.hcs.sodaApp.gui.Gui;

public class SodaApp {

	public static void main(String[] args) {
		SodaApp sodaApp = new SodaApp();
		sodaApp.run();
	}
	
	public void run() {
		Gui gui = new Gui();
		gui.init();		
	}
}
