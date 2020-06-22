package de.studium.image_processing;

import java.awt.image.BufferedImage;

/**
 * Classes that implement the Filter interface must provide a method to
 * process given images.
 */
public interface Filter {
	
	public BufferedImage process(BufferedImage ... input);

}
