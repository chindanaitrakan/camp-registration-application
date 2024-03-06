package enquiry;

import java.util.ArrayList;

/**
 * List of enquiries.
 * @author Trakantannarong Chindanai
 * @version 1.0
 * @since 2023-11-24
 */

public class EnquiryList {
	/**
	 * Array of the enquiries.
	 */
	private ArrayList<Enquiry> EnquiryList;
	
	/**
	 * Constructor of EnquiryList.
	 */
	public EnquiryList() {
		this.EnquiryList = new ArrayList<Enquiry>();
	}

	/**
	 * Accessor of EnquiryList.
	 * @return this EnquiryList's EnquiryList.
	 */
	public ArrayList<Enquiry> getEnquiryList(){
		return EnquiryList;
	}
	
	/**
	 * Add method.
	 * @param Enquiry from user.
	 */
	public void add(Enquiry Enquiry) {
		this.EnquiryList.add(Enquiry);
	}
	
	/**
	 * Get size of EnquiryList.
	 * @return size This Enquiry's size.
	 */
	public int size() {
		return EnquiryList.size();
	}
	
	/**
	 * Get Enquiry object at index i.
	 * @param i index from user.
	 * @return Enquiry at index i of enquiry list.
	 */
	public Enquiry get(int i) {
		return EnquiryList.get(i);
	}
	
	/**
	 * Set the ith Enquiry object to the new Enquiry object.
	 * @param i index from user.
	 * @param Enquiry from user.
	 */
	public void set(int i, Enquiry Enquiry) {
		EnquiryList.set(i, Enquiry);
	}
	
	/**
	 * Remove the Enquiry object from EnquiryList.
	 * @param i index from user.
	 */
	public void remove(int i) {
		EnquiryList.remove(i);
	}
	
	/**
	 * Conversion to string.
	 * @return String of enquiry list.
	 */
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Enquiry Enquiry : EnquiryList) {
	        sb.append(Enquiry.getName()).append(", ");
	    }
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : ""; 
	}
}
