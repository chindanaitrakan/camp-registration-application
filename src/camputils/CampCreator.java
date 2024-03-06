package camputils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import datamanagement.CampDataManager;
import datamanagement.CampExcelData;
import models.User;

/**
 * Creates a camp which will be added to camp list.
 * Update the new camp list to the excel spread sheet.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class CampCreator  extends CampManager{
	
	/**
	 * Constructor of campCreator.
	 * @param user The user who create a camp.
	 * @param campList The original campList.
	 */
	public CampCreator(User user, CampList campList) {
		super(user, campList);
	}
	
	/**
	 * Camp data manager for saving the data.
	 */
	private CampDataManager campData;
	
	/**
	 * name of new camp.
	 */
	private String name;
	
	/**
	 * startDate of new camp.
	 */
	private Date startDate;
	
	/**
	 * endDate of new camp.
	 */
	private Date endDate;
	
	/**
	 * registrationClosingDate of new camp.
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
	 * Create a new camp from the details given by user
	 */
	public void changeCampInfo() {
		
		Scanner sc = new Scanner(System.in);
		// get all camp information
        // get campName
        System.out.print("Enter camp name:");
        name = sc.nextLine().trim();
        
        //get camp dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        String datesstr;
        
        //start date
        while (startDate == null) {
            System.out.println("Enter camp start date (DD/MM/YYYY):");
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
        //end date
        Date endDate = null;
        while (endDate == null) {
            System.out.println("Enter camp end date (DD/MM/YYYY):");
            datesstr = sc.nextLine().trim();

            try {
                endDate = dateFormat.parse(datesstr);

                // Date is valid and parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
            }
            if(endDate.before(startDate)) {
            	System.out.println("End date should come after start date");
            	endDate = null;
            }   
        }
        //get camp registration closing dates
        String regCloseDateStr;
        Date registrationClosingDate = null;

        while (registrationClosingDate == null) {
            System.out.println("Enter camp registration closing date (DD/MM/YYYY):");
            regCloseDateStr = sc.nextLine().trim();

            try {
                registrationClosingDate = dateFormat.parse(regCloseDateStr);

                // Date is valid and parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
            }
        }
        //get user group 
        System.out.print("Enter user group this camp is open to:");
        userGroup = sc.nextLine().trim().toUpperCase();
        
        //get location
        System.out.print("Enter camp location:");
        location = sc.nextLine().trim();
        
        //get camp committee slots
        System.out.print("Enter camp committee slots:");
        String slotsstr = sc.nextLine().trim();
        // loop to ask for valid input
        while(!(slotsstr.matches("^[1-9]|10$"))){
            System.out.println("Enter a valid slots (The maximum slot is 10):");
            slotsstr = sc.nextLine().trim();
        }
        campCommitteeSlots = Integer.parseInt(slotsstr);
        
        //get total slots
        System.out.print("Enter camp total slots:");
        String totalslotsstr = sc.nextLine().trim();
        // loop to ask for valid input
        // receive maximum of 100
        while(!(totalslotsstr.matches("\\b(?:[1-9]|[1-9][0-9]|100)\\b")) || (Integer.parseInt(totalslotsstr)<=campCommitteeSlots)){
        	if(Integer.parseInt(totalslotsstr)<=campCommitteeSlots)
        		System.out.println("Enter a valid slots (The total slots must be more than committee slots):");
        	else if(!(totalslotsstr.matches("\\b(?:[1-9]|[1-9][0-9]|100)\\b"))) 
        		System.out.println("Enter a valid slots (The maximum total slots is 100):");
            totalslotsstr = sc.nextLine().trim();
        }
        totalSlots = Integer.parseInt(totalslotsstr);
       
        //get camp description
        System.out.print("Enter camp description:");
        description = sc.nextLine().trim();
        
        //get id of staff in charge
        staffId = this.user.getUserID();
        
        //get camp visibility
        String visible;
        System.out.print("Enter new camp visibility (on/off):");
        do {
        	visible = sc.nextLine().trim();
        	if(!(visible.equals("on") || (visible.equals("off"))))
        		System.out.print("Please enter valid visibility:");
        }while(!(visible.equals("on") || (visible.equals("off"))));
        if(visible.equals("on")) visibility = true;
        else visibility = false;
        
        //empty initial student and committee list
        studentIdList = new ArrayList<String>();
        committeeIdList = new ArrayList<String>();
        withdrawIdList = new ArrayList<String>();
        
        //Instantiate new camp object and add to campList
        campList.add(new Camp(name, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, 
        		campCommitteeSlots, description, staffId, visibility, studentIdList, committeeIdList, withdrawIdList));
        campList.sort();
        campData = new CampExcelData();
		campData.save(campList);
        System.out.println("Successfully create camp.");
	}

}
