package datamanagement;

import camps.CampList;

/**
 * Interface for classes to implement saving and loading of camps between excel sheet and ArrayList.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public interface CampDataManager {
	
    /**
     * Appends the data extracted from the excel sheet into the ArrayList.
     * @param campList The excel data will be extracted into this campList.
     */
	public void load(CampList campList);
	
    /**
     * Saves and update the excel sheet with the modified campList.
     * @param campList The campList that provides the information to be saved into the excel sheet.
     */
	public void save(CampList campList);
}
