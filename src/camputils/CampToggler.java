package camputils;

import java.util.Scanner;

import camps.CampList;
import datamanagement.CampDataManager;
import datamanagement.CampExcelData;
import models.Staff;
import models.User;

/**
 * Change the visibility of the chosen camp.
 * Update the new camp information accordingly.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class CampToggler extends CampManager{
	/**
	 * Constructor of CampToggler.
	 * @param user The user who change the visibility.
	 * @param campList The original campList.
	 */
	public CampToggler(User user, CampList campList) {
		super(user, campList);
	}
	
	/**
	 * New visibility.
	 */
	private boolean visibility;
	
	private CampDataManager campData;

	@Override
	/**
	 * Change the visibility of the chosen camp.
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
			//If there are student/committee registered, the camp visibility cannot be edited
			if(campList.get(campIndex).getStudentIdList().size()!=0 || campList.get(campIndex).getCommitteeIdList().size()!=0) {
				System.out.println("The visibility cannot be toggled");
			}
			else {
				String visible;
				System.out.print("Enter new camp visibility (on/off):");
		        do {
		        	visible = sc.nextLine().trim();
		        	if(!(visible.equals("on") || (visible.equals("off"))))
		        		System.out.print("Please enter valid visibility:");
		        }while(!(visible.equals("on") || (visible.equals("off"))));
		        if(visible.equals("on")) visibility = true;
		        else visibility = false;
		        // set the new visibility to the edited camp
		        campList.get(campIndex).setVisibility(visibility);
		        
		        System.out.println("Successfully set the visibility to: " + visible);
			}
			campList.sort();
			campData = new CampExcelData();
			campData.save(campList);	
		}
	}
}
