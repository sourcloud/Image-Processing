package de.studium.image_processing;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class stores and handles predefined filters.
 *
 */
public class FilterStorage {
	
	private static FilterStorage instance = new FilterStorage();
	
	private Map<String, Filter> filters;
	
	
	/**
	 * Only one instance exists. Creates and fills map with name and filter pairs.
	 */
	private FilterStorage() {
		
		filters = new TreeMap<>();
		
		// PixelFilter
		
		filters.put("monochrome", new MonochromeFilter());
		filters.put("colorband_red", new ColorBandFilter(ColorBand.RED));
		filters.put("colorband_green", new ColorBandFilter(ColorBand.GREEN));
		filters.put("colorband_blue", new ColorBandFilter(ColorBand.BLUE));
		filters.put("threshold_128", new ThresholdFilter(128)); 			
		filters.put("threshold_192", new ThresholdFilter(192)); 
		filters.put("multithreshold", new MultiThresholdFilter(64, 128, 192));
		filters.put("colorreplacement_96", new ColorReplacementFilter(96));	
		filters.put("colorreplacement_160", new ColorReplacementFilter(160));	
		filters.put("colorreplacement_255", new ColorReplacementFilter(255));	
		
		// AreaFilter
		
 		filters.put("blur_3", new BlurFilter(3));				
		filters.put("blur_5", new BlurFilter(5));
		filters.put("pixel_20", new PixelGraphicFilter(20)); 
		filters.put("pixel_40", new PixelGraphicFilter(40));
		filters.put("pixel_60", new PixelGraphicFilter(60));
		
		// ChainFilter
		
		ChainFilter warhol = new ChainFilter();
		warhol.add(filters.get("multithreshold"));
		warhol.add(new ColorReplacementFilter(0));
		warhol.add(filters.get("colorreplacement_96"));
		warhol.add(filters.get("colorreplacement_160"));
		warhol.add(filters.get("colorreplacement_255"));
		filters.put("warhol", warhol);
	}
	
	/**
	 * Returns only instance of FilterStorage
	 * 
	 * @return (FilterStorage) Instance of FilterStorage
	 */
	public static FilterStorage getInstance() {
		return instance;
	}
	
	/**
	 * Returns filter for given key, null if key unknown.
	 * 
	 * @param key (String) Name of the filter in map.
	 * @return (Filter) Filter in map, null if unknown.
	 */
	public Filter get(String key) {
		return filters.get(key);
	}
	
	/**
	 * @return (ArrayList) List of all filters in map.
	 */
	public ArrayList<Filter> getValues() {
		return new ArrayList<Filter>(filters.values());
	}
	
	/**
	 * @return (ArrayList) List of all keys in map.
	 */
	public ArrayList<String> getKeys() {
		return new ArrayList<String>(filters.keySet());
	}

}
