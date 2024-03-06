package camputils;

import camps.CampList;
import models.User;

/**
 * Interface for register and withdraw from the camp.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public interface EnrollmentManager{
	
	/**
	 * Abstract method to be overidden to process the enrollment.
	 * @param user The user involved in enrollment process.
	 * @param campList The campList which is accessible for the user.
	 */
	public void process(User user, CampList campList);

}
