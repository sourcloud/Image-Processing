package de.studium.image_processing;

import java.awt.Color;

/**
 * This class handles RGB, int and java.awt.Color ways of representing
 * a color. Can be instantiated by either one of them and calculates
 * the other ones.
 * 
 * Basically own implementation of java.awt.Color storing and converting
 * capabilities to practice bitshifting. 
 */
public class ColorContainer {

	private final int red;
	private final int green;
	private final int blue;
	private final int colorValue;
	private final Color color;
	
	/**
	 * Gets RGB values, calculates corresponding int, creates corresponding Color
	 * 
	 * @param red (int) 0-255 red part of color
	 * @param green (int) 0-255 green part of color
	 * @param blue (int) 0-255 blue part of color
	 */
	private ColorContainer(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.colorValue = combine(red, green, blue);
		this.color = new Color(red, green, blue);
	}
	
	/**
	 * Gets int representation, extracts RGB, creates corresponding Color
	 * 
	 * @param colorValue (int) 0xFF000000 - 0xFFFFFFFF representation of color
	 */
	private ColorContainer(int colorValue) {
		this.colorValue = colorValue;
		this.red = extract(colorValue, ColorBand.RED);
		this.green = extract(colorValue, ColorBand.GREEN);
		this.blue = extract(colorValue, ColorBand.BLUE);
		this.color = new Color(red, green, blue);
	}
	
	/**
	 * Gets Color, sets RGB and int representation by Color getters
	 * 
	 * @param color (Color) 
	 */
	private ColorContainer(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.colorValue = color.getRGB();
		this.color = color;
	}
	
	/**
	 * @return ColorContainer created by RGB values
	 */
	public static ColorContainer fromRGB(int red, int green, int blue) {
		return new ColorContainer(red, green, blue);
	}
	
	/**
	 * @return ColorContainer created by int representation of color
	 */
	public static ColorContainer fromValue(int colorValue) {
		return new ColorContainer(colorValue);
	}
	
	/**
	 * @return ColorContainer created by java.awt.Color object
	 */
	public static ColorContainer fromColor(Color color) {
		return new ColorContainer(color);
	}
	
	/**
	 * @return ColorContainer created by random RGB values
	 */
	public static ColorContainer fromRandom() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return new ColorContainer(r, g, b);
	}
	
	/**
	 * Getter for red part
	 * @return (int) red part of color
	 */
	public int getRed() {
		return red;
	}
	
	/**
	 * Getter for green part
	 * @return (int) green part of color
	 */
	public int getGreen() {
		return green;
	}
	
	/**
	 * Getter for blue part
	 * @return (int) blue part of color
	 */
	public int getBlue() {
		return blue;
	}
	
	/**
	 * Getter for int representation
	 * @return (int) representation of color
	 */
	public int getColorValue() {
		return colorValue;
	}
	
	/**
	 * Getter for java.awt.Color object
	 * @return (Color) Color object
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Calculates and returns average of RGB values. This is the
	 * greyscale value.
	 * 
	 * @return (int) Arithmetic mean of RGB values.
	 */ 
	public int getAverage() {
		return (red + green + blue) / 3;
	}
	
	/**
	 * Calculate greyscale color int representation. Greyscale color
	 * value is value of an pixel, where RGB are all set to their mean.
	 * 
	 * @return (int) Color representation of greyscale value
	 */
	public int getGreyscale() {
		int average = getAverage();
		return combine(average, average, average);
	}
	
	/**
	 * Extracts colorband from int representation by bit operations.
	 * 
	 * Blue value is stored in bits 0-7.
	 * Green value is stored in bits 8-15.
	 * Red value is stored in bits 16-23.
	 * 
	 * Shifts by 0 / 8 / 16 and perfoms AND with 0xff=255 to extract 
	 * a colorband.
	 * 
	 * @param pixelColor (int) representation of color to extract from
	 * @param colorband (ColorBand) colorband to extract
	 * @return (int) 0-255 value of specific colorband in color
	 */
	private int extract(int pixelColor, ColorBand colorband) {	
		int shift;
		switch(colorband) {
			case RED:
				shift = 16;
				break;
			case GREEN:
				shift = 8;
				break;
			case BLUE:
				shift = 0;
				break;
			default: 
				shift = 0;
				break;
		}
		return (pixelColor >> shift) & 0xff;
	}
	
	
	/**
	 * Gets color representation of RGB values by using bit operations.
	 * 
	 * Shifts red / green / blue by 16 / 8 / 0 to left.
	 * Performs OR operations with 0xff000000 to get the int representation.
	 * 
	 * @param redValue (int) 0-255 red part of color
	 * @param greenValue (int) 0-255 green part of color
	 * @param blueValue (int) 0-255 blue part of color
	 * @return (int) color representation
	 */
	private int combine(int redValue, int greenValue, int blueValue) {
		int pixelColor = 0xff000000;
		pixelColor |= redValue << 16;
		pixelColor |= greenValue << 8;
		pixelColor |= blueValue;
		return pixelColor;
	}
	
}
