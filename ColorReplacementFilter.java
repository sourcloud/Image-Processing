package de.studium.image_processing;

import java.awt.Color;

/**
 * ColorRelacementFilter replaces one color with another.
 */
public class ColorReplacementFilter extends PixelFilter {

	private ColorContainer toReplace;
	private ColorContainer replacement;
	  
	/**
	 * Creates ColorReplacementFilter with a greyscale value and random replacement.
	 * @param greyscale (int) greyscale value to replace.
	 */
	public ColorReplacementFilter(int greyscale) {
		this(greyscale, greyscale, greyscale);
	}
	
	/**
	 * Creates ColorReplacementFilter with a greyscale value and replacement color.
	 * @param greyscale (int) greyscale value to replace.
	 * @param replacement (int) int representation of color that will replace greyscale
	 */
	public ColorReplacementFilter(int greyscale, int replacement) {
		this(greyscale, greyscale, greyscale, replacement);
	}
	
	/**
	 * Creates ColorReplacementFilter with a greyscale value and replacement color.
	 * @param greyscale (int) greyscale value to replace.
	 * @param replacement (Color) color that will replace greyscale.
	 */
	public ColorReplacementFilter(int greyscale, Color replacement) {
		this(greyscale, greyscale, greyscale, replacement);
	}
	
	/**
	 * Creates ColorReplacementFilter with a RGB color that will be replaced
	 * by a random replacement color.
	 * @param red (int) red value of RGB color
	 * @param green (int) green value of RGB color
	 * @param blue (int) blue value of RGB color
	 */
	public ColorReplacementFilter(int red, int green, int blue) {
		this.toReplace = ColorContainer.fromRGB(red, green, blue);
		this.replacement = ColorContainer.fromRandom();
	}
	
	/**
	 * Creates ColorReplacementFilter with a RGB color and replacement.
	 * @param red (int) red value of RGB color
	 * @param green (int) green value of RGB color
	 * @param blue (int) blue value of RGB color
	 * @param replacement (int) representation of color that will replace RGB color
	 */
	public ColorReplacementFilter(int red, int green, int blue, int replacement) {
		this.toReplace = ColorContainer.fromRGB(red, green, blue);
		this.replacement = ColorContainer.fromValue(replacement);
	}

	/**
	 * Creates ColorReplacementFilter with RGB color and random replacement.
	 * @param red (int) red value of RGB color
	 * @param green (int) green value of RGB color
	 * @param blue (int) blue value of RGB color
	 * @param replacement (Color) color that will replace RGB color 
	 */
	public ColorReplacementFilter(int red, int green, int blue, Color replacement) {
		this.toReplace = ColorContainer.fromRGB(red, green, blue);
		this.replacement = ColorContainer.fromColor(replacement);
	}
	
	/**
	 * Checks if given color matches color to replace, if so, returns replacement
	 * else returns given color
	 * 
	 * @return (int) representation of a color
	 */
	@Override
	public int calculate(int pixelColor) {
		return (toReplace.getColorValue() == pixelColor) 
				? replacement.getColorValue()
				: pixelColor;
	}

}
