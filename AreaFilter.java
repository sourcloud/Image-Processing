package de.studium.image_processing;

import java.awt.image.BufferedImage;

/**
 * An area filter traverses each pixel of an image and manipulates it based on
 * the surrounding pixels.
 * 
 * E.g. if the image is represented by
 * 
 * 		1  2  1  2  1  2
 * 
 * 		2  1  2  1  2  1
 * 
 * 		1  2  1  2  1  2
 * 
 * 		2  1  2  1  2  1
 * 
 * 		1  2  1  2  1  2
 * 
 * and the filter area is 3, it traverses areas like
 * 
 *		1  2  1  2  1  2
 *			  -------
 * 		2  1 |2  1  2| 1
 * 		     |       |
 * 		1  2 |1  2  1| 2
 *           |   	 |
 * 		2  1 |2  1  2| 1
 *            -------
 * 		1  2  1  2  1  2
 * 
 * from top-left to bottom-right. In case of pixels closer to the edge
 * 
 * 		1  2  1  
 *		   ------- 		
 *      2 |1  2   |
 *        |       |
 *      1 |2  1   |
 *        |       |
 *        |       |
 * 		   -------
 * 
 * the part outside of the image pixels will be ignored.
 * 
 * @see https://www.youtube.com/watch?v=C_zFhWdM4ic
 *
 */
public abstract class AreaFilter implements Filter {
	
	protected final int IGNORE_PIXEL = -1; // mark masked or overlapped pixels
	protected int size;
	
	/**
	 * AreaFilters need to have a size.
	 * 
	 * @param size (int) size of filter
	 */
	public AreaFilter(int size) {
		this.size = size;
	}
	
	/**
	 * Extracts color values from image and creates output array of the same
	 * size. For each pixel checks if masked. If not, assigns calculated value
	 * from surrounding pixels to output array, else assigns pixel value.
	 * Creates and returns image out of output array.
	 */
	public BufferedImage process(BufferedImage ...input){
		
		BufferedImage image, mask;
		
		image = (input.length > 0) ? input[0] : null;
		mask = (input.length > 1) ? input[1] : null;
		
		if (image == null) // nothing to process;
			return null;
		
		BufferedImage output;
		int[] imageArray, maskArray, outputArray;
		int width, height;
		
		width = image.getWidth();
		height = image.getHeight();
		
		imageArray = image.getRGB(0, 0, width, height, null, 0, width);
		outputArray = new int[imageArray.length];
		
		maskArray = (mask != null) 
				? mask.getRGB(0, 0, width, height, null, 0, width)
				: null;
				
		for (int i = 0; i < imageArray.length; i++) {
			
			outputArray[i] = (mask == null || maskArray[i] > 0xff000000)
					? calculate(imageArray, maskArray, i, width, height)
					: imageArray[i];
					
		}
		
		output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		output.setRGB(0, 0, width, height, outputArray, 0, width);
		return output;
	}
	
	
	protected abstract int calculate(int[] pixel, int[] maskPixel, int index, 
			int width, int height);
	
	/**
	 * Calculates the average color of a array of pixels by calculating
	 * average RGB values.
	 * 
	 * @param pixelArray (int[]) array of color values
	 * @return (int) Average color value
	 */
	protected int getAveragePixelColor(int[] pixelArray) {
		
		int redSum = 0;
		int blueSum = 0;
		int greenSum = 0;
		int pixelCount = 0;
		
		for (int pixel : pixelArray) {	
			// only non-masked pixels will be considered in calculation
			if (pixel != IGNORE_PIXEL) {
				ColorContainer pixelColor = ColorContainer.fromValue(pixel);	
				redSum += pixelColor.getRed();
				greenSum += pixelColor.getGreen();
				blueSum += pixelColor.getBlue();		
				pixelCount++;
			}
		}
		
		if (pixelCount == 0)   // black if there are no valid pixels
			return 0xff000000;
		
		int avgRed = redSum / pixelCount;
		int avgGreen = greenSum / pixelCount;
		int avgBlue = blueSum / pixelCount;
		
		return ColorContainer.fromRGB(avgRed, avgGreen, avgBlue).getColorValue();
	}
	
}
