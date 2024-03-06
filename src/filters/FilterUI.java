package filters;

import java.util.Scanner;

import camps.CampList;
import models.User;

/**
 * Class to print the types of filters that can be applied to the camp list.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class FilterUI {
	
	private static CampFilter filter;
	
    /**
     * Print the types of filters that can be applied to the camp list.
     * @param user The user committee that is filtering the camp.
     * @param campList The list of camps that can be accessed by the user.
     * @return campList The camp list to contain the filtered camps.
     */
	public static CampList chooseFilter(User user, CampList campList) {
		
		CampList filteredCampList = new CampList();
		
		//choose the filter for the camps
		System.out.println("Choose the filter");
		System.out.println("[1] StartDate");
		System.out.println("[2] EndDate");
		System.out.println("[3] RegistrationClosingDate");
		System.out.println("[4] Location");
		System.out.println("[5] UserGroup");
		System.out.println("[6] View All");
		
		Scanner sc = new Scanner(System.in);
		
		// get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-6]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

		switch (option){
        
            case 1: filter = new CampStartDateFilter();
            		filteredCampList = filter.filter(user, campList);
            		break;
            case 2: filter = new CampEndDateFilter();
    				filteredCampList = filter.filter(user, campList);
    				break;
	        case 3: filter = new CampRegistrationClosingDateFilter();
	    			filteredCampList = filter.filter(user, campList);
	    			break;
            case 4: filter = new CampLocationFilter();
			   		filteredCampList = filter.filter(user, campList);
			   		break;
            case 5: filter = new CampUserGroupFilter();
			   		filteredCampList = filter.filter(user, campList);
			   		break;
            case 6: filteredCampList = campList;
		}
		return filteredCampList;
	}
}
