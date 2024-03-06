package suggestionutils;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import camps.CampList;
import datamanagement.SuggestionDataManager;
import datamanagement.SuggestionExcelData;
import models.Student;
import models.User;
import suggestion.Suggestion;
import suggestion.SuggestionList;
import utils.DataViewer;

/**
 * View suggestions for student type users, implements DataView interface.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class SuggestionViewerStudent implements DataViewer{

	@Override
	/**
	 * Allows students to view suggestions.
	 * @param user The user viewing suggestions.
	 * @param campList campList The list of camps that is accessible to the user.
	 */
	public void viewData(User user, CampList campList) {	
		
		//initialize everything
		SuggestionList suggestionList = new SuggestionList();
		String userID;
		SuggestionDataManager suggestionData = new SuggestionExcelData();
		Student student = (Student) user;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		userID = user.getUserID().trim().toUpperCase();
		suggestionData.load(suggestionList);
		Scanner sc = new Scanner(System.in);
		
		int count;
		System.out.println("════════════════════════════════════════════════════════════════");
		System.out.println("                List of Suggestions Posted: ");
		count = 1;
		for (int i=0; i<suggestionList.size(); i++) {
			if (suggestionList.get(i).getUserID().equals(userID)) {
				System.out.println("════════════════════════════════════════════════════════════════");
				System.out.println("[" + count + "] Camp Name: "+suggestionList.get(i).getName() + " | Date: " 
						+ dateFormat.format(suggestionList.get(i).getSuggestionDate())
						+ " | Status: " + (suggestionList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
				count++;
			}
		}
		System.out.println("════════════════════════════════════════════════════════════════");
		while (true) {
			System.out.println("[1] View Suggestion \n[2] Edit Pending Suggestion \n[3] Delete Pending Request \n[4] Back");
			System.out.print("Select an option: ");
	        String optionstr = sc.nextLine().trim();
	        System.out.println();
	        // get what the user wants to do
	        while(!(optionstr.matches("^[1-4]{1}$"))){
	            System.out.println("Enter a valid option:");
	            optionstr = sc.nextLine().trim();
	        }
	
	        int option = Integer.parseInt(optionstr);
	        if (option == 4) break;
	        switch (option){
	            case 1:
	    			System.out.println("\n");
	    			System.out.println("════════════════════════════════════════════════════════════════");
	    			System.out.println("                List of Suggestions Posted: ");
	    			count = 1;
	    			for (int i=0; i<suggestionList.size(); i++) {
	    				if (suggestionList.get(i).getUserID().equals(userID)) {
	    					System.out.println("════════════════════════════════════════════════════════════════");
	    					System.out.println("[" + count + "] Camp Name: "+suggestionList.get(i).getName() + " | Date: " 
	    							+ dateFormat.format(suggestionList.get(i).getSuggestionDate())
	    							+ " | Status: " + (suggestionList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
	    					count++;
	    				}
	    			}
	    			System.out.println("════════════════════════════════════════════════════════════════");
			        System.out.print("Select the suggestion you would like to access (input the number): ");
			        try {
				        int suggestionNumber = sc.nextInt();
						sc.nextLine();
						System.out.println();
						if (suggestionNumber > count-1) {
							System.out.println("Invalid input.");
							System.out.println();
							continue;
						}
				        int tracker1=0, tracker2=0;
				        //match the suggestionNumber to the index of the actual suggestion in suggestionList
						for (int i=0; i<suggestionList.size(); i++) {
							if (tracker2 == suggestionNumber) break;
							if (suggestionList.get(i).getUserID().equals(userID)) {
								tracker2++;
							}
							tracker1++;
						}
				        Suggestion suggestion = suggestionList.get(tracker1-1);
				        //print all content related to suggestion
						System.out.println("════════════════════════════════════════════════════════════════");
						System.out.println("Camp Name: "+suggestion.getName() + " | Date: " 
								+ dateFormat.format(suggestion.getSuggestionDate())
								+ " | Status: " + (suggestion.getProcessedStatus() ? "Processed" : "Pending"));
						System.out.println("════════════════════════════════════════════════════════════════");
				        for(int i=0; i<suggestionList.size();i++) {
				        	if (suggestionList.get(i).getSuggestionID().equals(suggestion.getSuggestionID())) {
				        		System.out.println(suggestionList.get(i).getUserID() + " (" + dateFormat.format(suggestionList.get(i).getSuggestionDate()) + "): "
				        				+ suggestionList.get(i).getSuggestion());
				        		System.out.println("OUTCOME OF SUGGESTION: " + suggestionList.get(i).getApprovedOrDeclined());
				        	}
				        }
						System.out.println("════════════════════════════════════════════════════════════════");
			        } catch (InputMismatchException e) {
			        	System.out.println("Invalid input");
			        } 
			        break;
	            case 2:
	    			System.out.println("\n");
	    			System.out.println("════════════════════════════════════════════════════════════════");
	    			System.out.println("                List of Suggestions Posted: ");
	    			count = 1;
	    			for (int i=0; i<suggestionList.size(); i++) {
	    				if (suggestionList.get(i).getUserID().equals(userID)) {
	    					System.out.println("════════════════════════════════════════════════════════════════");
	    					System.out.println("[" + count + "] Camp Name: "+suggestionList.get(i).getName() + " | Date: " 
	    							+ dateFormat.format(suggestionList.get(i).getSuggestionDate())
	    							+ " | Status: " + (suggestionList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
	    					count++;
	    				}
	    			}
	    			System.out.println("════════════════════════════════════════════════════════════════");
			        System.out.print("Select the suggestion you would like to edit (input the number): ");
			        try {
				        int suggestionNumber = sc.nextInt();
				        sc.nextLine();
						System.out.println();
						if (suggestionNumber > count-1) {
							System.out.println("Invalid input.");
							System.out.println();
							continue;
						}
				        int tracker1=0, tracker2=0;
				        //match the suggestionNumber to the index of the actual suggestion in suggestion list
						for (int i=0; i<suggestionList.size(); i++) {
							if (tracker2 == suggestionNumber) break;
							if (suggestionList.get(i).getUserID().equals(userID)) {
								tracker2++;
							}
							tracker1++;
						}
				        Suggestion suggestion = suggestionList.get(tracker1-1);
				        if (suggestion.getProcessedStatus()) {
				        	System.out.println("This suggestion has been processed already, you can no longer edit it.");
				        	break;
				        }
				        System.out.print("Enter your new suggestion: ");
	        	        String newSuggestion = sc.nextLine();
	        	        suggestion.setSuggestion(newSuggestion);
	        	        suggestionData.save(suggestionList);
	        	        System.out.println("Your suggestion has been updated successfully.");
			        } catch (InputMismatchException e) {
			        	System.out.println("Invalid input. ");
			        } 
	            	break;
	            case 3:
	    			System.out.println("\n");
	    			System.out.println("════════════════════════════════════════════════════════════════");
	    			System.out.println("                List of Suggestions Posted: ");
	    			count = 1;
	    			for (int i=0; i<suggestionList.size(); i++) {
	    				if (suggestionList.get(i).getUserID().equals(userID)) {
	    					System.out.println("════════════════════════════════════════════════════════════════");
	    					System.out.println("[" + count + "] Camp Name: "+suggestionList.get(i).getName() + " | Date: " 
	    							+ dateFormat.format(suggestionList.get(i).getSuggestionDate())
	    							+ " | Status: " + (suggestionList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
	    					count++;
	    				}
	    			}
	    			System.out.println("════════════════════════════════════════════════════════════════");
			        System.out.print("Select the suggestion you would like to delete (input the number): ");
			        try {
				        int suggestionNumber = sc.nextInt();
						sc.nextLine();
						System.out.println();
						if (suggestionNumber > count-1) {
							System.out.println("Invalid input.");
							System.out.println();
							continue;
						}
				        int tracker1=0, tracker2=0;
				        //match the suggestionNumber to the index of the actual suggestion in suggestionList
						for (int i=0; i<suggestionList.size(); i++) {
							if (tracker2 == suggestionNumber) break;
							if (suggestionList.get(i).getUserID().equals(userID)) {
								tracker2++;
							}
							tracker1++;
						}
				        Suggestion suggestion = suggestionList.get(tracker1-1);
				        student.setCommitteePoints(student.getCommitteePoints() + 1);
				        if (suggestion.getProcessedStatus()) {
				        	System.out.println("This suggestion has been processed already, you can no longer delete it.");
				        	break;
				        }
				        suggestionList.remove(tracker1-1);
				        suggestionData.save(suggestionList);
				        System.out.println("Your suggestion has been deleted successfully.");
				        student.setCommitteePoints(student.getCommitteePoints() - 1);
			        } catch (InputMismatchException e) {
			        	System.out.println("Invalid input");
			        } 
				    break;
	        	}
			System.out.println();
		}
	}
}
