package camps;

import java.util.ArrayList;
import java.util.Collections;

/**
 * List of camps.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */

public class CampList {
	/**
	 * Array of the camps.
	 */
	private ArrayList<Camp> campList;
	
	/**
	 * Constructor of CampList.
	 */
	public CampList() {
		this.campList = new ArrayList<Camp>();
	}
	/**
	 * Accessor of CampList.
	 * @return this CampList's campList.
	 */
	public ArrayList<Camp> getCampList(){
		return campList;
	}
	
	/**
	 * Adds method.
	 * @param Camp The Camp added from user.
	 */
	public void add(Camp camp) {
		this.campList.add(camp);
	}
	
	/**
	 * Gets size of CampList.
	 * @return size of CampList.
	 */
	public int size() {
		return campList.size();
	}
	
	/**
	 * Gets camp object at index i.
	 * @param i Index from user.
	 * @return Camp at index i.
	 */
	public Camp get(int i) {
		return campList.get(i);
	}
	
	/**
	 * Sets the ith camp object to the new camp object.
	 * @param i Index from user.
	 * @param Camp 
	 */
	public void set(int i, Camp camp) {
		campList.set(i, camp);
	}
	
	/**
	 * Removes the Camp from CampList.
	 * @param i Index from user.
	 */
	public void remove(int i) {
		campList.remove(i);
	}
	
	/**
	 * Sorts the CampList by Camp's name.
	 */
	public void sort() {
		Collections.sort(campList, Camp.CampNameComparator);
	}
	
	/**
	 * Conversion to string
	 * @return String of CampList.
	 */
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Camp camp : campList) {
	        sb.append(camp.getName()).append(", ");
	    }
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : ""; 
	}
}
