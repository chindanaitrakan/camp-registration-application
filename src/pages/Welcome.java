package pages;

import java.util.Scanner;

/**
 * Welcome page that users see after executing the camp program.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class Welcome extends Page{
	/**
	 * Scanner object for user input.
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Type of user student or staff.
     */
    private String userType;
    /**
     * Constructor for Welcome.
     * @param previousPage The previous page the user was on.
     */
    public Welcome(Page previousPage){
        super(previousPage);
    }
    /**
     * Main executable for the page.
     * @return page The next page to be loaded depending on user type.
     */
    @Override
    public Page startExecution(){
        
        // print menu
        System.out.println("Log in as a:");
        System.out.println("[1] Student");
        System.out.println("[2] Staff");

        // get user type
        System.out.print("Enter your user type(empty input to exit): ");
        this.userType = sc.nextLine().trim();
        System.out.println();

        // if input not blank, loop to ask for valid input
        while (!this.userType.equals("1") && !this.userType.equals("2")) {
            // exit program if empty input
            if(this.userType.isBlank()){
                return new Exit(null);
            }

            System.out.println("Enter a valid user type:");
            this.userType = sc.nextLine().trim();
        }
        
        
        return new Login(this, this.userType);
        
    }

}
