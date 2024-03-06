package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import datamanagement.EnquiryDataManager;
import datamanagement.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import filters.CampCommitteeIdFilter;
import filters.CampFilter;
import filters.CampStaffIdFilter;
import filters.FilterUI;
import models.Staff;
import models.Student;
import models.User;

/**
 * Class for inquiry report.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class EnquiryReport implements ReportGenerator{
	
	@Override
	/**
	 * Generates an inquiry report and writes it in either txt or csv .
	 * @param user The user currently logged in.
	 * @param campList The list of camps.
	 */
	public void generate(User user, CampList campList) {
		
		Scanner sc = new Scanner(System.in);
		Staff staff = (Staff) user;
		String filename, filepath;
		CampList filteredCampList = new CampList();
		CampList firstFilteredCampList = new CampList();
		EnquiryDataManager enquiryData = new EnquiryExcelData();
		EnquiryList enquiryList = new EnquiryList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int count = 0;
		
		enquiryData.load(enquiryList);
		
		CampFilter campFilter;

		
        System.out.println("Enter file format: \n[1] txt \n[2] csv");
        int choice = Integer.parseInt(sc.nextLine().trim());
        while(!(choice==1 || choice ==2)){
            System.out.println("Enter a valid choice:");
            choice = Integer.parseInt(sc.nextLine().trim());
        }
        
        //filter by custom
      	firstFilteredCampList = FilterUI.chooseFilter(user, campList);
		campFilter = new CampStaffIdFilter();
		filteredCampList = campFilter.filter(staff, firstFilteredCampList);
        
        if (choice == 1) {
        	try {
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
				FileWriter myWriter = new FileWriter(filepath);
	  
				myWriter.write("Enquiries Report");
				myWriter.write("\r\n");

				for(int i=0; i<filteredCampList.size(); i++) {
					Camp selectedCamp = filteredCampList.get(i);
					myWriter.write("\r\n");
					myWriter.write("["+(i+1)+"] "+selectedCamp.getName());
					myWriter.write("\r\n");
					count = 0;
					for (int j=0; j<enquiryList.size(); j++) {
						if (selectedCamp.getName().equals(enquiryList.get(j).getName())) {
							Enquiry selectedEnquiry = enquiryList.get(j);
							myWriter.write("    "+"["+(count+1)+"] "+ selectedEnquiry.getUserID() + " ("+ dateFormat.format(selectedEnquiry.getEnquiryDate()) +") : "+ selectedEnquiry.getEnquiry());
							myWriter.write("\r\n");
							count++;
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
    			
				FileWriter myWriter = new FileWriter(filepath);
				  
				myWriter.write("Camp Name, UserID, Date, Enquiry Message");
				myWriter.write("\r\n");
				for(int i=0; i<filteredCampList.size(); i++) {
					Camp selectedCamp = filteredCampList.get(i);
					for (int j=0; j<enquiryList.size(); j++) {
						if (selectedCamp.getName().equals(enquiryList.get(j).getName())) {
							Enquiry selectedEnquiry = enquiryList.get(j);
							myWriter.write(selectedCamp.getName() + ",");
							myWriter.write(selectedEnquiry.getUserID() + "," + dateFormat.format(selectedEnquiry.getEnquiryDate()) + "," + selectedEnquiry.getEnquiry());
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
	}
}