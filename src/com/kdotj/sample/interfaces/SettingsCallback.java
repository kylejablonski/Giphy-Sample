package com.kdotj.sample.interfaces;

/**
 * Interface for changes in API call Settings
 * @author kyle.jablonski
 *
 */
public interface SettingsCallback {

	/**
	 * Notifies the Tag has changed
	 * @param newTag the new tag
	 */
	void onTagChanged(String newTag);
	
	/**
	 * Notifies the rating has changed
	 * @param newRating the new rating
	 */
	void onRatingChanged(String newRating);
	
	/**
	 * Notifies the polling interval changed
	 * @param interval polling interval
	 */
	void onPollingChanged(long interval);
}
