package com.hcs.sodaApp.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hcs.sodaApp.model.Soda;
import com.hcs.sodaApp.processor.SodaProcessor;
import com.hcs.sodaApp.utils.Constants;

public class Gui extends JFrame implements ActionListener {

	private JFrame mainFrame = new JFrame("Soda Dispenser");

	JPanel buttonPanel = new JPanel();
	
	private JPanel selectPanel = new JPanel();
	private JPanel coinsPanel = new JPanel();
	private JPanel infoPanel = new JPanel();

	private JButton insertBtn = new JButton(Constants.INSERT);
	private JButton ejectBtn = new JButton(Constants.EJECT);
	private JButton dispenseBtn = new JButton(Constants.DISPENSE);

	private JLabel displayArea = new JLabel("");
	private JLabel displayCoinsArea = new JLabel(Constants.INSERT_COINS_PROMPT);

	private SodaProcessor processor;

	public void init() {

		this.processor = SodaProcessor.getInstance();

		mainFrame.setSize(600, 450);		
		buttonPanel.setBounds(0, 0, 600, 100);
		selectPanel.setBounds(0, 100, 600, 200);
		coinsPanel.setBounds(0, 300, 600, 50);
		infoPanel.setBounds(0, 350, 600, 100);

		
		buttonPanel.setBackground(Color.black);
		selectPanel.setBackground(Color.black);
		coinsPanel.setBackground(Color.black);
		infoPanel.setBackground(Color.black);

		
		insertBtn.setBounds(50, 100, 80, 30);
		insertBtn.setBackground(Color.green);
		insertBtn.setActionCommand(Constants.INSERT);
		insertBtn.addActionListener(this);

		ejectBtn.setBounds(100, 100, 80, 30);
		ejectBtn.setEnabled(false);
		ejectBtn.setBackground(Color.green);
		ejectBtn.setActionCommand(Constants.EJECT);
		ejectBtn.addActionListener(this);

		dispenseBtn.setEnabled(false);
		dispenseBtn.setBounds(100, 100, 80, 30);
		dispenseBtn.setBackground(Color.green);
		dispenseBtn.setActionCommand(Constants.DISPENSE);
		dispenseBtn.addActionListener(this);

		buttonPanel.add(insertBtn);
		buttonPanel.add(ejectBtn);
		buttonPanel.add(dispenseBtn);

		coinsPanel.add(displayCoinsArea);
		infoPanel.add(displayArea);

		mainFrame.add(coinsPanel);
		mainFrame.add(infoPanel);
		mainFrame.add(selectPanel);
		mainFrame.add(buttonPanel);

		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		
		
		displaySelectPanel();
	}

	public void setInfoText(String text){
		displayArea.setText(text);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == Constants.INSERT) {

			processor.insertQuarter(Constants.QUARTER);
			setInfoText("");
			

		} else if (e.getActionCommand() == Constants.EJECT) {

			processor.ejectQuarters();
			setInfoText(Constants.EJECT_COINS_PROMPT);

		} else if (e.getActionCommand() == Constants.DISPENSE) {

			if (processor.dispense()) {
				setInfoText(Constants.COLLECT_DRINK_PROMPT);
			}

		}
		this.postProcess();
	}

	public void postProcess() {

		double currentAmout = processor.getAmount();

		this.displayCoinsArea.setText(String.valueOf(currentAmout));

		ejectBtn.setEnabled(currentAmout > 0 ? true : false);

		this.displaySelectPanel();

		dispenseBtn.setEnabled(processor.getSelectedSoda() != null ? true : false);

	}

	public void clearSelectPanel() {
		selectPanel.removeAll();
		selectPanel.revalidate();
		selectPanel.repaint();
	}

	public void displaySelectPanel() {

		clearSelectPanel();

		Set<Soda> sodas = processor.getAll();

		for (Soda soda : sodas) {

			boolean isSoldOut = soda.getQuantity() <= 0;
			String label = soda.getBrand();
			
			if(isSoldOut) {
				label = label + " " + Constants.SOLD_OUT;
			} else {
				label = label + "   $" + soda.getPrice();
			}
			JButton j = new JButton(label );
			
			j.setBounds(50, 100, 80, 30);
			
			j.setBackground(!isSoldOut ? Color.WHITE : Color.LIGHT_GRAY);

			j.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					processor.select(soda);
					postProcess();
				}
			});

			j.setEnabled(processor.getAmount() >= soda.getPrice() && !isSoldOut ? true : false);
			selectPanel.add(j);
		}

		mainFrame.revalidate();
		mainFrame.repaint();
	}

}
