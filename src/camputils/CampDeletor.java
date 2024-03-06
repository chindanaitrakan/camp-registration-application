package camputils;

import java.util.Scanner;

import camps.CampList;
import datamanagement.CampDataManager;
import datamanagement.CampExcelData;
import models.Staff;
import models.User;

/**
 * Delete selected camp and update to camp list.
 * Update the new camp list to the excel spread sheet.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class CampDeletor extends CampManager{
	
	/**
	 * Camp data manager for saving the data.
	 */
	private CampDataManager campData;
	
	/**
	 * Constructor of CampDeletor
	 * @param user The user who delete the camp
	 * @param campList The original campList
	 */
	public CampDeletor(User user, CampList campList) {
		super(user, campList);
	}
	
	@Override
	/**
	 * Delete the selected camp from camp list.
	 */
	public void changeCampInfo() {
		Scanner sc = new Scanner(System.in);
		// Input the edited camp
		System.out.print("Enter camp name:");
		String searchedName = sc.nextLine().trim();
		//search for the camp name
		int campIndex = CampSearcher.searchCamp(searchedName, campList);
		// If camp is not found
		if(campIndex==-1) {
			System.out.println("Camp is not found");
		}
		// If camp is found
		else { 
			if(campList.get(campIndex).getStudentIdList().size()!=0 || campList.get(campIndex).getCommitteeIdList().size()!=0) {
				System.out.println("This camp cannot be deleted as there are students that have already registered for it.");
			} else {
				campList.remove(campIndex);
				campData = new CampExcelData();
				campData.save(campList);
				System.out.println("Successfully delete the camp");
			}
		}
		campList.sort();
		campData = new CampExcelData();
		campData.save(campList);
	}
}
