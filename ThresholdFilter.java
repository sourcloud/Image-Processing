package de.studium.image_processing;

/**
 * A threshold filter sets all greyscale values above its threshold to 255,
 * all below to 0.
 *
 */
public class ThresholdFilter extends PixelFilter {
	
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 255;
	
	int threshold;
	
	/**
	 * Creates ThresholdFilter with given threshold
	 * @param threshold (int) Value that separetes black from white pixels.
	 */
	public ThresholdFilter(int threshold) {
		this.threshold = (threshold >= MIN_VALUE || threshold <= MAX_VALUE)
				? threshold
				: (threshold > MAX_VALUE)
					? MAX_VALUE
					: MIN_VALUE;
	}

	/**
	 * Returns black color value, if pixel greyscale value is below threshold,
	 * white color value else.
	 */
	@Override
	public int calculate(int pixelColor) {
		int averageValue = ColorContainer.fromValue(pixelColor).getAverage();	
	
		if (averageValue < threshold)
			return ColorContainer
					.fromRGB(MIN_VALUE, MIN_VALUE, MIN_VALUE)
					.getColorValue();
		
		return ColorContainer
				.fromRGB(MAX_VALUE, MAX_VALUE, MAX_VALUE)
				.getColorValue();
	}

}
