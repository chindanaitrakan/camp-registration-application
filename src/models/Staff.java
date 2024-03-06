package models;

import camputils.CampManager;
import datamanagement.CampDataManager;
import datamanagement.EnquiryDataManager;
import datamanagement.SuggestionDataManager;
import report.ReportGenerator;
import utils.DataViewer;

/**
 * Class for staff.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class Staff extends User {
	
    /**
     * A class to allow staff to make modifications to the camp.
     */
	private CampManager campManager;
    
    /**
     * A class to load and save camp's data.
     */
	private CampDataManager campData;
	
    /**
     * A class to load and save suggestion data.
     */
	private SuggestionDataManager suggestionData;
	
    /**
     * A class to load and save enquiry data.
     */
	private EnquiryDataManager enquiryData;
	
    /**
     * A class to allow the viewing of any data.
     */
	private DataViewer dataviewer;
	
    /**
     * A class to allow Camp Committee to generate report.
     */
    private ReportGenerator generator;
	

	/**
	 * Constructor of staff
     * @param name This user's name
     * @param faculty This user's faculty
     * @param email This user's email
     * @param entryNumber This user's entry number in the excel sheet provided
	 */
	public Staff(String name, String faculty, String email, int entryNumber) {
        super(name, faculty, email, entryNumber);
        this.userType = 2;
    }
	
	
	
    /**
     * Gets the class to access the data of the camps.
     * @return the class that provides access to camps' data.
     */
	public CampDataManager getCampDataManager() {
		return campData;
	}
	
    /**
     * Gets the class to access the data of the suggestions.
     * @return the class that provides access to suggestions' data.
     */
	public SuggestionDataManager getSuggestionDataManager() {
		return suggestionData;
	}
	
    /**
     * Gets the class to access the data of the enquiries.
     * @return the class that provides access to enquiries' data.
     */
	public EnquiryDataManager getEnquiryDataManager() {
		return enquiryData;
	}
	
    /**
     * Gets class to access the viewing functions.
     * @return the class that provides access to viewing functions. 
     */
	public DataViewer getDataViewer() {
		return dataviewer;
	}
	
    /**
     * Gets class to access the functions to generate a report (for Camp Committee).
     * @return the class that provides access to functions for generating a report (for Camp Committee)
     */
	public ReportGenerator getReportGenerator() {
		return this.generator;
	}
	
    /**
     * Gets class to access the functions to make modifications to camp information.
     * @return the class that provides access to functions to make modifications to camp information.
     */
	public CampManager getCampManager() {
		return campManager;
	}
	
    /**
     * Sets the class that provides data of the camps.
     * @param campData A class that allows access to the camps' data.
     */
	public void setCampDataManager(CampDataManager campData) {
		this.campData = campData;
	}
	
    /**
     * Sets the class that provides data of the suggestions.
     * @param suggestionData A class that allows access to the suggestions' data.
     */
	public void setSuggestionDataManager(SuggestionDataManager suggestionData) {
		this.suggestionData = suggestionData;
	}
	
    /**
     * Sets the class that provides data of the enquiries.
     * @param enquiryData A class that allows access to the enquiries' data.
     */
	public void setEnquiryDataManager(EnquiryDataManager enquiryData) {
		this.enquiryData = enquiryData;
	}
	
    /**
     * Sets the class that provides the function to view data.
     * @param dataviewer A class that provides the function to view data.
     */
	public void setDataViewer(DataViewer dataviewer) {
		this.dataviewer = dataviewer;
	}
	
    /**
     * Sets the class that provides the function to generate a report about the camp.
     * @param campData A class that provides the function to generate a report about the camp.
     */
	public void setReportGenerator(ReportGenerator generator) {
		this.generator = generator;
	}
		
    /**
     * Sets the class that provides the function to make modifications to camp information.
     * @param campData A class that provides the function to make modifications to camp information.
     */
	public void setCampManager(CampManager campManager) {
		this.campManager = campManager;
	}


}


