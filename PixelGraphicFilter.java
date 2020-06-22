package de.studium.image_processing;

/**
 * PixelGraphicFilter sets all pixels inside a kernel to the average color
 * of that kernel
 */
public class PixelGraphicFilter extends AreaFilter {

	/**
	 * Calls superclass constructor
	 */
	public PixelGraphicFilter(int size) {
		super(size);
	}
	
	/**
	 * Calculates value of a pixel by creating a kernel around it. Performs 
	 * new calculation if pixel is start of a new pixelblock, else 
	 * performs the same calculation as if it was the start of the block its in.
	 * New value is average of values in containing block.
	 */
	@Override
	protected int calculate(int[] pixel, int[] maskPixel, int index, int width, int height) {
		
		int[] kernel = new int[size * size];
		
		// Current position represented in 2D
		int posRow = index / width;
		int posCol = index % width;
		
		boolean startOfBlock = (posCol % size == 0 && posRow % size == 0);
		
		if (!startOfBlock) {
			
			// Normalize by blocksize to get start of pixelblock
			int pixelBlockStartRow = posRow / size * size;
			int pixelBlockStartCol = posCol / size * size;
			
			// Position where pixelblock starts in 1D
			int pixelBlockStartIndex = pixelBlockStartRow * width + pixelBlockStartCol;
			
			index = pixelBlockStartIndex;
		
		}
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				
				// visualized in 2D:
				// 		top left of kernel starts at current image index
				
				// map kernel pos to image index
				int kernelIndex = row * size + col;
				int imageIndex = row * width + col;
					
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
