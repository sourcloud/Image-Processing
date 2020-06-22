package de.studium.image_processing;

/**
 * Multithreshold performs a threshold with multiple layers.
 * If one threshold is given, there will be two greyscale colors, if two
 * thresholds are given, there will be three different greyscale colors etc.
 */
public class MultiThresholdFilter extends PixelFilter {
	
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 255;
	
	int[] thresholds;
	
	/**
	 * Creates MultiThresholdFilter with array of thresholds
	 * @param thresholds (int[]) Values that are borders of greyscale layers
	 */
	public MultiThresholdFilter (int ...thresholds) {
		// TODO: sanity checks
		this.thresholds = thresholds;
	}

	
	/**
	 * Calculates greyscale value of a pixel based on which range its greyscale
	 * value is in. All pixels in a range between two threshold will have the
	 * same greyscale color.
	 */
	@Override
	public int calculate(int pixelColor) {
		
		int averageValue = ColorContainer.fromValue(pixelColor).getAverage();
		
		for (int i = 0; i < thresholds.length; i++) {
			
			int threshold = thresholds[i];
			int greyscale;
			
			if (i == 0)
				greyscale = MIN_VALUE;
			else
				// middle between current and last one
				greyscale = (threshold + thresholds[i-1]) / 2;
			
			if (averageValue < threshold) {
				return ColorContainer
						.fromRGB(greyscale, greyscale, greyscale)
						.getColorValue();
			}
		}
		
		return ColorContainer
				.fromRGB(MAX_VALUE, MAX_VALUE, MAX_VALUE)
				.getColorValue();
		
	}

}
