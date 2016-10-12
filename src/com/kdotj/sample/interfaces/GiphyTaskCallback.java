package com.kdotj.sample.interfaces;

import com.kdotj.simplegiphy.data.RandomGiphyResponse;

/**
 * Interface to Change the Giphy
 * @author kyle.jablonski
 *
 */
public interface GiphyTaskCallback {
	
	/**
	 * Callback to change to the new Giphy
	 * @param giphyResponse the new Random Giphy
	 */
	void onNextGiphy(RandomGiphyResponse giphyResponse);
}
