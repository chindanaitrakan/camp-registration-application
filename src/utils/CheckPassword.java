package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * Allows program to authenticate password for a user.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class CheckPassword {

    /**
     * Method to authenticate student.
     * @param entryNumber The index of the student in the excel sheet.
     * @param userPass The student's input for password.
     * @return Whether the student passed authentication.
     */
    public static int checkPasswordStudent(int entryNumber, String userPass){
        
        try {
        	Workbook workbook = WorkbookFactory.create(new FileInputStream("src/Data/student_list.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            String password = "";
            
            try {
            	password = sheet.getRow(entryNumber).getCell(3).getStringCellValue();
            } catch (NullPointerException NPE) {
            	password = null;
            }
    		if (password == null) {
            	if (userPass.equals(HashPassword.hashPassword("password"))) {
            		return 2;
            	} else {
            		return 0;
            	}
            } else {
            	if (userPass.equals(password)) {
            		return 1;
            	} else {
            		return 0;
            	}
            }
        } catch (IOException IOE) {
        	System.out.println("No such file");
        	return -1;
        }
    }

    /**
     * Method to authenticate staff.
     * @param entryNumber The index of the staff in the excel sheet.
     * @param userPass The staff's input for password.
     * @return Whether the staff passed authentication.
     */
    public static int checkPasswordStaff(int entryNumber, String userPass){
        
        try {
        	Workbook workbook = WorkbookFactory.create(new FileInputStream("src/Data/staff_list.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            String password = "";
            
            try {
            	password = sheet.getRow(entryNumber).getCell(3).getStringCellValue();
            } catch (NullPointerException NPE) {
            	password = null;
            }
    		if (password == null) {
            	if (userPass.equals(HashPassword.hashPassword("password"))) {
            		return 2;
            	} else {
            		return 0;
            	}
            } else {
            	if (userPass.equals(password)) {
            		return 1;
            	} else {
            		return 0;
            	}
            }
        } catch (IOException IOE) {
        	System.out.println("No such file");
        	return -1;
        }
  
    }

}
