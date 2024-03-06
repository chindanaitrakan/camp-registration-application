package pages;

import java.util.Scanner;

import camps.CampList;
import camputils.CampRegisterManager;
import camputils.CampViewerStudent;
import camputils.CampViewerStudentRegistered;
import camputils.CampWithdrawManager;
import datamanagement.CampExcelData;
import enquiryutils.EnquiryUploader;
import enquiryutils.EnquiryViewerStudent;
import models.Student;
import models.User;
/**
 * Menu for student users.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class StudentMain extends Page{
	/**
	 * Scanner object for user input.
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Student that logged in.
     */
    private Student student;
    /**
     * List of camps.
     */
    private CampList campList;
   
    /**
     * Constructor of StudentMain.
     * @param previousPage The previous page.
     * @param student The student that has logged in.
     */
    public StudentMain(Page previousPage, Student student){
        super(previousPage);
        this.student = student;
    }

    /**
     * Main executable of the page.
     * Menu for student to choose their options.
     * @return Page The page of the student's choice.
     */
    public Page startExecution(){
    	//load campList
    	campList = new CampList();
    	student.setCampDataManager(new CampExcelData());
    	student.getCampDataManager().load(campList);
    	student.setIsCampCommittee(StudentMain.getCommitteeStatus(student, campList));
    	
    	//print UI
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                      Student Menu                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View All Camps                                        ║");
        System.out.println("║[3] Register Camp                                         ║");
        System.out.println("║[4] Submit Enquiries regarding a Camp                     ║");
        System.out.println("║[5] View/Edit/Delete Pending Enquiries                    ║");
        System.out.println("║[6] View Camps Registered                                 ║");
        System.out.println("║[7] Withdraw from Camp                                    ║");
        System.out.println("║[8] Enter Camp Committee Mode                             ║");
        System.out.println("║[9] Log-out                                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-9]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            // SetPassword Page
            case 1:
                return new SetPassword(this, this.student.getEntryNumber(), "1", false);
            case 2: 
            	student.setDataViewer(new CampViewerStudent());
            	student.getDataViewer().viewData(student, campList);
            	break;
            case 3:
            	student.setEnrollmentManager(new CampRegisterManager());
            	student.getEnrollmentManager().process(student, campList);
            	break;
            case 4:
            	student.setMessageUploader(new EnquiryUploader());
            	student.getMessageUploader().submit(student, campList);
            	break;
            case 5: 
            	student.setDataViewer(new EnquiryViewerStudent());
            	student.getDataViewer().viewData(student, campList);
            	break;
            case 6:
            	student.setDataViewer(new CampViewerStudentRegistered());
            	student.getDataViewer().viewData(student, campList);
            	break;    
            case 7: 
            	student.setEnrollmentManager(new CampWithdrawManager());
            	student.getEnrollmentManager().process(student, campList);
            	break;
            case 8:
            	if(student.getIsCampCommittee()==true) return new CampCommitteeMain(this, this.student);
            	else{
            		System.out.println("You are not a camp committee!");
            		break;
            	}
            case 9:
            	return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }
    
    private static boolean getCommitteeStatus(User user, CampList campList) {
    	
    	String studentId = user.getUserID();
    	
    	for (int i = 0; i < campList.size(); i++) {
			//check if the student is in the camp committee
			if (campList.get(i).getCommitteeIdList().contains(studentId))
				return true;		
		}
    	return false;
    }
}
