package suggestionutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import camps.CampList;
import datamanagement.SuggestionExcelData;
import models.Student;
import models.User;
import suggestion.Suggestion;
import suggestion.SuggestionList;
import utils.MessageUploader;

/**
 * Class to upload a suggestion.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class SuggestionUploader implements MessageUploader{
	/**
	 * Submits suggestion.
	 * @param user The user uploading suggestion.
	 * @param campList The list of camps that is accessible to the user.
	 */
	public void submit(User user, CampList campList) {
		//initialize everything
		SuggestionList suggestionList = new SuggestionList();
		String suggestionID, userID, campName, suggestionQuestion, approvedOrDeclined;
		boolean processedStatus;
		Date suggestionDate;
		
		// if user is not a student
		if(!(user instanceof Student)) {
			System.out.println("You are not allowed to submit suggestions!");
		}
		// if user is a student
		else{
			Scanner sc = new Scanner(System.in);
			Student student = (Student) user;
	        userID = user.getUserID().trim().toUpperCase();
			//Check if student is a valid camp committee
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
				sc.close();
				return;
			}
			
			campName = campList.get(campIndex).getName();

			student.setSuggestionDataManager(new SuggestionExcelData());
    		student.getSuggestionDataManager().load(suggestionList);
    		
    		//generate a random ID for the suggestionID
    		UUID uuid = UUID.randomUUID();
    		suggestionID = uuid.toString();
    		//get userID
    		userID = user.getUserID().toUpperCase();
    		//set processed status as false
    		processedStatus = false;
    		//set reply to as default - PENDING
    		approvedOrDeclined = "PENDING";
	        //get suggestion dates
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        suggestionDate = new Date();
	        //get suggestion
	        System.out.print("What is your suggestion for this camp: ");
	        suggestionQuestion = sc.nextLine();
	        suggestionList.add(new Suggestion(suggestionID, userID, campName, suggestionQuestion, suggestionDate, processedStatus, approvedOrDeclined));
	        student.getSuggestionDataManager().save(suggestionList);
	        System.out.println("Suggestion posted successfully.");
	        student.setCommitteePoints(student.getCommitteePoints() + 1);
		} 
	}
}
