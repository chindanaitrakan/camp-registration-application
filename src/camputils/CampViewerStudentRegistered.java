package camputils;

import java.text.SimpleDateFormat;

import camps.CampList;
import filters.CampFilter;
import filters.CampStudentIdFilter;
import filters.CampVisibilityFilter;
import filters.FilterUI;
import models.Student;
import models.User;
import utils.DataViewer;

/**
* View the registered camp data which is accessible to a student.
* @author Trakantannarong Chindanai
* @version 1.0
* @since 2023-11-24
*/
public class CampViewerStudentRegistered implements DataViewer{
	
	/**
	 * CampFilter used to filter the camp.
	 */
	private CampFilter campFilter;

	@Override
	/**
	 * View registered camp data for student.
	 * @param user The user who views the camp.
	 * @param campList The campList which is accessible for the user.
	 */
	public void viewData(User user, CampList campList) {
		
		Student student = (Student) user;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		CampList openCampList, filteredCampList;
		//filter the visibility
		campFilter =  new CampVisibilityFilter();
		openCampList = campFilter.filter(student, campList);
		//filter by studentId
		campFilter = new CampStudentIdFilter();
		openCampList = campFilter.filter(student, openCampList);
		//filter by custom
		filteredCampList = FilterUI.chooseFilter(student, openCampList);

		//Filter for user 
		int count=1, remainingSlots=0, remainingCommSlots=0;
		System.out.println("\n");
		System.out.println("════════════════════════════════════════════════════════════════");
		System.out.println("                     List of Camps: ");
		for(int i=0; i<filteredCampList.size(); i++) {
			if(filteredCampList.get(i).getUserGroup().equals(student.getFaculty()) || filteredCampList.get(i).getUserGroup().equals("ALL")) {
				//show the camp and remaining slots
				remainingSlots = filteredCampList.get(i).getTotalSlots()-filteredCampList.get(i).getStudentIdList().size();
				remainingCommSlots = filteredCampList.get(i).getCampCommitteeSlots() - filteredCampList.get(i).getCommitteeIdList().size();
				System.out.println("════════════════════════════════════════════════════════════════");
				System.out.println("[" + count + "] "+filteredCampList.get(i).getName() 
						+ "\nDate: " + dateFormat.format(filteredCampList.get(i).getStartDate()) + " to " + dateFormat.format(filteredCampList.get(i).getEndDate()) 
						+ "\nRegistration Deadline: " + dateFormat.format(filteredCampList.get(i).getRegistrationClosingDate()) 
						+ "\nTotal Slots Remaining (Attendee) | (Committee Slots): " + remainingSlots + " | " + remainingCommSlots 
						+ "\nLocation: " + filteredCampList.get(i).getLocation() 
						+ "\nDescription: " + filteredCampList.get(i).getDescription()
						+ "\nStaffInCharge: " + filteredCampList.get(i).getStaffId());
				count++;
			}
		}
		System.out.println("════════════════════════════════════════════════════════════════");
		System.out.println("\n");
	}
		
}
