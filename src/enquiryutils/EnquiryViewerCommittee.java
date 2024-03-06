package enquiryutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

import camps.CampList;
import datamanagement.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import models.Student;
import models.User;
import utils.DataViewer;

/**
 * Class to implement viewing of enquiry by a camp committee.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class EnquiryViewerCommittee implements DataViewer{

    /**
     * Prints the list of enquiries that is visible to the camp committee.
     * @user user The camp committee that is viewing the enquiry.
     * @param campList The list of camps that are accessible to the camp committee.
     */
	@Override
	public void viewData(User user, CampList campList) {
		//initialize everything
		EnquiryList enquiryList = new EnquiryList();
		String enquiryID, userID, campName, replyTo;
		boolean processedStatus;
		Date enquiryDate;
		int enquiryOrReply;
		Student student  = (Student) user;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		userID = student.getUserID().trim().toUpperCase();
		student.setEnquiryDataManager(new EnquiryExcelData());
		student.getEnquiryDataManager().load(enquiryList);
		Scanner sc = new Scanner(System.in);
		
		boolean campFound = false;
		int campIndex = 0;
		for (int i=0; i<campList.size(); i++) {
			if (campList.get(i).getCommitteeIdList().contains(userID)) {
				campFound = true;
				break;
			}
			campIndex++;
		}
		
		if (!campFound) {
			System.out.println("You are not a camp committee of any camps.");
			return;
		}
		
		campName = campList.get(campIndex).getName();

    	boolean enquiryFound = false;
    	//find the respective enquiry in the enquiry list
		System.out.println("\n");
		System.out.println("═════════════════════════════════════════════════════════════════════════════════");
		System.out.println("                        List of Enquiries Received: ");
		int count=1;
		for(int i=0; i<enquiryList.size(); i++) {
			//if user is the owner of the camp
			if (enquiryList.get(i).getName().toUpperCase().trim().equals(campName) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
				enquiryFound = true;
				System.out.println("═════════════════════════════════════════════════════════════════════════════════");
				System.out.println("[" + count + "] StudentID: " + enquiryList.get(i).getUserID()
						+ " | Date: " + dateFormat.format(enquiryList.get(i).getEnquiryDate())
						+ " | Status: " + (enquiryList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
				count++;
			}
		}
		System.out.println("═════════════════════════════════════════════════════════════════════════════════");
    	
    	if (!enquiryFound) {
    		System.out.println("There are no enquiries at the moment.");
    		return;
    	}
		
		while (true) {
			System.out.println("[1] View Enquiry \n[2] Answer Enquiry \n[3] Back");
			System.out.print("Select an option: ");
	        String optionstr = sc.nextLine().trim();
	        System.out.println();
	        // get what the user wants to do
	        while(!(optionstr.matches("^[1-3]{1}$"))){
	            System.out.println("Enter a valid option:");
	            optionstr = sc.nextLine().trim();
	        }

	        int option = Integer.parseInt(optionstr);
	        if (option == 3) break;
	        if (option == 1) {
		        System.out.print("Select the enquiry you would like to access (input the number): ");
		        try {
			        int enquiryNumber = sc.nextInt();
					sc.nextLine();
					if (enquiryNumber > count-1) {
						System.out.println("Invalid input.");
						System.out.println();
						continue;
					}
			        int tracker1=0, tracker2=0;
			        //match the enquiryNumber to the index of the actual enquiry in enquiryList
					for (int i=0; i<enquiryList.size(); i++) {
						if (tracker2 == enquiryNumber) break;
						if (enquiryList.get(i).getName().toUpperCase().trim().equals(campName) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
							tracker2++;
						}
						tracker1++;
					}
			        Enquiry enquiry = enquiryList.get(tracker1-1);
			        //print all content related to enquiry
					System.out.println("════════════════════════════════════════════════════════════════");
					System.out.println("Camp Name: "+enquiry.getName() + " | Date: " 
							+ dateFormat.format(enquiry.getEnquiryDate())
							+ " | Status: " + (enquiry.getProcessedStatus() ? "Processed" : "Pending"));
					System.out.println("════════════════════════════════════════════════════════════════");
			        for(int i=0; i<enquiryList.size();i++) {
			        	if (enquiryList.get(i).getEnquiryID().equals(enquiry.getEnquiryID()) || enquiryList.get(i).getReplyTo().equals(enquiry.getEnquiryID())) {
			        		System.out.println(enquiryList.get(i).getUserID() + " (" + dateFormat.format(enquiryList.get(i).getEnquiryDate()) + "): "
			        				+ enquiryList.get(i).getEnquiry());
			        	}
			        }
					System.out.println("════════════════════════════════════════════════════════════════");
		        } catch (InputMismatchException e) {
		        	System.out.println("Invalid input");
		        } 

	        } else {

    	        System.out.print("Select the enquiry you would like to answer (input the number): ");
		        try {
			        int enquiryNumber = sc.nextInt();
			        sc.nextLine();
					if (enquiryNumber > count-1) {
						System.out.println("Invalid input.");
						System.out.println();
						continue;
					}
			        int tracker1=0, tracker2=0;
			        //match the enquiryNumber to the index of the actual enquiry in enquiryList
					for (int i=0; i<enquiryList.size(); i++) {
						if (tracker2 == enquiryNumber) break;
						if (enquiryList.get(i).getName().toUpperCase().trim().equals(campName) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
							tracker2++;
						}
						tracker1++;
					}
            		//generate a random ID for the enquiryID
            		UUID uuid = UUID.randomUUID();
            		enquiryID = uuid.toString();
            		//get userID
            		userID = user.getUserID().toUpperCase();
            		//set processed status as false
            		processedStatus = true;
            		//set enquiry or reply to reply (which is 1)
            		enquiryOrReply = 1;
            		//set reply accordingly
            		replyTo = enquiryList.get(tracker1-1).getEnquiryID();
        	        //get enquiry dates
        	        enquiryDate = new Date();
        	        //get enquiry
        	        System.out.print("Response to the enquiry: ");
        	        String response = sc.nextLine();
        	        enquiryList.add(new Enquiry(enquiryID, userID, campName, response, enquiryDate, processedStatus, enquiryOrReply, replyTo));
        	        enquiryList.get(tracker1-1).setProcessedStatus(true);
        	        student.getEnquiryDataManager().save(enquiryList);
        	        System.out.println("Response posted successfully.");
        	        System.out.println();
        	        student.setCommitteePoints(student.getCommitteePoints() + 1);
		        } catch (InputMismatchException e) {
		        	System.out.println("Invalid input");
		        }
		        break;
	        }
		}
	}
	//end of the method
	
}
