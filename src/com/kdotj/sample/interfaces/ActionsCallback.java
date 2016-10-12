package com.kdotj.sample.interfaces;

/**
 * Callback interface for the Actions Panel
 * @author kyle.jablonski
 *
 */
public interface ActionsCallback {

	/**
	 * Skips the current giphy
	 */
	void onSkipGiphy();
	
	/**
	 * Stops the Timer task
	 */
	void onStop();
	
	/**
	 * starts the Timer task
	 */
	void onStart();
}
