package com.kdotj.sample.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.kdotj.sample.interfaces.SettingsCallback;

/**
 * Settings Panel which has Options to Change the Tag, Rating, or Skip the current Giphy
 * @author kyle.jablonski
 *
 */
public class SettingsPanel extends JPanel{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 3694794324091994167L;
	
	/**
	 * Arrays for the Combo Boxes
	 */
	private static final String [] TAGS = {"Animals","Meme","Holidays","Emotions","Anime","Gaming","Cartoons","Sports","Nature"};
	private static final String [] RATINGS = {"Y","G", "PG", "PG-13", "R"};
	private static final String [] POLLING = {"5000","10000", "15000", "30000", "45000", "60000"};
	
	/**
	 * Callback to invoke changes in Settings
	 */
	private SettingsCallback callback;
	
	/**
	 * Default insets
	 */
	private Insets insets = new Insets(8,8,8,8);
	
	/**
	 * Constructor
	 * @param callback callback to make changes in Behavior of the API call
	 */
	public SettingsPanel(SettingsCallback callback){
		this.callback = callback;
		// Set the Layout options for JPanel
		setLayout(new GridBagLayout());
		Border linBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border titleBorder = BorderFactory.createTitledBorder(linBorder, "Settings");
		setBorder(titleBorder);
		
		buildSettingsUI();
	}
	
	/**
	 * Builds the UI using GridBagContraints
	 */
	private void buildSettingsUI(){
		
		JComboBox<String> cbTags = new JComboBox<String>(TAGS);
		JComboBox<String> cbRatings = new JComboBox<String>(RATINGS);
		JComboBox<String> cbPolling = new JComboBox<String>(POLLING);
		
		JLabel lblTags = new JLabel("Tags");
		JLabel lblRating = new JLabel("Rating");
		JLabel lblPolling = new JLabel("Polling");
		
		cbTags.addActionListener(TagListener);
		cbRatings.addActionListener(RatingListener);
		cbPolling.addActionListener(PollingListener);
		
		// Configure positions
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = insets;
		
		// Fill horizontal, 0,0
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(lblTags, constraints);

		// Fill horizontal, 0,1
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1.0;
		add(cbTags, constraints);
		
		// Fill horizontal, 1,0
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(lblRating, constraints);
		
		// Fill horizontal, 1,1
		constraints.gridx = 1;
		constraints.gridy = 1;
		add(cbRatings, constraints);
		
		// Fill horizontal 
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		constraints.gridy = 0;
		add(lblPolling, constraints);
		
		// Fill horizontal, 2,1
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(cbPolling, constraints);
		
	}
	
	/*
	 *  Listeners
	 */
	@SuppressWarnings("unchecked")
	private final ActionListener TagListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			String item = (String) cb.getSelectedItem();
			// Invoke callback method here
			System.out.println(item);
			callback.onTagChanged(item.toLowerCase());
		}
		
	};
	
	@SuppressWarnings("unchecked")
	private final ActionListener RatingListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			String item = (String) cb.getSelectedItem();
			System.out.println(item);
			//Invoke callback method here
			callback.onRatingChanged(item.toLowerCase());
		}
						
    };
    
    @SuppressWarnings("unchecked")
    private final ActionListener PollingListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			String item = (String) cb.getSelectedItem();
			System.out.println(item);
			callback.onPollingChanged(Long.parseLong(item));
		}
    	
    };

}
