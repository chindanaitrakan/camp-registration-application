package filters;

import java.util.Scanner;

import camps.CampList;
import models.User;

/**
 * Class to implement the filtering of camps according to the faculty.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class CampUserGroupFilter implements CampFilter{

    /**
     * Filters the camps according to the faculty.
     * @param user The user committee that is filtering the camp.
     * @param campList The list of camps that can be accessed by the user.
     * @return campList The filtered camp list.
     */
	@Override
	public CampList filter(User user, CampList campList) {
        //Instantiate new campList
		CampList FilteredCampList  = new CampList();
		
		Scanner sc = new Scanner(System.in);
		//get user group 
        System.out.print("Enter user group this camp is open to:");
        String userGroup = sc.nextLine().trim().toUpperCase();
        
        //screen the campList
  		for(int i=0; i<campList.size(); i++) {
  			if(campList.get(i).getUserGroup().equals(userGroup) || campList.get(i).getUserGroup().equals("ALL")) {
  				FilteredCampList.add(campList.get(i));
  			}
  		}
      		
      	return FilteredCampList;      
	}

}
