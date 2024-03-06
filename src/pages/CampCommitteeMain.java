package pages;

import java.util.Scanner;

import camps.CampList;
import camputils.CampViewerCommittee;
import camputils.RegisteredStudentViewer;
import datamanagement.CampExcelData;
import enquiryutils.EnquiryViewerCommittee;
import models.Student;
import report.MembersReport;
import suggestionutils.SuggestionUploader;
import suggestionutils.SuggestionViewerStudent;

/**
 * The class for the Camp Committee Menu that Camp Committee Members can access.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class CampCommitteeMain extends Page {
	/**
	 * Scanner object for taking input.
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * The student accessing CampCommitteeMain.
     */
    Student student;
    /**
     * List of available camps.
     */
    CampList campList;
    /**
     * Constructor of CampCommitteeMain.
     * @param previousPage The previous page.
     * @param student The student accessing CampCommitteeMain.
     */
    public CampCommitteeMain(Page previousPage, Student student){
        super(previousPage);
        this.student = student;
    }

	/**
	 * Displays the camp committee menu.
	 * Provides multiple options for camp committee members.
	 * @return Page The page to the choosen option based on user input.
	 */
    public Page startExecution(){
    	
    	
    	campList = new CampList();
    	student.setCampDataManager(new CampExcelData());
    	student.getCampDataManager().load(campList);
    	
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  Camp Committee Menu                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] View Camps Registered                                 ║");
        System.out.println("║[2] View List of Students in a Camp                       ║");
        System.out.println("║[3] Submit suggestions to camp detail                     ║");
        System.out.println("║[4] View/Edit/Delete Pending Suggestions                  ║");
        System.out.println("║[5] View Enquiries from Attendees                         ║");
        System.out.println("║[6] Generate Report for Camp                              ║");
        System.out.println("║[7] Back to Student Menu                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-7]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            case 1:
            	student.setDataViewer(new CampViewerCommittee());
            	student.getDataViewer().viewData(student, campList);
            	break;
            case 2:
            	student.setDataViewer(new RegisteredStudentViewer());
            	student.getDataViewer().viewData(student, campList);
            	break;
            case 3:
            	student.setMessageUploader(new SuggestionUploader());
            	student.getMessageUploader().submit(student, campList);
            	break;
            case 4:
            	student.setDataViewer(new SuggestionViewerStudent());
            	student.getDataViewer().viewData(student, campList);
            	break;
            case 5:
            	student.setDataViewer(new EnquiryViewerCommittee());
            	student.getDataViewer().viewData(student, campList); 
            	break;
            case 6:
        		student.setReportGenerator(new MembersReport());
        		student.getReportGenerator().generate(student, campList);
        		break;
            case 7:
            	return this.getPreviousPage();
        }

        return this;
    }
}
