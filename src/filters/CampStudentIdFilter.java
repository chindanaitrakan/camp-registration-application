package filters;

import camps.CampList;
import models.User;

/**
 * Class to implement the filtering of camps according to the userID.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class CampStudentIdFilter implements CampFilter{

    /**
     * Filters the camps that contains this userID in the studentIdList.
     * @param user The user committee that is filtering the camp.
     * @param campList The list of camps that can be accessed by the user.
     * @return campList The filtered camp list.
     */
	@Override
	public CampList filter(User user, CampList campList) {
		//Instantiate new campList
		CampList FilteredCampList  = new CampList();
		
        //screen the campList
  		for(int i=0; i<campList.size(); i++) {
  			if(campList.get(i).getStudentIdList().contains(user.getUserID())) {
  				FilteredCampList.add(campList.get(i));
  			}
  		}
      	return FilteredCampList;	
	}

}
