package camputils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import datamanagement.CampDataManager;
import datamanagement.CampExcelData;
import models.Student;
import models.User;

/**
* Withdraw student from the camp.
* @author Trakantannarong Chindanai
* @version 1.0
* @since 2023-11-24
*/
public class CampWithdrawManager implements EnrollmentManager{
	
	/**
	 * Camp data manager for saving the data.
	 */
	private CampDataManager campData;

	@Override
	/**
	 * Withdraw the student from the camp.
	 * @param user The user who withdraws the camp.
	 * @param campList The campList which is accessible for the user.
	 */
	public void process(User user, CampList campList) {
		
		Student student = (Student) user;
		System.out.println("Enter the name of the camp to withdraw from ");
		Scanner sc = new Scanner(System.in);
		String campName = sc.nextLine().trim();
		
		//get campIndex in campList
		int campIndex = CampSearcher.searchCamp(campName, campList);
		//selected camp object
		Camp selectedCamp;
		
		//check if camp is found
		if(campIndex==-1) {
			System.out.println("Camp not found");
		}
		else {
			//assign selected camp to the searchedcamp
			selectedCamp = campList.get(campIndex);
			if (selectedCamp.getCommitteeIdList().contains(student.getUserID())) {
				System.out.println("You are not allowed to withdraw from this camp as you are part of the camp committee.");
			} else if (!selectedCamp.getStudentIdList().contains(student.getUserID())) {
				System.out.println("You are not registered for this camp");
			} else {
				//Edit the studentidList
				ArrayList<String> newStudentIdList = selectedCamp.getStudentIdList();
				//remove the student Id
				newStudentIdList.remove(student.getUserID());
				
				//Edit the withdrawidList
				ArrayList<String> newWithdrawIdList = selectedCamp.getWithdrawIdList();
				//add new student id
				newWithdrawIdList.add(student.getUserID());
				Collections.sort(newWithdrawIdList);
			
				//save to data base
		        campData = new CampExcelData();
				campData.save(campList);
		        System.out.println("Successfully withdraw from the camp.");
			}
		}	
	}	
}
