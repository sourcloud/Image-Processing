package de.studium.image_processing;

public class BlurFilter extends AreaFilter {

	
	/**
	 * Calls superclass constructor
	 * 
	 * @see #AreaFilter.AreaFilter(int size)
	 */
	public BlurFilter(int size) {
		super(size);
	}

	/**
	 * Calculates average pixelcolor of pixels around index by creating a kernel
	 * and filling it with unweighted surroundings. Filling happens by calculating
	 * corresponding image index to kernel index. Then calculates average value
	 * of filled kernel values.
	 */
	@Override
	protected int calculate(int[] pixel, int[] maskPixel, int index, int width, int height) {
		
		int[] kernel = new int[size * size];
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				
				// center kernel around pixel at index by shifting kernel grid
				int imageRow = row - size / 2;
				int imageCol = col - size / 2; 
				
				// map kernel pos to image index
				int kernelIndex = row * size + col;
				int imageIndex = imageRow * width + imageCol;
			
				// check for overlap and mask before assigning value
				kernel[kernelIndex] = IGNORE_PIXEL;
				if (index + imageIndex < pixel.length && index + imageIndex > 0)
					if (maskPixel == null || maskPixel[index + imageIndex] > 0xff000000)
						kernel[kernelIndex] = pixel[index + imageIndex];
			}
		}
		
		return getAveragePixelColor(kernel);
	}
	
}

