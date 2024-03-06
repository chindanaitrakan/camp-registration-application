package datamanagement;

import suggestion.SuggestionList;

/**
 * Interface for classes to implement saving and loading of suggestions between excel sheet and ArrayList.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public interface SuggestionDataManager {
	
    /**
     * Appends the data extracted from the excel sheet into the ArrayList.
     * @param suggestionList The excel data will be extracted into this suggestionList.
     */
	public void load(SuggestionList suggestionList);
	
    /**
     * Saves and update the excel sheet with the modified suggestionList.
     * @param suggestionList The suggestionList that provides the information to be saved into the excel sheet.
     */
	public void save(SuggestionList suggestionList);	
}
