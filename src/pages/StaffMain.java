package pages;

import java.util.Scanner;

import camps.CampList;
import camputils.*;
import datamanagement.CampExcelData;
import enquiryutils.EnquiryViewerStaff;
import models.Staff;
import report.EnquiryReport;
import report.MembersReport;
import report.PerfReport;
import suggestionutils.SuggestionViewerStaff;
import utils.*;
/**
 * Menu for staff users.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class StaffMain extends Page{
	/** 
	 * Scanner object for user input.
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Staff that logged in.
     */
    private Staff staff;
    /**
     * List of camps.
     */
    private CampList campList;
    
    /**
     * Constructor of StaffMain.
     * @param previousPage The previous page.
     * @param staff The staff that logged in.
     */
    public StaffMain(Page previousPage, Staff staff){
        super(previousPage);
        this.staff = staff;
    }    
    /**
     * Main executable of StaffMain.
     * Menu for staff to choose their options.
     * @return Page The page of the staff's choice.
     */
    public Page startExecution(){
    	//load campList and firstly create bare camplist
    	campList = new CampList();
    	staff.setCampDataManager(new CampExcelData());	
    	staff.getCampDataManager().load(campList);
    	
    	//print UI
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                        Staff Menu                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] Create Camp                                           ║");
        System.out.println("║[3] Edit Camp                                             ║");
        System.out.println("║[4] Delete Camp                                           ║");
        System.out.println("║[5] Toggle Visibility of Camp                             ║");
        System.out.println("║[6] View All Camps                                        ║");
        System.out.println("║[7] View Camps (created by this user)                     ║");
        System.out.println("║[8] View List of Students in a Camp                       ║");
        System.out.println("║[9] View Enquiries from Students                          ║");
        System.out.println("║[10] View/Approve Suggestions to Changes to Camp Details  ║");
        System.out.println("║[11] Generate Report for Camp                             ║");
        System.out.println("║[12] Generate Performance Report of Camp Commitee Members ║");
        System.out.println("║[13] Generate Enquiry Report                              ║");
        System.out.println("║[14] Log-out                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while (!(optionstr.matches("^(1[0-4]|[1-9])$"))) {
            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            // SetPassword Page
            case 1:
                return new SetPassword(this, this.staff.getEntryNumber(), "2", false);
            case 2:
            	staff.setCampManager(new CampCreator(staff, campList));
            	staff.getCampManager().changeCampInfo();
            	break;
            case 3:
            	staff.setCampManager(new CampEditor(staff, campList));
            	staff.getCampManager().changeCampInfo();
            	break;
            case 4:
            	staff.setCampManager(new CampDeletor(staff, campList));
            	staff.getCampManager().changeCampInfo();
            	break;
            case 5:
            	staff.setCampManager(new CampToggler(staff, campList));
            	staff.getCampManager().changeCampInfo();
            	break;
            case 6:
            	staff.setDataViewer(new CampViewerStaff());
            	staff.getDataViewer().viewData(staff, campList);
            	break;
            case 7:
            	staff.setDataViewer(new CampViewerStaffOwner());
            	staff.getDataViewer().viewData(staff, campList);
            	break;
            case 8:
            	staff.setDataViewer(new RegisteredStudentViewer());
            	staff.getDataViewer().viewData(staff, campList);
            	break;
            case 9:
            	staff.setDataViewer(new EnquiryViewerStaff());
            	staff.getDataViewer().viewData(staff, campList);
            	break;    
            case 10:
            	staff.setDataViewer(new SuggestionViewerStaff());
            	staff.getDataViewer().viewData(staff, campList);
            	break;
            case 11: 
            	staff.setReportGenerator(new MembersReport());
            	staff.getReportGenerator().generate(staff, campList);
            	break;
            case 12:
            	staff.setReportGenerator(new PerfReport());
            	staff.getReportGenerator().generate(staff, campList);
            	break;  
            case 13:
            	staff.setReportGenerator(new EnquiryReport());
            	staff.getReportGenerator().generate(staff, campList);
            	break;
            case 14:
            	return this.getPreviousPage().getPreviousPage();
        }
        return this;
    }
}

