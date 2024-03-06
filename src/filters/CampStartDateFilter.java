package filters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import camps.CampList;
import models.User;

/**
 * Class to implement the filtering of camps according to the camp start date.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */


public class CampStartDateFilter implements CampFilter{

    /**
     * Filters the camps according to the camp start date.
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
        Date selectedStartDate = null;
        String datesstr;
        
		//get input start date
        while (selectedStartDate == null) {
        	
            System.out.println("Enter camp start date (DD/MM/YYYY):");
            datesstr = sc.nextLine().trim();
            try {
                selectedStartDate = dateFormat.parse(datesstr);
                // Date is valid and parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. Please use DD/MM/YYYY format.");
            }
        }
		
		//screen the campList
		for(int i=0; i<campList.size(); i++) {
			if(campList.get(i).getStartDate().equals(selectedStartDate)) {
				FilteredCampList.add(campList.get(i));
			}
		}
		
		return FilteredCampList;
	}

}
