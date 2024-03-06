package models;

/**
 * Provides the super class for the other types of Users.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class User {
    /**
     * The name of the User.
     */
    protected String name;
    
    /**
     * The email of the User.
     */
    protected String email;
    
    /**
     * The user ID of the User.
     */
    protected String userID;
    
    /**
     * The user type of the User (can be either a student or a staff).
     */
    protected int userType;

    /**
     * The entry number of the User in the excel sheet provided, this will be used later on for ease of finding.
     */
    protected int entryNumber;
    
    /**
     * The faculty of the User.
     */
    protected String faculty;
    




    /**
     * Constructor of User
     * @param name This user's name
     * @param faculty This user's faculty
     * @param email This user's email
     * @param entryNumber This user's entry number in the excel sheet provided
     */
    public User(String name, String faculty, String email, int entryNumber){
        this.name = name;
        this.faculty = faculty;
        this.email = email;
        this.userID = email.split("@")[0];
        this.entryNumber = entryNumber;
    }
    
    /**
     * Gets user's name
     * @return this user's name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Gets user's email
     * @return this user's email
     */
    public String getEmail(){
        return this.email;
    }
    
    /**
     * Gets user's ID
     * @return this user's ID
     */
    public String getUserID(){
        return this.userID;
    }
    
    /**
     * Gets type of user
     * @return this user's type
     */
    public int getType() {
        return this.userType;
    }
    
    /**
     * Gets entry number in excel sheet
     * @return this user's entry number in the excel sheet provided
     */
    public int getEntryNumber() {
        return this.entryNumber;
    }
    
    /**
     * Gets faculty of users
     * @return this user's faculty
     */
    public String getFaculty() { 
    	return this.faculty;
    }
}


