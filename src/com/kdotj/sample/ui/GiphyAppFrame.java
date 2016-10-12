package com.kdotj.sample.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.swing.JFrame;

import com.kdotj.sample.interfaces.ActionsCallback;
import com.kdotj.sample.interfaces.GiphyTaskCallback;
import com.kdotj.sample.interfaces.SettingsCallback;
import com.kdotj.sample.tasks.GiphyTask;
import com.kdotj.simplegiphy.data.RandomGiphyResponse;

/**
 * Main App UI
 * <p>This is the entry point for the Application, this draws the App Frame and adds UI compenonts
 * then starts the GiphyTask to get Random Giphy's at a polling interval</p>
 * @author kyle.jablonski
 *
 */
public class GiphyAppFrame extends JFrame implements GiphyTaskCallback, SettingsCallback, ActionsCallback{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -4215733241608863419L;
	
	/**
	 * Settings Panel
	 */
	private SettingsPanel mSettingsPanel;
	
	/**
	 * Master Panel
	 */
	private MasterPanel mMasterPanel;
	
	/**
	 * Actions Panel
	 */
	private ActionsPanel mActionsPanel;
	
	/**
	 * Giphy Task to get a new Giphy
	 */
	private GiphyTask mGiphyTask;
	
	/**
	 * Timer to schedule the GiphyTask
	 */
	private Timer timer;
	
	private long interval = 5000L;

	/**
	 * Constructor
	 */
	public GiphyAppFrame(){
		super("Simple Giphy Demo");
		mMasterPanel = new MasterPanel(this);
		mSettingsPanel = new SettingsPanel(this);
		mActionsPanel = new ActionsPanel(this);
		
		setLayout(new GridBagLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		GridBagConstraints constraints = new GridBagConstraints();
	
		// Settings at the Top
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = .2;
		constraints.weightx = 1.0;
		add(mSettingsPanel, constraints);
		mGiphyTask = new GiphyTask(this);
		
		// Master Panel in the center
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = .6; 		
		add(mMasterPanel, constraints);
		
		// Actions Panel on the bottom
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = .2; 		
		constraints.weightx = 1.0;
		add(mActionsPanel, constraints);
		
		addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if(timer != null){
					timer.cancel();
				}
			}
		});

		pack();
	}
	
	@Override
	public Insets getInsets(){
		return new Insets(16,16,16,16);
	}
	
	@Override
	public Dimension getPreferredSize() {
		// width, height
		return new Dimension(496, 580);
	}
	
	/**
	 * Starts the app
	 */
	public void startApp(){
		timer = new Timer();
		mGiphyTask = null;
		mGiphyTask = new GiphyTask(this);
		timer.scheduleAtFixedRate(mGiphyTask, 0, interval);
		mActionsPanel.disableStart();
	}

	@Override
	public void onTagChanged(String newTag) {
		mGiphyTask.setTag(newTag);
	}

	@Override
	public void onRatingChanged(String newRating) {
		mGiphyTask.setRating(newRating);
	}
	
	@Override
	public void onPollingChanged(long interval) {
		this.interval = interval;
		if(timer != null){
			timer.cancel();
			timer.purge();
		}
		
		startApp();
	}

	@Override
	public void onSkipGiphy() {
		mGiphyTask.getNextGiphy();
	}

	@Override
	public void onNextGiphy(RandomGiphyResponse giphyResponse) {
		mMasterPanel.updateLblIcon(giphyResponse);
	}

	@Override
	public void onStop() {
		timer.cancel();
		timer.purge();
		mActionsPanel.disableStop();
	}

	@Override
	public void onStart() {
		startApp();
		mActionsPanel.disableStart();
	}

}
