package camps;

import java.util.Date;
import models.Staff;

/**
 * Class for a camp basic info.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */

public class CampInfo {
	/**
	 * Name of the camp.
	 */
	private String name;
	
	/**
	 * Start Date of the camp.
	 */
	private Date startDate;
	
	/**
	 * End Date of the camp.
	 */
	private Date endDate;
	
	/**
	 * Registration closing date of the camp.
	 */
	private Date registrationClosingDate;
	
	/**
	 * School where the camp is open for.
	 */
	private String userGroup;
	
	/**
	 * Location of the camp.
	 */
	private String location;
	
	/**
	 * Total slots which includes camp committee slots.
	 */
	private int totalSlots;
	
	/**
	 * Slots for camp committee (max10).
	 */
	private int campCommitteeSlots;
	
	/**
	 * Description of the camp.
	 */
	private String description;
	
	/**
	 * Staff in charge of the camp.
	 */
	private String staffId;
	
	/**
	 * Constructor of CampInfo.
	 * @param name This CampInfo's name.
	 * @param startDates This CampInfo's startDates.
	 * @param endDates This CampInfo's endDates.
	 * @param registrationClosingDate This CampInfo's registrationClosingDate.
	 * @param userGroup This CampInfo's userGroup.
	 * @param location This CampInfo's location.
	 * @param totalSlots This CampInfo's totalSlots.
	 * @param campCommitteeSlots This CampInfo's campCommitteeSlots.
	 * @param description This CampInfo's description.
	 * @param staffInCharge This CampInfo's staffInCharge.
	 */
	public CampInfo(String name, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffId) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.registrationClosingDate = registrationClosingDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		this.campCommitteeSlots = campCommitteeSlots;
		this.description = description;
		this.staffId = staffId;
	}
	
	/**
	 * Gets CampInfo's name.
	 * @return this CampInfo's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets CampInfo's start date.
	 * @return this CampInfo's start date.
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Gets CampInfo's end date.
	 * @return this CampInfo's end date.
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Gets CampInfo's registration closing date.
	 * @return this CampInfo's registration closing date.
	 */
	public Date getRegistrationClosingDate() {
		return this.registrationClosingDate;
	}
	
	/**
	 * Gets CampInfo's user group.
	 * @return this CampInfo's user group.
	 */
	public String getUserGroup() {
		return this.userGroup;
	}
	
	/**
	 * Gets CampInfo's location.
	 * @return this CampInfo's location.
	 */
	public String getLocation() {
		return this.location;
	}
	/**
	 * Gets CampInfo's total slots.
	 * @return this CampInfo's total slots.
	 */
	public int getTotalSlots() {
		return this.totalSlots;
	}
	/**
	 * Gets CampInfo's camp committee slot.
	 * @return this CampInfo's camp committee slot.
	 */
	public int getCampCommitteeSlots() {
		return this.campCommitteeSlots;
	}
	/**
	 * Gets CampInfo's description.
	 * @return this CampInfo's description.
	 */
	public String getDescription() {
		return this.description;
	}
	/** 
	 * Get CampInfo's staff in charge.
	 * @return this CampInfo's staff in charge.
	 */
	public String getStaffId() {
		return this.staffId;
	}
	
	/**
	 * Sets CampInfo's name.
	 * @return this CampInfo's name.
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Sets CampInfo's start date.
	 * @return this CampInfo's start date.
	 */
	public void setStartDate(Date newDate) {
		this.startDate = newDate;
	}
	
	/**
	 * Sets CampInfo's end date.
	 * @return this CampInfo's end date.
	 */
	public void setEndDate(Date newDate) {
		this.endDate = newDate;
	}
	
	/**
	 * Sets CampInfo's registration closing date.
	 * @return this CampInfo's registration closing date.
	 */
	public void setRegistrationClosingDate(Date newRegistrationClosingDate) {
		this.registrationClosingDate = newRegistrationClosingDate;
	}
	
	/**
	 * Sets CampInfo's user group.
	 * @return this CampInfo's user group.
	 */
	public void setUserGroup(String newUserGroup) {
		this.userGroup = newUserGroup;
	}
	
	/**
	 * Sets CampInfo's location.
	 * @return this CampInfo's location.
	 */
	public void setLocation(String newLocation) {
		this.location = newLocation;
	}
	
	/**
	 * Sets CampInfo's total slots.
	 * @return this CampInfo's total slots.
	 */
	public void setTotalSlots(int newTotalSlots) {
		this.totalSlots = newTotalSlots;
	}
	
	/**
	 * Sets CampInfo's camp committee slot.
	 * @return this CampInfo's camp committee slot.
	 */
	public void setCampCommitteeSlots(int newCampCommitteeSlots) {
		this.campCommitteeSlots = newCampCommitteeSlots;
	}
	
	/**
	 * Sets CampInfo's description.
	 * @return this CampInfo's description.
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
}
