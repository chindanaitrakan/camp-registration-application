package filters;

import camps.CampList;
import models.User;

/**
 * Interface for classes to implement filtering of camps.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public interface CampFilter {
	
    /**
     * Filters the camps according to how the user wants.
     * @param user The user that is filtering the camp.
     * @param campList The list of camps that can be accessed by the user.
     * @return campList The filtered camp list.
     */
	public CampList filter(User user, CampList campList);
}
