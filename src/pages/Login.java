package pages;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

import models.Staff;
import models.Student;
import utils.CheckPassword;
import utils.HashPassword;

/**
 * Class for different kind of users to login.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class Login extends Page {
	/**
	 * Scanner object for user input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Type of user student or staff.
     */
    private String userType;
    /**
     * User ID of user.
     */
    private String userID;
    /**
     * Password of the user ID input.
     */
    private String userPass;
    /**
     * Integer indicating whether user credentials matches those in the excel sheet.
     */
    private int userFound;
    
   /**
    * User name from excel sheet.
    */
    private static String name;
    /**
     * User faculty from excel sheet.
     */
    private static String faculty;
    /**
     * User email address from excel sheet.
     */
    private static String email;
    /**
     * User committee points for camp committee members.
     */
    private int committeePoints;
    /**
     * Row number where user ID is found.
     */
    private int entryNumber;
    
    /**
     * Constructor for Login class.
     * @param previousPage The previous page of the menu.
     * @param userType Whether the user is a student or staff.
     */
    public Login(Page previousPage, String userType){
        super(previousPage);
        this.userType = userType;
    }

   /**
    * Main executable for login page.
    * @return Page The page for student or staff depending on user type.
    */
    public Page startExecution(){
        System.out.println("Leave any field empty to return to user selection");

        do{	
        	//Reinitialise userFound variable
        	userFound = 0;
        	if (userType.equals("1")) { 
        		System.out.println("Student Login");
        	} else {
        			System.out.println("Staff Login");
        	}
            // get userID
            System.out.print("Enter UserID:");
            this.userID = sc.nextLine().trim().toUpperCase();

            // get userPass
            System.out.print("Enter password:");
            this.userPass = sc.nextLine();
            
            // Finding student in the Excel file
            try {
                // Load the Excel file
            	String filepath = userType.equals("1") ? "src/Data/student_list.xlsx" : "src/Data/staff_list.xlsx";
                Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath));
                // The data is in the first sheet (index 0)
                Sheet sheet = workbook.getSheetAt(0);
                // Iterate through rows to find the user
                entryNumber = 0;
                for (Row row : sheet) {
                    Cell emailCell = row.getCell(1); // Assuming email is in the first column
                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
                        email = emailCell.getStringCellValue();
                        String[] parts = email.split("@");
                        if (parts.length > 1 && parts[0].equals(userID)) {
                            // User found
                            name = row.getCell(0).getStringCellValue(); // Name in the first column
                            faculty = row.getCell(2).getStringCellValue();
                            if (userType.equals("1")) {
                                try {
                                	committeePoints = (int) row.getCell(4).getNumericCellValue();
                                } catch (NullPointerException NPE) {
                                	//student is not a committee
                                	committeePoints = 0;
                                }
                            }
                            // Faculty in the third column
                            userFound = 1;
                            break; // Exit the loop when the user is found
                        }
                    }
                    entryNumber += 1;
                }

                // Close the workbook
                workbook.close();

                // If user is not found
                if (userFound == 0) {
                	System.out.println("User not found.");
                	continue;
                } else {
                	//If user is found
                	int checkPassword;
                	if (userType.equals("1")) {
                    	checkPassword = CheckPassword.checkPasswordStudent(entryNumber, HashPassword.hashPassword(userPass));
        				if (checkPassword == 2) {
                    		System.out.println("User login succussful");
                            System.out.println("Student : " + name + " (" + faculty + ")");
                            System.out.println("It is your first time logging in, you are required to change your password.");
                            Student student = new Student(name, faculty, email, entryNumber, committeePoints);
                            return new SetPassword(this, student.getEntryNumber(), "1", true);
                        } else if (checkPassword == 1) {
                    		System.out.println("User login succussful");
                            System.out.println("Student : " + name + " (" + faculty + ")");
                            Student student = new Student(name, faculty, email, entryNumber, committeePoints);
                            return new StudentMain(this, student);
                        } else if (checkPassword == 0) {
                    		System.out.println("Wrong password.");
                        } else {
                    		System.out.println("Error, check input file");
                        }
                	} else {
                    	checkPassword = CheckPassword.checkPasswordStaff(entryNumber, HashPassword.hashPassword(userPass));
        				if (checkPassword == 2) {
                    		System.out.println("User login succussful");
                            System.out.println("Staff : " + name + " (" + faculty + ")");
                            System.out.println("It is your first time logging in, you are required to change your password.");
                            Staff staff = new Staff(name, faculty, email, entryNumber);
                            return new SetPassword(this, staff.getEntryNumber(), "2", true);
                        } else if (checkPassword == 1) {
                    		System.out.println("User login succussful");
                            System.out.println("Staff : " + name + " (" + faculty + ")");
                            Staff staff = new Staff(name, faculty, email, entryNumber);
                            return new StaffMain(this, staff);
                        } else if (checkPassword == 0) {
                    		System.out.println("Wrong password.");
                        } else {
                    		System.out.println("Error, check input file");
                        }
                	}
                }


            } catch (IOException IOE) {
            	System.out.println("No such file");
            }

            // Redirect to staff main
            
            if(!(this.userPass.isBlank() || this.userID.isBlank())){
                System.out.println("Re-attempting login.");
            }

        } while(!(this.userPass.isBlank() || this.userID.isBlank()));
        return this.getPreviousPage();
    }
}
