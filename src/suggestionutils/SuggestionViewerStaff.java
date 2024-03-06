package suggestionutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import camps.CampList;
import datamanagement.SuggestionDataManager;
import datamanagement.SuggestionExcelData;
import models.User;
import suggestion.Suggestion;
import suggestion.SuggestionList;
import utils.DataViewer;

/**
 * View suggestions for staff user types, implements DataViewer interface.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class SuggestionViewerStaff implements DataViewer{
	@Override
	/**
	 * View data regarding suggestions.
	 * @param user The user viewing suggestions.
	 * @param campList The list of camps that is accessible to the user.
	 */
	public void viewData(User user, CampList campList) {
		//initialize everything
		SuggestionList suggestionList = new SuggestionList();
		String userID;
		SuggestionDataManager suggestionData = new SuggestionExcelData();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		userID = user.getUserID().trim().toUpperCase();
		suggestionData.load(suggestionList);
		Scanner sc = new Scanner(System.in);
		
		//append the camps this staff is incharge of
		ArrayList <String> campIncharge = new ArrayList<String>();
		for(int i=0; i<campList.size();i++) {
			if (campList.get(i).getStaffId().equals(userID)) {
				campIncharge.add(campList.get(i).getName());
			}
		}
		System.out.println("\n");
		System.out.println("═════════════════════════════════════════════════════════════════════════════════");
		System.out.println("                        List of Suggestions Received: ");
		int count=1;
		for(int i=0; i<suggestionList.size(); i++) {
			//if user is the owner of the camp
			if (campIncharge.contains(suggestionList.get(i).getName())) {
				System.out.println("═════════════════════════════════════════════════════════════════════════════════");
				System.out.println("[" + count + "] Camp Name: "+suggestionList.get(i).getName() 
						+ " | StudentID: " + suggestionList.get(i).getUserID()
						+ " | Date: " + dateFormat.format(suggestionList.get(i).getSuggestionDate())
						+ " | Status: " + (suggestionList.get(i).getProcessedStatus() ? "Processed" : "Pending"));
				count++;
			}
		}
		System.out.println("═════════════════════════════════════════════════════════════════════════════════");
		
		while (true) {
			System.out.println("[1] View Suggestion \n[2] Approve/Decline Suggestion \n[3] Back");
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
			        System.out.print("Select the suggestion you would like to access (input the number): ");
			        try {
				        int suggestionNumber = sc.nextInt();
						sc.nextLine();
						if (suggestionNumber > count-1) {
							System.out.println("Invalid input.");
							System.out.println();
							continue;
						}
				        int tracker1=0, tracker2=0;
				        //match the suggestionNumber to the index of the actual suggestion in suggestionList
						for (int i=0; i<suggestionList.size(); i++) {
							if (tracker2 == suggestionNumber) break;
							if (campIncharge.contains(suggestionList.get(i).getName())) {
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
	    	        System.out.print("Select the suggestion you would like to approve/decline (input the number): ");
			        try {
				        int suggestionNumber = sc.nextInt();
				        sc.nextLine();
						if (suggestionNumber > count-1) {
							System.out.println("Invalid input.");
							System.out.println();
							continue;
						}
				        int tracker1=0, tracker2=0;
				        //match the suggestionNumber to the index of the actual suggestion in suggestionList
						for (int i=0; i<suggestionList.size(); i++) {
							if (tracker2 == suggestionNumber) break;
							if (campIncharge.contains(suggestionList.get(i).getName())) {
								tracker2++;
							}
							tracker1++;
						}
						System.out.println("Would you like to approve/decline the suggestion made to " + suggestionList.get(tracker1-1).getName() + ".");
						System.out.println("[1] Approve \n[2] Decline \n[3] Back ");
						String decision = sc.nextLine().trim();
				        while(!(decision.matches("^[1-3]{1}$"))){
				            System.out.println("Enter a valid option:");
				            decision = sc.nextLine().trim();
				        }
				        int decisionInt = Integer.parseInt(decision);
				        if (decisionInt == 3) break;
	        	        suggestionList.get(tracker1-1).setProcessedStatus(true);
	        	        suggestionList.get(tracker1-1).setApprovedOrDeclined((decisionInt == 1 ? "APPROVED" : "DECLINED"));
	        	        suggestionData.save(suggestionList);
	        	        System.out.println("Suggestion " + (decisionInt == 1 ? "approve" : "declined") + " successfully.");
	        	        if (decisionInt == 1) { //Give 1 point to student
	        	        	String studentID = suggestionList.get(tracker1-1).getUserID();
	        	        	try {
	        	        		String email;
	        	        		int committeePoints;
	        	            	//WRITE TO EXCEL HERE!
	        	            	Workbook workbook = WorkbookFactory.create(new FileInputStream("src/Data/student_list.xlsx"));
	        	            	Sheet sheet = workbook.getSheetAt(0);
	        	                for (Row row : sheet) {
	        	                    Cell emailCell = row.getCell(1); // Assuming email is in the first column
	        	                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
	        	                        email = emailCell.getStringCellValue();
	        	                        String[] parts = email.split("@");
	        	                        if (parts.length > 1 && parts[0].toUpperCase().equals(studentID)) {
	        	                            // User found
        	                                try {
        	                                	committeePoints = (int) row.getCell(4).getNumericCellValue();
        	                                	row.getCell(4).setCellValue(committeePoints + 1);
        	                                } catch (NullPointerException NPE) {
        	                                	committeePoints = 1;
        	                                }
	        	                            break; // Exit the loop when the user is found
	        	                        }
	        	                    }
	        	                }

	        	            	// Now, you can save the changes back to the Excel file
	        	            	try (FileOutputStream fos = new FileOutputStream("src/Data/student_list.xlsx")) {
	        	            	    workbook.write(fos);
	        	            	}

	        	            	// Close the workbook when done
	        	            	workbook.close();
	        	            	
	        	        	} catch (IOException IOE) {
	        	            	System.out.println("No such file");
	        	            }
	        	        }
			        } catch (InputMismatchException e) {
			        	System.out.println("Invalid input");
			        }
			        break;

	        }
			System.out.println();

		}
	}
}
