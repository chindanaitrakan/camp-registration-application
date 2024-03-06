package camputils;

import camps.CampList;

/**
 * Search the Camp in CampList.
 * Return the index of the camp.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public class CampSearcher {
	
	/**
	 * search the Camp and return the index of the Camp in CampList.
	 * @param name
	 * @param campList
	 * @return campIndex The index of the Camp in CampList.
	 */
	public static int searchCamp(String name, CampList campList) {

		int campIndex=-1;
		for(int i=0; i<campList.size(); i++) {
			if(campList.get(i).getName().equals(name)) {
				campIndex = i;
				break;
			}
		}
		return campIndex;
	}

}
