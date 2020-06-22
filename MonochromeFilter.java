package de.studium.image_processing;

/**
 * Filter that creates monochrome image out of given image.
 */
public class MonochromeFilter extends PixelFilter {

	/**
	 * Calculates greyscale value by getting the average value and setting it as
	 * value for red, green and blue.
	 * 
	 * @param pixelColor (int) Color value of the pixel.
	 */
	@Override
	public int calculate(int pixelColor) {
		return ColorContainer.fromValue(pixelColor).getGreyscale();
	}

}
