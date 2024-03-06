package camputils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import camps.CampList;
import datamanagement.CampDataManager;
import datamanagement.CampExcelData;
import models.Staff;
import models.User;

/**
 * Edit a camp which will be added to camp list.
 * Update the new camp list to the excel spread sheet.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class CampEditor extends CampManager{
	
	/**
	 * Constructor of CampEditor.
	 * @param user The user who edit the camp.
	 * @param campList The original campList.
	 */
	public CampEditor(User user, CampList campList) {
		super(user, campList);
	}
	
	/**
	 * Camp data manager for saving the data.
	 */
	private CampDataManager campData;
	
	/**
	 * Name of new camp.
	 */
	private String name;
	
	/**
	 * StartDate of new camp.
	 */
	private Date startDate;
	
	/**
	 * EndDate of new camp.
	 */
	private Date endDate;
	
	/**
	 * RegistrationClosingDate of new camp.
	 */
	private Date registrationClosingDate;
	
	/**
	 * School where the camp is open for of new camp.
	 */
	private String userGroup;
	
	/**
	 * Location of new camp.
	 */
	private String location;
	
	/**
	 * Total slots of new camp.
	 */
	private int totalSlots;
	
	/**
	 * Slots for camp committee (max10) of new camp.
	 */
	private int campCommitteeSlots;
	
	/**
	 * Description of new camp
	 */
	private String description; 
	
	/**
	 * Id staff in charge of new camp.
	 */
	private String staffId;
	
	/**
	 * Visibility of new camp
	 */
	private Boolean visibility;
	
	/**
	 * List of registered student of new camp.
	 */
	private static ArrayList<String> studentIdList;
	
	/**
	 * List of registered committee members of new camp.
	 */
	private static ArrayList<String> committeeIdList;
	
	/**
	 * List of previous student who withdraw from new camp.
	 */
	private static ArrayList<String> withdrawIdList;

	@Override
	/**
	 * Choose the details for editing and update the camp list accordingly
	 */
	public void changeCampInfo() {
		Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Input the edited camp
		System.out.print("Enter camp name:");
		String searchedName = sc.nextLine().trim();
		//search for the camp name
		int campIndex = CampSearcher.searchCamp(searchedName, campList);
		// If camp is not found
		if(campIndex==-1) {
			System.out.println("Camp is not found");
		}
		else if( (campIndex!=-1) && !(campList.get(campIndex).getStaffId().equals(user.getUserID()))) {
			System.out.println("You are not the owner of the camp. Permission Denied!");
		}
		// If camp is found
		else { 
			System.out.println("Camp is found");
			while(true) {
		        System.out.println("╔════════════════════════════════════════╗");
		        System.out.println("║         Edit Camp Information          ║");
		        System.out.println("╠════════════════════════════════════════╣");
		        System.out.println("║[1] Edit Camp Name                      ║");
		        System.out.println("║[2] Edit Camp Dates                     ║");
		        System.out.println("║[3] Edit Registration Closing Date      ║");
		        System.out.println("║[4] Edit User Group Camp is Open To     ║");
		        System.out.println("║[5] Edit Camp Location                  ║");
		        System.out.println("║[6] Edit Total Slots                    ║");
		        System.out.println("║[7] Edit Camp Committee Slot            ║");
		        System.out.println("║[8] Edit Camp Description               ║");
		        System.out.println("║[9] Edit Camp Visibility                ║");
		        System.out.println("║[10] Save Changes and Back              ║");
		        System.out.println("╚════════════════════════════════════════╝");
		        
		        String optionstr = sc.nextLine().trim();
		        System.out.println();
		        // get the field that is to be edited
		        while(!(optionstr.matches("^[1-9]|10$"))){
		            System.out.println("Enter a valid option:");
		            optionstr = sc.nextLine().trim();
		        }

		        int option = Integer.parseInt(optionstr);
		        if (option == 10) {
	            	campList.sort();
	        		campData = new CampExcelData();
	        		campData.save(campList);
	        		System.out.println("Successfully edit the camp");
	        		break;
		        }
		        switch (option){

		            // SetPassword Page
		            case 1:
				        System.out.print("Enter camp new name:");
				        name = sc.nextLine().trim();
				        // set the new name to the edited camp
				        campList.get(campIndex).setName(name);
				        System.out.printf("Camp name changed to %s.\n", name);
				        break;
		            case 2:
				        //get camp dates    
				        String datesstr;
				        Date startDate = null;

				        while (startDate == null) {
				            System.out.println("Enter new camp date (DD/MM/YYYY):");
				            datesstr = sc.nextLine().trim();

				            try {
				                startDate = dateFormat.parse(datesstr);

				                // Date is valid and parsed successfully
				            } catch (java.text.ParseException e) {
				                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
				            }
				            if(startDate.before(new Date())) {
				            	System.out.println("Start date should come after current date");
				            	startDate = null;
				            }  
				        }
				        // set the new dates to the edited camp
				        campList.get(campIndex).setStartDate(startDate);
				        
				        Date endDate = null;

				        while (endDate == null) {
				            System.out.println("Enter new camp date (DD/MM/YYYY):");
				            datesstr = sc.nextLine().trim();

				            try {
				                endDate = dateFormat.parse(datesstr);

				                // Date is valid and parsed successfully
				            } catch (java.text.ParseException e) {
				                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
				            }
				            if(endDate.before(campList.get(campIndex).getStartDate())) {
				            	System.out.println("End date should come after start date");
				            	endDate = null;
				            } 
				        }
				        // set the new dates to the edited camp
				        campList.get(campIndex).setEndDate(endDate);
		            	break;
		            case 3:
				        //get camp registration closing dates
		    	        String regCloseDateStr;
		    	        Date registrationClosingDate = null;

		    	        while (registrationClosingDate == null) {
		    	            System.out.println("Enter new camp registration closing date (DD/MM/YYYY):");
		    	            regCloseDateStr = sc.nextLine().trim();

		    	            try {
		    	                registrationClosingDate = dateFormat.parse(regCloseDateStr);

		    	                // Date is valid and parsed successfully
		    	            } catch (java.text.ParseException e) {
		    	                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
		    	            }
		    	            if(registrationClosingDate.after(campList.get(campIndex).getStartDate())) {
		    	            	System.out.println("Registration closing date should come before start date");
		    	            	registrationClosingDate = null;
		    	            }   
		    	        }
				        // set the new registration closing date to the edited camp
				        campList.get(campIndex).setRegistrationClosingDate(registrationClosingDate);
		            	break;
		            case 4:
				        //get user group 
				        System.out.print("Enter new user group this camp is open to:");
				        userGroup = sc.nextLine().trim();
				        // set the new user group to the edited camp
				        campList.get(campIndex).setUserGroup(userGroup);
		            	break;
		            case 5:
				        //get location
				        System.out.print("Enter new camp location:");
				        location = sc.nextLine().trim();
				        // set the new location to the edited camp
				        campList.get(campIndex).setLocation(location);
		            	break;
		            case 6:
		            	//get total slots
		                System.out.print("Enter camp total slots:");
		                String totalslotsstr = sc.nextLine().trim();
		                // loop to ask for valid input
		                // receive maximum of 9999
		                // Total slot must be more than the camp committee slots
		                while(!(totalslotsstr.matches("^[1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]$")) || (Integer.parseInt(totalslotsstr)<=campList.get(campIndex).getCampCommitteeSlots())){
		                	if(Integer.parseInt(totalslotsstr)<=campCommitteeSlots)
		                		System.out.println("Enter a valid slots (The total slots must be more than committee slots):");
		                	else if(!(totalslotsstr.matches("\\b(?:[1-9]|[1-9][0-9]|100)\\b"))) 
		                		System.out.println("Enter a valid slots (The maximum total slots is 100):");
		                    totalslotsstr = sc.nextLine().trim();
		                }
		                totalSlots = Integer.parseInt(totalslotsstr);
				        // set the new totalSlots to the edited camp
				        campList.get(campIndex).setTotalSlots(totalSlots);
		            	break;
		            case 7:
				        //get camp committee slots
				        System.out.print("Enter new camp committee slots:");
				        String slotsstr = sc.nextLine().trim();
				        // loop to ask for valid input
				        while(!(slotsstr.matches("^[1-9]|10$")) || Integer.parseInt(slotsstr)>campList.get(campIndex).getTotalSlots()){
				            System.out.println("Enter a valid slots (The maximum slot is 10 and the committee slots must less than total slots):");
				            slotsstr = sc.nextLine().trim();
				        }
				        campCommitteeSlots = Integer.parseInt(slotsstr);
				        // set the new camp committee slots to the edited camp
				        campList.get(campIndex).setCampCommitteeSlots(campCommitteeSlots);
		            	break;
		            case 8:
				        //get camp description
				        System.out.print("Enter new camp description:");
				        description = sc.nextLine().trim();
				        // set the new description to the edited camp
				        campList.get(campIndex).setDescription(description);
		            	break;      	
		            case 9:
				        //get camp visibility
				        String visible;
				        System.out.print("Enter new camp visibility (on/off):");
				        //If there are student/committee registered, the camp visibility cannot be edited
						if(campList.get(campIndex).getStudentIdList().size()!=0 || campList.get(campIndex).getCommitteeIdList().size()!=0) {
							System.out.println("The visibility cannot be toggled");
						}
						else {
							do {
					        	visible = sc.nextLine().trim();
					        	if(!(visible.equals("on") || (visible.equals("off"))))
					        		System.out.print("Please enter valid visibility:");
					        }while(!(visible.equals("on") || (visible.equals("off"))));
					        if(visible.equals("on")) visibility = true;
					        else visibility = false;
					        // set the new visibility to the edited camp
					        campList.get(campIndex).setVisibility(visibility);
						}
						break;
		        }
			}
			
		}
	}
}
