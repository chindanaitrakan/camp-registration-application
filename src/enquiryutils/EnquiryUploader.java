package enquiryutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import camps.CampList;
import datamanagement.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import models.Student;
import models.User;
import suggestion.SuggestionList;
import utils.MessageUploader;

/**
 * Class to implement submittion of enquiry by a student.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class EnquiryUploader implements MessageUploader{
	
    /**
     * Appends the enquiry into the ArrayList and calls save which will update the excel sheet.
     * @user user The student that submitted the new enquiry.
     * @param campList The list of camps that are accessible to the student.
     */
	@Override
	public void submit(User user, CampList campList) {
		//initialize everything
		EnquiryList enquiryList = new EnquiryList();;
		String enquiryID, userID, campName, enquiryQuestion, replyTo;
		boolean processedStatus;
		Date enquiryDate;
		int enquiryOrReply;
		
		// if user is not a student
		if(!(user instanceof Student)) {
			System.out.println("You are not allowed to submit enquiries!");
		}
		// if user is a student
		else{
			Scanner sc = new Scanner(System.in);
			Student student = (Student) user;
			// get all camp information
	        // get campName
	        System.out.print("Which camp are you enquiring about: ");
	        campName = sc.nextLine().trim();
	        userID = user.getUserID().trim().toUpperCase();
	        
	        boolean campFound = false;
	        
			for(int i=0; i<campList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				if(campList.get(i).getName().toUpperCase().equals(campName.toUpperCase()) && campList.get(i).getVisibility().equals(true)
					&& (campList.get(i).getUserGroup().equals(user.getFaculty()) || campList.get(i).getUserGroup().equals("ALL"))) {
                	campFound = true;
                	student.setEnquiryDataManager(new EnquiryExcelData());
                	student.getEnquiryDataManager().load(enquiryList);
            		
            		//generate a random ID for the enquiryID
            		UUID uuid = UUID.randomUUID();
            		enquiryID = uuid.toString();
            		//get userID
            		userID = user.getUserID().toUpperCase();
            		//set processed status as false
            		processedStatus = false;
            		//set enquiry or reply to enquiry (which is 0)
            		enquiryOrReply = 0;
            		//set reply to as default - NIL
            		replyTo = "NIL";
        	        //get enquiry dates
        	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	        enquiryDate = new Date();
        	        //get enquiry
        	        System.out.print("What would you like to enquire about this camp: ");
        	        enquiryQuestion = sc.nextLine();
        	        enquiryList.add(new Enquiry(enquiryID, userID, campName, enquiryQuestion, enquiryDate, processedStatus, enquiryOrReply, replyTo));
        	        student.getEnquiryDataManager().save(enquiryList);
        	        System.out.println("Enquiry posted successfully.");
                    break;
				}
			}
                
            if (!campFound) {
            	System.out.println("Invalid camp.");
            }
		} 
		
	}
}
