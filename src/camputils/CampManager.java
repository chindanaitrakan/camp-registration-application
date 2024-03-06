package camputils;

import camps.CampList;
import models.User;

/**
 * Abstract class which can edit the camps information.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */
public abstract class CampManager{
	
	/**
	 * A camp list before editing.
	 */
	protected CampList campList;
	
	/**
	 * User who edit the information.
	 */
	protected User user;
	
	/**
	 * Constructor of campManager
	 * @param user This CampManager's user.
	 * @param campList This CampManager's campList.
	 */
	public CampManager(User user, CampList campList){
		this.campList = campList;
		this.user = user;
	}
	
	/**
	 * Abstract method to be overidden and to change the camp information.
	 */
	public abstract void changeCampInfo();
	
}
