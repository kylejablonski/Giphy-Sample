package com.kdotj.sample;

import com.kdotj.sample.ui.GiphyAppFrame;

/**
 * Entry point for the Application, it creates a new GiphyAppFrame
 * @author kyle.jablonski
 *
 */
public class GiphyApp {
	
	public static void main(String [] args){
		GiphyAppFrame appFrame = new GiphyAppFrame();
		appFrame.setVisible(true);
	}
}
