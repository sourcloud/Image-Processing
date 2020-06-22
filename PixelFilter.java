package de.studium.image_processing;

import java.awt.image.BufferedImage;


/**
 * PixelFilters create a new image by traversing each pixel of an image and
 * performing a calculation based on its color.
 */
public abstract class PixelFilter implements Filter {

	
	/**
	 * Extracts color values from image and creates output array of the same
	 * size. For each pixel checks if masked. If not, assigns calculated value
	 * to output array, else assigns pixel value.
	 * Creates and returns image out of output array.
	 */
	public BufferedImage process(BufferedImage ...images) {
		
		BufferedImage image, mask;
		
		image = (images.length > 0) ? images[0] : null;
		mask = (images.length > 1) ? images[1] : null;
		
		if (image == null)	// nothing to process
			return null;
		
		BufferedImage output;
		int[] imagePixel, maskPixel, outputPixel;
		int width, height;

		width = image.getWidth();
		height = image.getHeight();

		imagePixel = image.getRGB(0, 0, width, height, null, 0, width);
		outputPixel = new int[imagePixel.length];
				
		maskPixel = (mask != null) 
				? mask.getRGB(0, 0, width, height, null, 0, width) 
				: null;	
				
		for (int i = 0; i < imagePixel.length; i++)
			if (maskPixel == null || maskPixel[i] > 0xff000000)
				outputPixel[i] = calculate(imagePixel[i]);
			else
				outputPixel[i] = imagePixel[i];
		
		output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		output.setRGB(0, 0, width, height, outputPixel, 0, width);
		return output;
	
	}
	
	protected abstract int calculate(int pixelColor);
}

