package com.kdotj.sample.tasks;

import java.util.TimerTask;

import com.kdotj.sample.interfaces.GiphyTaskCallback;
import com.kdotj.simplegiphy.SimpleGiphy;
import com.kdotj.simplegiphy.data.RandomGiphyResponse;

/**
 * Timer task which polls the giphy Api via the SimpleGiphy Java Library
 * @author kyle.jablonski
 *
 */
public class GiphyTask extends TimerTask{
	
	/**
	 * Default tag
	 */
	private String tag = "animals";
	
	/**
	 * Default Rating
	 */
	private String rating = "y";
	
	/**
	 * Callback for when a new Giphy is retrieved from the API
	 */
	private GiphyTaskCallback callback;
	
	/**
	 * Constructor
	 * @param callback callback to invoke after giphy is fetched
	 */
	public GiphyTask(GiphyTaskCallback callback){
		this.callback = callback;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
		
	/**
	 * Gets the next Giphy
	 */
	public void getNextGiphy(){
		RandomGiphyResponse giphy = SimpleGiphy.getInstance().random(tag, rating);
		
		// invoke interface callback here
		callback.onNextGiphy(giphy);
	}

	@Override
	public void run() {
		getNextGiphy();
	}

}
