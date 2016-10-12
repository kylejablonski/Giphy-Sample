package com.kdotj.sample.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.kdotj.sample.interfaces.ActionsCallback;
import com.kdotj.sample.tasks.GiphyTask;

/**
 * JPanel containing Action Buttons
 * @author kyle.jablonski
 *
 */
public class ActionsPanel extends JPanel{

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -8050707345660681214L;
	
	/**
	 * Callback for invoking Actions 
	 */
	private ActionsCallback callback;
	
	/**
	 * Start button
	 */
	private JButton btnStart = new JButton("START");
	
	/**
	 * Stop button
	 */
	private JButton btnStop = new JButton("STOP");
	
	/**
	 * Skip button
	 */
	private JButton btnSkip = new JButton("SKIP");
	
	private Insets insets = new Insets(8,8,8,8);
	
	/**
	 * Constructor
	 * @param callback callback to invoke changes in the {@link GiphyTask}
	 */
	public ActionsPanel(ActionsCallback callback){
		this.callback = callback;
		setLayout(new GridBagLayout());
		Border linBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border titleBorder = BorderFactory.createTitledBorder(linBorder, "Actions");
		setBorder(titleBorder);
		
		buildUI();
	}
	
	/**
	 * Draws the UI using GridBagConstraints
	 */
	private void buildUI(){
		
		btnSkip.setEnabled(false);
		btnStart.addActionListener(StartListener);
		btnStop.addActionListener(StopListener);
		btnStop.setEnabled(false);
		btnSkip.addActionListener(SkipListener);
		
		// Configure positions
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = insets;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		add(btnStart, constraints);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(btnStop, constraints);
		
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(btnSkip, constraints);
	}
	
	public void disableStart(){
		btnStart.setEnabled(false);
		btnStop.setEnabled(true);
		btnSkip.setEnabled(true);
	}
	
	public void disableStop(){
		btnStop.setEnabled(false);
		btnStart.setEnabled(true);
		btnSkip.setEnabled(false);
	}
	
	/*
	 *  Listeners
	 */
	
	private final ActionListener StartListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// invoke callback to start the task
			callback.onStart();
		}
	};
	
	private final ActionListener SkipListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// invoke callback to get next giphy
			callback.onSkipGiphy();
		}
		
	};
	
	private final ActionListener StopListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// invoke callback to stop the task
			callback.onStop();
		}
		
	};

}
