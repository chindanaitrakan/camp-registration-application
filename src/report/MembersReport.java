package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import camps.Camp;
import camps.CampList;
import filters.CampCommitteeIdFilter;
import filters.CampFilter;
import filters.CampStaffIdFilter;
import filters.FilterUI;
import models.Staff;
import models.Student;
import models.User;
/**
 * Class for the members report.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class MembersReport implements ReportGenerator{
	/**
	 * Generates the IDS of users in each camp in either txt or csv.
	 * @param User The user generating the report.
	 * @param campList The list of camps.
	 */
	@Override
	public void generate(User user, CampList campList) {
		
		Scanner sc = new Scanner(System.in);
		String filename, filepath;
		CampList filteredCampList = new CampList();
		CampList firstFilteredCampList = new CampList();
		CampFilter campFilter;

		
		boolean genAttendeeList = false;
		boolean genCommitteeList = false;
		

		System.out.println("[1] Generate Attendee List");
		System.out.println("[2] Generate Committee List");
		System.out.println("[3] Generate All");
		System.out.print("Select an option: ");
        String optionstr = sc.nextLine().trim();
		
        while(!(optionstr.matches("^[1-3]{1}$"))){
        	System.out.println("Enter a valid option:");
        	optionstr = sc.nextLine().trim();
        }
        
        int option = Integer.parseInt(optionstr);
        switch (option){
        	case 1: genAttendeeList = true;
        			break;
        	case 2: genCommitteeList = true;
        			break;
        	case 3: genAttendeeList = true;
        			genCommitteeList = true;
        			break;
        }
        System.out.println("Enter file format: \n[1] txt \n[2] csv");
        int choice = Integer.parseInt(sc.nextLine().trim());
        while(!(choice==1 || choice ==2)){
            System.out.println("Enter a valid choice:");
            choice = Integer.parseInt(sc.nextLine().trim());
        }
        
        //filter by custom
      	firstFilteredCampList = FilterUI.chooseFilter(user, campList);
        
        if (choice == 1) {
	        while(true) {
	        	System.out.println("Enter the file name:");
	        	filename = sc.nextLine().trim();
	            filepath = "src/Data/reports/"+filename+".txt";
	        	try {
	  		      File myObj = new File(filepath);
	  		      if (myObj.createNewFile()) {
	  		        System.out.println("File created: " + myObj.getName());
	  		        break;
	  		      } else {
	  		        System.out.println("File already exists.");
	  		      }
	  		    } catch (IOException e) {
	  		      System.out.println("An error occurred.");
	  		      e.printStackTrace();
	  		    }
	        }
	        
			try {
				FileWriter myWriter = new FileWriter(filepath);
	  
				myWriter.write("Members Report");
				myWriter.write("\r\n");
				
				if(user instanceof Staff) {
					Staff staff = (Staff) user;
					campFilter = new CampStaffIdFilter();
					filteredCampList = campFilter.filter(staff, firstFilteredCampList);
				}

				else {
					Student student =  (Student) user;
					campFilter = new CampCommitteeIdFilter();
					filteredCampList = campFilter.filter(student, firstFilteredCampList);
				}
				

				for(int i=0; i<filteredCampList.size(); i++) {

					Camp selectedCamp = filteredCampList.get(i);
					myWriter.write("\r\n");
					myWriter.write("["+(i+1)+"] "+selectedCamp.getName());
					myWriter.write("\r\n");

					if(genAttendeeList) {
						ArrayList<String> IdList = selectedCamp.getStudentIdList();
						myWriter.write("    List of Students");
						myWriter.write("\r\n");
						for(int j=0; j<IdList.size(); j++) {
							myWriter.write("    "+"["+(j+1)+"] "+ IdList.get(j));
							myWriter.write("\r\n");
						}
					}
					if(genCommitteeList) {
						ArrayList<String> IdList = selectedCamp.getCommitteeIdList();
						myWriter.write("    List of Committees");
						myWriter.write("\r\n");
						for(int j=0; j<IdList.size(); j++) {
							myWriter.write("    "+"["+(j+1)+"] "+ IdList.get(j));
							myWriter.write("\r\n");
						}
					}
				}
							
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
			  System.out.println("An error occurred.");
			      e.printStackTrace();
			 }	
        }
		
        
        else if (choice == 2){
    		try {
    			while(true) {
    	        	System.out.println("Enter the file name:");
    	        	filename = sc.nextLine().trim();
    	            filepath = "src/Data/reports/"+filename+".csv";
    	        	try {
    	  		      File myObj = new File(filepath);
    	  		      if (myObj.createNewFile()) {
    	  		        System.out.println("File created: " + myObj.getName());
    	  		        break;
    	  		      } else {
    	  		        System.out.println("File already exists.");
    	  		      }
    	  		    } catch (IOException e) {
    	  		      System.out.println("An error occurred.");
    	  		      e.printStackTrace();
    	  		    }
    	        }
    			
                filepath = "src/Data/reports/" + filename + ".csv";
                FileWriter myWriter = new FileWriter(filepath);
                myWriter.write("Camp Name, Member Type, Member ID\n");
                for (Camp selectedCamp : firstFilteredCampList.getCampList()) {
                    String campName = selectedCamp.getName().replace(",", ";"); 
                    if (genAttendeeList) {
                        for (String studentId : selectedCamp.getStudentIdList()) {
                            myWriter.write(campName + ",Student," + studentId + "\n");
                        }
                    }
                    if (genCommitteeList) {
                        for (String committeeId : selectedCamp.getCommitteeIdList()) {
                            myWriter.write(campName + ",Committee," + committeeId + "\n");
                        }
                    }
                }
    		myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    	}
	}
}