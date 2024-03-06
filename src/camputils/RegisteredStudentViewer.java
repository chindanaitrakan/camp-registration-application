package camputils;

import java.util.ArrayList;
import java.util.Scanner;

import camps.CampList;
import models.Staff;
import models.User;
import utils.DataViewer;

/**
 * View list of student who registered in the camp.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class RegisteredStudentViewer implements DataViewer{

	@Override
	/**
	 * View list of registered students in the camp.
	 * @param user The user who views the list of student.
	 * @param campList The campList which is accessible for the user.
	 */
	public void viewData(User user, CampList campList) {
		Scanner sc = new Scanner(System.in);
		boolean campFound = false;
		int campIndex = 0;
		// if user is a staff
		if(user instanceof Staff) {
			System.out.print("Enter the camp name in which you would like to see the attendees: ");
			String campName = sc.nextLine().trim();
			for (int i=0; i<campList.size();i++) {
				if (campList.get(i).getName().toUpperCase().equals(campName.toUpperCase())) {
					campFound = true;
					break;
				}
				campIndex++;
			}
			if (!campFound) {
				System.out.println("Camp not found.");
				return;
			}
			ArrayList <String> studentIdList = campList.get(campIndex).getStudentIdList();
			ArrayList <String> committeeIdList = campList.get(campIndex).getCommitteeIdList();
			System.out.println("\n");
			System.out.println("════════════════════════════════════════════════════════════════");
			System.out.println("                   List of Students: ");
			for(int i=0; i<studentIdList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				System.out.println("════════════════════════════════════════════════════════════════");
				System.out.println("[" + i+1 + "] "+studentIdList.get(i));
			}
			System.out.println("════════════════════════════════════════════════════════════════");
			System.out.println("                List of Camp Committee: ");
			for(int i=0; i<committeeIdList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				System.out.println("════════════════════════════════════════════════════════════════");
				System.out.println("[" + i+1 + "] "+committeeIdList.get(i));
			}
			System.out.println("════════════════════════════════════════════════════════════════");
			System.out.println();
		}
		// if user is a camp committee
		else{
			String userID = user.getUserID().toUpperCase();
			for (int i=0; i<campList.size();i++) {
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
			ArrayList <String> studentIdList = campList.get(campIndex).getStudentIdList();
			ArrayList <String> committeeIdList = campList.get(campIndex).getCommitteeIdList();
			System.out.println("\n");
			System.out.println("════════════════════════════════════════════════════════════════");
			System.out.println("                   List of Students: ");
			for(int i=0; i<studentIdList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				System.out.println("════════════════════════════════════════════════════════════════");
				System.out.println("[" + i+1 + "] "+studentIdList.get(i));
			}
			System.out.println("════════════════════════════════════════════════════════════════");
			System.out.println("                List of Camp Committee: ");
			for(int i=0; i<committeeIdList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				System.out.println("════════════════════════════════════════════════════════════════");
				System.out.println("[" + i+1 + "] "+committeeIdList.get(i));
			}
			System.out.println("════════════════════════════════════════════════════════════════");
			System.out.println();
		}	
	}
	
}
