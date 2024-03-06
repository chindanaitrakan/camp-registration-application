package suggestion;

import java.util.Date;

/**
 * Suggestions by students.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class Suggestion {
	/**
	 * ID of suggestion.
	 */
	private String suggestionID;
	/**
	 * ID of user that gave the suggestion.
	 */
	private String userID;
	/**
	 * Name of camp.
	 */
	private String name;
	/**
	 * Suggestion
	 */
	private String suggestion;
	/**
	 * Date of suggestion.
	 */
	private Date suggestionDate;
	/**
	 * Whether suggestion has been processed.
	 */
	private boolean processedStatus;
	/**
	 * Reply to suggestion.
	 */
	private String approvedOrDeclined;


	
	
	/**
	 * Constructor for Suggestion
	 * @param suggestionID ID of suggestion.
	 * @param userID ID of user accessing suggestion.
	 * @param name Name of user accessing suggestion.
	 * @param suggestion Suggestion for camps.
	 * @param suggestionDate Date the suggestion was posted.
	 * @param processedStatus Whether the suggestion is being processed.
	 * @param approvedOrDeclined Whether the suggestion is approved.
	 */
	public Suggestion(String suggestionID, String userID, String name, String suggestion, Date suggestionDate, boolean processedStatus, String approvedOrDeclined) {
		this.suggestionID = suggestionID;
		this.userID = userID;
		this.name = name;
		this.suggestion = suggestion;
		this.suggestionDate = suggestionDate;
		this.processedStatus = processedStatus;
		this.approvedOrDeclined = approvedOrDeclined;
	}
	
	
	/**
	 * Gets suggestionID of the suggestion.
	 * @return The suggestionID of the suggestion. 
	 */
	public String getSuggestionID() {
		return this.suggestionID;
	}
	/**
	 * Gets userID of user accessing suggestion..
	 * @return The userID of user accessing suggestion.
	 */
	public String getUserID() {
		return this.userID;
	}
	/**
	 * Gets camp's name.
	 * @return The camps name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gets suggestion for the camp.
	 * @return The suggestion for the camp.
	 */
	public String getSuggestion() {
		return this.suggestion;
	}
	/**
	 * Gets the date the suggestion was posted.
	 * @return The date the suggestion was posted.
	 */
	public Date getSuggestionDate() {
		return this.suggestionDate;
	}
	/**
	 * Gets processed status of the suggestion.
	 * @return The process status of the suggestion.
	 */
	public boolean getProcessedStatus() {
		return this.processedStatus;
	}
	/**
	 * Gets approval status of the suggestion.
	 * @return The approval status of the suggestion.
	 */
	public String getApprovedOrDeclined() {
		return this.approvedOrDeclined;
	}

	

	/**
	 * Sets suggestionID.
	 * @param suggestionID The ID of the suggestion.
	 */
	public void setSuggestionID(String suggestionID) {
		this.suggestionID = suggestionID;
	}
	/**
	 * Sets userID.
	 * @param userID The ID of user.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * Sets camp's name.
	 * @param name The camp's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Sets suggestion.
	 * @param suggestion The suggestion of student.
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	/**
	 * Sets suggestion date.
	 * @param suggestionDate The date of suggestion.
	 */
	public void setSuggestionDate(Date suggestionDate) {
		this.suggestionDate = suggestionDate;
	}
	/**
	 * Sets processed status.
	 * @param processedStatus Whether the suggestion is being processed.
	 */
	public void setProcessedStatus(boolean processedStatus) {
		this.processedStatus = processedStatus;
	}
	/**
	 * Sets approvedOrDeclined status.
	 * @param approvedOrDeclined Whether the suggestion is approved or declined.
	 */
	public void setApprovedOrDeclined(String approvedOrDeclined) {
		this.approvedOrDeclined = approvedOrDeclined;
	}

}
