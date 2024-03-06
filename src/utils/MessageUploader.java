package utils;

import camps.CampList;
import models.User;

/**
 * Interface for uploading messages.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public interface MessageUploader {
	/**
	 * Abstract method to be overriden by different users with their respective methods to submit a message.
	 * @param user submitting message
	 * @param campList list of camps
	 */
	public void submit(User user, CampList campList);
}
