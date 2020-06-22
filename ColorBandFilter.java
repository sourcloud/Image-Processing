package de.studium.image_processing;

/**
 * A ColorbandFilter extracts a single colorband (R/G/B) by setting all other 
 * RGB values to 0.
 */
public class ColorBandFilter extends PixelFilter {
	
	private ColorBand color;
	
	/**
	 * Creates filter for specific colorband.
	 * 
	 * @param color (ColorBand) colorband to extract
	 */
	public ColorBandFilter(ColorBand color) {
		this.color = color;
	}
	
	/**
	 * Sets RGB values that do not match colorband to 0, does not touch the
	 * other one.
	 */
	@Override
	public int calculate(int pixelColor) {
		
		ColorContainer inputColor = ColorContainer.fromValue(pixelColor);
		
		int red = (color == ColorBand.RED) 
				? inputColor.getRed()
				: 0;
				
		int green = (color == ColorBand.GREEN) 
				? inputColor.getGreen()
				: 0;
				
		int blue = (color == ColorBand.BLUE) 
				? inputColor.getBlue()
				: 0;
		
		return ColorContainer.fromRGB(red, green, blue).getColorValue();
	
	}

}

