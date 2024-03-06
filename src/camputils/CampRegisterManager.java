package camputils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;

import camps.Camp;
import camps.CampList;
import datamanagement.CampDataManager;
import datamanagement.CampExcelData;
import models.Student;
import models.User;

/**
 * Register the student as either camp committee or attendee.
 * Update the new studentIDList and committeeIDList accordingly.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class CampRegisterManager implements EnrollmentManager{
	
	/**
	 * Camp data manager for saving the data.
	 */
	private CampDataManager campData;
	
	@Override
	/**
	 * Allows student to register the camp as either attendee or committee.
	 * @param user The user who register the camp
	 * @param campList The list of the camp which is accessible to the user.
	 */
	public void process(User user, CampList campList) {
		//Upcast user to student
		Student student = (Student) user;
		//Get the camp name
		System.out.println("Enter the name of the camp ");
		Scanner sc = new Scanner(System.in);
		String campName = sc.nextLine().trim();
		
		//get campIndex in campList
		int campIndex = CampSearcher.searchCamp(campName, campList);
		//selected camp object
		Camp selectedCamp;
		//check whether the camp's data clash with other or not
		boolean dateClash = false;
		
		//check if camp is found
		if(campIndex==-1) {
			System.out.println("Camp not found");
		}
		else {
			//assign selected camp to the searchedcamp
			selectedCamp = campList.get(campIndex);

            // Check if there is any clash in the dates of camps
			for (int i = 0; i < campList.size(); i++) {
				//Not check if it is the same camp
				if (campList.get(i).getName().equals(selectedCamp.getName())) continue;
				//Not check if the student is not in the camp
				if (!(campList.get(i).getStudentIdList().contains(student.getUserID()))) continue;
				//check if there is a date clashes
				if (selectedCamp.getStartDate().before(campList.get(i).getEndDate()) && selectedCamp.getEndDate().after(campList.get(i).getStartDate())) {
					dateClash = true;
					break;
				}
			}
			
			 // Check the conditions which user is not allowed to register the camp     
		    if (selectedCamp.getWithdrawIdList().contains(student.getUserID())) 
            	System.out.println("You've withdrawn from this camp before ");
            
            else if (!(selectedCamp.getUserGroup().equals(student.getFaculty())) && !(selectedCamp.getUserGroup().equals("ALL")))
            	System.out.println("You are not in the allowed user group.");
            
            else if (selectedCamp.getRegistrationClosingDate().before(new Date())) 
            	System.out.println("You've missed the registration date for this camp, you can no longer register for it.");
			
			else if (dateClash)
				System.out.println("You are unable to register for this camp because there is a clash in the date with one of your previously registered camps!");
            
			else {
				System.out.println("Registering as:\n [1] camp attendee \n [2] camp committee member ");
	    		int choice = sc.nextInt();
	    		
	    		if (choice == 1) {
	    			//check if the user is already registered
	    			if (selectedCamp.getStudentIdList().contains(student.getUserID())) 
	                	System.out.println("You are already registered for this camp as an attendee");
	    			else if (selectedCamp.getCommitteeIdList().contains(student.getUserID()))
	    				System.out.println("You are not allowed to register for this camp as you are already a camp committee of this camp.");
	    			
	    			else {
	    				int remainingSlots = selectedCamp.getTotalSlots() - selectedCamp.getCampCommitteeSlots() - selectedCamp.getStudentIdList().size();
		    			
		    			if(remainingSlots<=0) {
		    				System.out.println("No more camp slots");
		    			}
		    			else {
		    				//add student to the studentIdList
		    				ArrayList<String> newStudentIdList = selectedCamp.getStudentIdList();
		    				newStudentIdList.add(student.getUserID());
		    				//sort the Id list before storing
		    				Collections.sort(newStudentIdList);
		    				
		    				//save to data base
		    	            campData = new CampExcelData();
		    	    		campData.save(campList);
		    	            System.out.println("Successfully register the camp as a camp attendee.");
		    			}
	    			}
	    		}
	    		// Registered as a camp committee
	    		else {
	    			if (student.getIsCampCommittee()) {
	    				System.out.println("Student is already part of a camp committee/ Student can only be a camp committee for one camp");
	    			}	else if (selectedCamp.getStudentIdList().contains(student.getUserID()))
	    				System.out.println("You are not allowed to register for this camp as a camp committee as you have already registered as an attendee.");
	    			
	    			else{
	    				int remainingSlots = selectedCamp.getCampCommitteeSlots() - selectedCamp.getCommitteeIdList().size();
	    				
	    				if(remainingSlots<=0) {
	    					System.out.println("No more camp slots");
	    				}
	    				else {
	    					//add student to the CommitteeId list
	    					ArrayList<String> newCommitteeIdList = selectedCamp.getCommitteeIdList();
	        				newCommitteeIdList.add(student.getUserID());
	        				//sort the Id list before storing
	        				Collections.sort(newCommitteeIdList);
	        				
	        				//save to data base
	        	            campData = new CampExcelData();
	        	    		campData.save(campList);
	        	            System.out.println("Successfully register the camp as a camp committee.");
	    				}
	    			}
	    		}
			} 
		}
		
	}

}
