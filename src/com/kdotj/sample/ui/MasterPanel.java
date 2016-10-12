package com.kdotj.sample.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.kdotj.sample.interfaces.GiphyTaskCallback;
import com.kdotj.simplegiphy.data.RandomGiphy;
import com.kdotj.simplegiphy.data.RandomGiphyResponse;

/**
 * Displays the Giphy in the Center of the App
 * @author kyle.jablonski
 *
 */
public class MasterPanel extends JPanel{

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -5321888192070402391L;
	
	/**
	 * Label displaying the Giphy
	 */
	private JLabel mLblImageWithText;
	
	/**
	 * Default insets
	 */
	private Insets insets = new Insets(16,16,16,16);
	
	/**
	 * Constructor
	 * @param callback callback to invoke changes for the Image
	 */
	public MasterPanel(GiphyTaskCallback callback){
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
		Border linBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border titleBorder = BorderFactory.createTitledBorder(linBorder, "Random Giphy");
		setBorder(titleBorder);
		setLayout(new GridBagLayout());
		drawUI();
	}
	
	@Override
	public Dimension getPreferredSize() {
		// width, height
		return new Dimension(300,300);
	}

	/**
	 * Draws the UI using GridBagConstraints
	 */
	private void drawUI(){
		mLblImageWithText = new JLabel();
		mLblImageWithText.setHorizontalTextPosition(JLabel.CENTER);
		mLblImageWithText.setVerticalTextPosition(JLabel.BOTTOM);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = insets;
		constraints.fill = GridBagConstraints.BOTH ;
	
		add(mLblImageWithText, constraints);
	}
	
	/**
	 * Updates the Icon with a new Random Giphy
	 * @param giphy the random giphy to display
	 */
	public void updateLblIcon(RandomGiphyResponse giphy){
		String fileName = null;
		if(giphy != null){
			RandomGiphy randomGiphy = giphy.getRandomGiphy();
			if(randomGiphy != null){
				fileName = randomGiphy.getFixedHeightDownsampledUrl();
				System.out.println(fileName);
				
				URL url = null;
				try {
					url = new URL(fileName);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				if(url != null){
					ImageIcon icon = new ImageIcon(url);
					mLblImageWithText.setIcon(icon);
				}
			}
		}
	}
}
