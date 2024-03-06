package datamanagement;

import enquiry.EnquiryList;

/**
 * Interface for classes to implement saving and loading of enquires between excel sheet and ArrayList.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public interface EnquiryDataManager {
	
    /**
     * Appends the data extracted from the excel sheet into the ArrayList.
     * @param enquiryList The excel data will be extracted into this enquiryList.
     */
	public void load(EnquiryList enquiryList);
	
    /**
     * Saves and update the excel sheet with the modified enquiryList.
     * @param enquiryList The campList that provides the information to be saved into the excel sheet.
     */
	public void save(EnquiryList enquiryList);
}
