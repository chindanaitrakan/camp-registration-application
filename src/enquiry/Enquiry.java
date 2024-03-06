package enquiry;

import java.util.ArrayList;
import java.util.Date;

import models.Staff;
import models.Student;
import models.User;

/**
 * Class to store the enquiry details.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */

public class Enquiry {
	
	/**
	 * ID of enquiry
	 */
	private String enquiryID;
	
	/**
	 * ID of user who submit the enquiry
	 */
	private String userID;
	
	/**
	 * Name of the camp which have the enquiry.
	 */
	private String name;
	
	/**
	 * Content of the enquiry.
	 */
	private String enquiry;
	
	/**
	 * Date of enquiry.
	 */
	private Date enquiryDate;
	
	/**
	 * Whether enquiry has been processed.
	 */
	private boolean processedStatus;
	
	/**
	 * enquiry or reply (0 - enquiry, 1 - reply)
	 */
	private int enquiryOrReply;
	
	/**
	 * Reply to which enquiry ID.
	 */
	private String replyTo;

	/**
	 * Constructor for enquiry.
	 * @param enquiryID This Enquiry's enquiryID.
	 * @param userID This Enquiry's userID.
	 * @param name This Enquiry's name.
	 * @param enquiry This Enquiry's enquiry.
	 * @param enquiryDate This Enquiry's enquiryDate.
	 * @param processedStatus This Enquiry's processedStatus.
	 * @param enquiryOrReply This Enquiry's enquiryOrReply.
	 * @param replyTo This Enquiry's replyTo.
	 */
	public Enquiry(String enquiryID, String userID, String name, String enquiry, Date enquiryDate, boolean processedStatus, int enquiryOrReply, String replyTo) {
		this.enquiryID = enquiryID;
		this.userID = userID;
		this.name = name;
		this.enquiry = enquiry;
		this.enquiryDate = enquiryDate;
		this.processedStatus = processedStatus;
		this.enquiryOrReply = enquiryOrReply;
		this.replyTo = replyTo;
	}
	
	
	/**
	 * Gets enquiryID of the enquiry.
	 * @return this Enquiry's enquiryID.
	 */
	public String getEnquiryID() {
		return this.enquiryID;
	}
	
	/**
	 * Gets userID of the enquiry.
	 * @return this Enquiry's userID.
	 */
	public String getUserID() {
		return this.userID;
	}
	
	/**
	 * Gets camp's name of the enquiry.
	 * @return this Equiry's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the content in enquiry.
	 * @return this Enquiry's enquiry.
	 */
	public String getEnquiry() {
		return this.enquiry;
	}
	
	/**
	 * Gets enquiry date.
	 * @return this Enquiry's Date.
	 */
	public Date getEnquiryDate() {
		return this.enquiryDate;
	}
	
	/**
	 * Gets processed status.
	 * @return this Enquiry's processedStatus.
	 */
	public boolean getProcessedStatus() {
		return this.processedStatus;
	}
	
	/**
	 * Gets enquiryOrReply.
	 * @return this Enquiry's enquiryOrReply.
	 */
	public int getEnquiryOrReply() {
		return this.enquiryOrReply;
	}
	
	/**
	 * Gets replyTo.
	 * @return this Enquiry's replyTo.
	 */
	public String getReplyTo() {
		return this.replyTo;
	}

	/**
	 * Sets enquiryID of the enquiry.
	 * @param enquiryID This Enquiry's enquiryID.
	 */
	public void setEnquiryID(String enquiryID) {
		this.enquiryID = enquiryID;
	}
	
	/**
	 * Sets userID of the enquiry.
	 * @param userID This Enquiry's userID. 
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * Sets camp's name of the enquiry.
	 * @param name This Enquiry's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the content in enquiry.
	 * @param enquiry This Enquiry's enquiry.
	 */
	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}
	/**
	 * Sets enquiry date.
	 * @param enquiryDate This Enquiry's enquiryDate.
	 */
	public void setEnquiryDate(Date enquiryDate) {
		this.enquiryDate = enquiryDate;
	}
	/**
	 * Sets processed status.
	 * @param processedStatus This Enquiry's processedStatus.
	 */
	public void setProcessedStatus(boolean processedStatus) {
		this.processedStatus = processedStatus;
	}
	/**
	 * Sets enquiryOrReply.
	 * @param enquiryOrReply This Enquiry's enquiryOrReply.
	 */
	public void setEnquiryOrReply(int enquiryOrReply) {
		this.enquiryOrReply = enquiryOrReply;
	}
	/**
	 * Sets replyTo.
	 * @param replyTo This Enquiry's replyTo.
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

}
