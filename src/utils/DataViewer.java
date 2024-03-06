package utils;

import camps.CampList;
import models.User;

/**
 * Interface for viewing data.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public interface DataViewer {
	/**
	 * Abstract method to be overriden by different users to view their respective data.
	 * @param user The user viewing the data.
	 * @param campList The list of camps that is accessible to the user.
	 */
	public void viewData(User user, CampList campList);
}
