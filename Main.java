package de.studium.image_processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Class containing the main mathod
 */
public class Main {
	
	private static final String MASK_TAG = "-m";

	public static void main(String[] args) throws Exception {
		
		String filtername;
		String pathToImage;
		String tag;
		String pathToMask;
		String outputPath;
		
		BufferedImage image = null;
		BufferedImage mask = null;
		
		FilterStorage filters = FilterStorage.getInstance();
		
		if (args.length == 3) {
			
			filtername = args[0];
			pathToImage = args[1];
			outputPath = args[2];
			
			try {
				image = ImageIO.read(new File(pathToImage));
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
		else if (args.length == 5) {
			filtername = args[0];
			pathToImage = args[1];
			tag = args[2];
			
			if (!tag.equals(MASK_TAG)) {
				throw new Error("Invalid tag permitted!");
			}
			pathToMask = args[3];
			outputPath = args[4];
			
			try {
				image = ImageIO.read(new File(pathToImage));
				mask = ImageIO.read(new File(pathToMask));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			throw new Exception("Invalid number of arguments provided. "
					+ "Must be either 3 or 5!");
		}
	
		if (filtername.equals("test")) {

			ArrayList<String> nameList = filters.getKeys();
			ArrayList<Filter> filterList = filters.getValues();
			
			for (int i = 0; i < filterList.size(); i++) {
				
				Filter filter = filterList.get(i);
				String name = nameList.get(i);
				
				System.out.printf("Performing %s ... ", name);
				
				BufferedImage output = filter.process(image, mask);
				try {
					ImageIO.write(output, "bmp", new File(outputPath + name + ".bmp"));
					System.out.println("Done!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			
			Filter toApply = filters.get(filtername);
			
			if (toApply == null) {
				throw new Exception("Filter unknown!");		
			}
			System.out.printf("Perfroming %s ... ", filtername);
			BufferedImage output = toApply.process(image, mask);
			System.out.println("Done!");
			try {
				ImageIO.write(output, "bmp", new File(outputPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
