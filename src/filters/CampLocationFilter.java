package filters;

import java.util.Scanner;

import camps.CampList;
import models.User;

/**
 * Class to implement the filtering of camps according to the location.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */


public class CampLocationFilter implements CampFilter{

    /**
     * Filters the camps according to the location.
     * @param user The user that is filtering the camp.
     * @param campList The list of camps that can be accessed by the user.
     * @return campList The filtered camp list.
     */
	@Override
	public CampList filter(User user, CampList campList) {		
		//Instantiate new campList
		CampList FilteredCampList  = new CampList();
		
		Scanner sc = new Scanner(System.in);
		//get location
        System.out.print("Enter camp location:");
        String location = sc.nextLine().trim();
        
        //screen the campList
  		for(int i=0; i<campList.size(); i++) {
  			if(campList.get(i).getLocation().equals(location)) {
  				FilteredCampList.add(campList.get(i));
  			}
  		}
      	return FilteredCampList;
	}

}
