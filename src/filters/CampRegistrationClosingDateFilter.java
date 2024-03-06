package filters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import camps.CampList;
import models.User;

/**
 * Class to implement the filtering of camps according to the camp registration closing date.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */


public class CampRegistrationClosingDateFilter implements CampFilter{

    /**
     * Filters the camps according to the camp registration closing date.
     * @param user The user that is filtering the camp.
     * @param campList The list of camps that can be accessed by the user.
     * @return campList The filtered camp list.
     */
	@Override
	public CampList filter(User user, CampList campList) {
		//Instantiate new campList
		CampList FilteredCampList  = new CampList();
		
		//declare the input parameters
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date selectedRegistrationDates = null;
        String datesstr;
        
		//get input registration closing date date
        while (selectedRegistrationDates == null) {
        	
            System.out.println("Enter camp registration closing date (DD/MM/YYYY):");
            datesstr = sc.nextLine().trim();
            try {
                selectedRegistrationDates = dateFormat.parse(datesstr);
                // Date is valid and parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
            }
        }
		
		//screen the campList
		for(int i=0; i<campList.size(); i++) {
			if(campList.get(i).getRegistrationClosingDate().equals(selectedRegistrationDates)) {
				FilteredCampList.add(campList.get(i));
			}
		}
		
		return FilteredCampList;
	}

}
