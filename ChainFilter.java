package de.studium.image_processing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * ChainFilter allows multiple filters to be applied to the same image one
 * after another.
 */
public class ChainFilter implements Filter {

	List<Filter> toApply;
	
	/**
	 * Creates ChainFilter with empty list of filters to apply.
	 */
	public ChainFilter() {
		toApply = new ArrayList<>();
	}
	
	/**
	 * Calls process method for each Filter to apply. First filter manipulates
	 * original image, following filters manipulate output of the last applied.
	 */
	@Override
	public BufferedImage process(BufferedImage... input) {
		BufferedImage image, mask, output;
		
		image = (input.length > 0) ? input[0] : null;
		mask = (input.length > 1) ? input[1] : null;
		output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < toApply.size(); i++) {
			output = (i == 0) 
					? toApply.get(i).process(image, mask) 
					: toApply.get(i).process(output, mask);
		}
		return output;
	}
	
	/**
	 * Adds filter to apply to the ChainFilter
	 * @param filter filter that will be applied.
	 */
	public void add(Filter filter) {
		toApply.add(filter);
	}
	

}
