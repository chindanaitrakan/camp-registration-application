package camps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import models.Staff;
import models.Student;
import models.User;

/**
 * Class for a camp.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */

public class Camp extends CampInfo{
	/**
	 * Array of studentIds of students who register the camp.
	 */
	private ArrayList<String> studentIdList;
	
	/**
	 * Array of committeeId of camp committee who responsible for the camp.
	 */
	private ArrayList<String> committeeIdList;
	
	/**
	 * Array of withdrawId of students who withdraw the camp.
	 */
	private ArrayList<String> withdrawIdList;
	
	/**
	 * Visibility of the camp.
	 */
	private boolean visibility;
	
	/**
	 * Constructor of Camp
	 * @param name 
	 * @param dates
	 * @param registrationClosingDate
	 * @param userGroup
	 * @param location
	 * @param totalSlots
	 * @param campCommitteeSlots
	 * @param description
	 * @param staffInCharge
	 */
	public Camp(String name, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffId
			, Boolean visibility, ArrayList<String> studentIdList, ArrayList<String> committeeIdList, ArrayList<String> withdrawIdList) {
		super(name, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description, staffId);
		this.visibility = visibility;
		this.studentIdList = studentIdList;
		this.committeeIdList = committeeIdList;
		this.withdrawIdList = withdrawIdList;
	}
	
	/**
	 * Gets student list in the camp.
	 * @return this Camp's studentIDList.
	 */
	public ArrayList<String> getStudentIdList(){
		return this.studentIdList;
	}
	
	/**
	 * Gets committee list in the camp.
	 * @return this Camp's committeeIDList.
	 */
	public ArrayList<String> getCommitteeIdList(){
		return this.committeeIdList;
	}
	
	/**
	 * Gets withdraw list in the camp.
	 * @return this Camp's withdrawIDList.
	 */
	public ArrayList<String> getWithdrawIdList(){
		return this.withdrawIdList;
	}
	
	/**
	 * Get camp visibility.
	 * @return this Camp's visibility.
	 */
	public Boolean getVisibility() {
		return this.visibility;
	}
	
	/**
	 * Set camp visibility.
	 */
	public void setVisibility(Boolean newVisibility) {
		this.visibility = newVisibility;
	}
	
	/**
	 * Sets student list in the camp.
	 */
	public void setStudentIdList(ArrayList<String> studentIdList){
		this.studentIdList = studentIdList;
	}
	
	/**
	 * Sets Committee list in the camp.
	 */
	public void setCommitteeIdList(ArrayList<String> committeeIdList){
		this.committeeIdList = committeeIdList;
	}
	
	/**
	 * Sets Withdraw List in the camp.
	 */
	public void setWithdrawIdList(ArrayList<String> withdrawIdList){
		this.withdrawIdList = withdrawIdList;
	}
	
	/**
	 * Comparator which determine which parameter of the camp used for comparasion.
	 */
    public static Comparator<Camp> CampNameComparator = new Comparator<Camp>() {
 
        // Comparing attributes of Camps
        public int compare(Camp camp1, Camp camp2) {
            String campName1
                = camp1.getName().toUpperCase();
            String campName2
                = camp2.getName().toUpperCase();
 
            // Returning in ascending order
            return campName1.compareTo(
                       campName2);
 
            // descending order
            // return
            // CampName2.compareTo(campName1);
        }
    };


}
