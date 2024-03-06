package enquiryutils;

import camps.CampList;
import models.User;
import utils.DataViewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import datamanagement.EnquiryDataManager;
import datamanagement.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import models.Staff;;

/**
 * Class to implement viewing of enquiry by a staff.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class EnquiryViewerStaff implements DataViewer{

    /**
     * Prints the list of enquiries that is visible to the staff.
     * @user user The staff that is viewing the enquiry.
     * @param campList The list of camps that are accessible to the staff.
     */
	@Override
	public void viewData(User user, CampList campList) {
		//initialize everything
		EnquiryDataManager enquiryData = new EnquiryExcelData();
		EnquiryList enquiryList = new EnquiryList();
		String enquiryID, userID, campName, enquiryQuestion, replyTo;
		boolean processedStatus;
		Date enquiryDate;
		int enquiryOrReply;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		userID = user.getUserID().trim().toUpperCase();
		enquiryData.load(enquiryList);
		Scanner sc = new Scanner(System.in);
		
		// if user is staff
		if(user instanceof Staff) {
			//append the camps this staff is incharge of
			ArrayList <String> campIncharge = new ArrayList<String>();
			for(int i=0; i<campList.size();i++) {
				if (campList.get(i).getStaffId().equals(userID)) {
					campIncharge.add(campList.get(i).getName());
				}
			}
			System.out.println("\n");
			System.out.println("═════════════════════════════════════════════════════════════════════════════════");
			System.out.println("                        List of Enquiries Received: ");
			int count=1;
			for(int i=0; i<enquiryList.size(); i++) {
				//if user is the owner of the camp
				if (campIncharge.contains(enquiryList.get(i).getName()) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
					System.out.println("═════════════════════════════════════════════════════════════════════════════════");
					System.out.println("[" + count + "] Camp Name: "+enquiryList.get(i).getName() 
							+ " | StudentID: " + enquiryList.get(i).getUserID()
							+ " | Date: " + dateFormat.format(enquiryList.get(i).getEnquiryDate())
							+ " | Status: " + (enquiryList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
					count++;
				}
			}
			System.out.println("═════════════════════════════════════════════════════════════════════════════════");
			
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
		        switch (option){

		            case 1:
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
								if (campIncharge.contains(enquiryList.get(i).getName()) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
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
				        break;
		            case 2:
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
								if (campIncharge.contains(enquiryList.get(i).getName()) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
									tracker2++;
								}
								tracker1++;
							}
		            		//generate a random ID for the enquiryID
		            		UUID uuid = UUID.randomUUID();
		            		enquiryID = uuid.toString();
		            		//get userID
		            		userID = user.getUserID().toUpperCase();
		            		//get campName
		            		campName = enquiryList.get(tracker1-1).getName();
		            		//set processed status as false
		            		processedStatus = true;
		            		//set enquiry or reply to reply (which is 1)
		            		enquiryOrReply = 1;
		            		//set reply to accordingly
		            		replyTo = enquiryList.get(tracker1-1).getEnquiryID();
		        	        //get enquiry dates
		        	        enquiryDate = new Date();
		        	        //get enquiry
		        	        System.out.print("Response to the enquiry: ");
		        	        enquiryQuestion = sc.nextLine();
		        	        enquiryList.add(new Enquiry(enquiryID, userID, campName, enquiryQuestion, enquiryDate, processedStatus, enquiryOrReply, replyTo));
		        	        enquiryList.get(tracker1-1).setProcessedStatus(true);
		        	        enquiryData.save(enquiryList);
		        	        System.out.println("Response posted successfully.");
				        } catch (InputMismatchException e) {
				        	System.out.println("Invalid input");
				        }
				        break;

		        }
				System.out.println();

			}
			
		}
	}
}
