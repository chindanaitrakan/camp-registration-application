package datamanagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import suggestion.Suggestion;
import suggestion.SuggestionList;

/**
 * Interface for classes to implement saving and loading of suggestions between excel sheet and ArrayList.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */

public class SuggestionExcelData implements SuggestionDataManager{
	
    /**
     * Saves and update the excel sheet with the modified suggestionList.
     * @param suggestionList The suggestionList that provides the information to be saved into the excel sheet.
     */
	@Override
	public void save(SuggestionList suggestionList) {
		//store the camp information in the last row
        try {
            // Load the Excel file
            Workbook workbook = WorkbookFactory.create(new FileInputStream("src/Data/suggestion_list.xlsx"));
            // The data is in the first sheet (index 0)
            Sheet sheet = workbook.getSheetAt(0);
            
            //map the column name to index
            Map<String, Integer> headers = new HashMap<>();
            //Get the header from file and put in map with Index value
            for (Cell cell : sheet.getRow(0)) {
            headers.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            
            // Clear previous information
            for(int i=1; i<= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // Instantiate all row objects again
            ArrayList<Row> row  = new ArrayList<Row>();
            // Loop all camp information and put them in excel sheet
            for(int i=1; i<suggestionList.size()+1;i++) {
            	row.add(sheet.createRow(i));
            	// Access the cell at the specified column index
	        	Cell cell0 = row.get(i-1).createCell(headers.get("suggestionID"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with the name
	        	cell0.setCellValue(suggestionList.get(i-1).getSuggestionID());
	        	
	            // Access the cell at the specified column index
	        	Cell cell1 = row.get(i-1).createCell(headers.get("userID"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with the dates
	        	cell1.setCellValue(suggestionList.get(i-1).getUserID());
	        	
	            // Access the cell at the specified column index
	        	Cell cell2 = row.get(i-1).createCell(headers.get("campName"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with the dates
	        	cell2.setCellValue(suggestionList.get(i-1).getName());
	        	
	            // Access the cell at the specified column index
	        	Cell cell3 = row.get(i-1).createCell(headers.get("suggestion"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with registrationClosingDate
	        	cell3.setCellValue(suggestionList.get(i-1).getSuggestion());
	        	
	            // Access the cell at the specified column index
	        	Cell cell4 = row.get(i-1).createCell(headers.get("suggestionDate"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with userGroup
	        	cell4.setCellValue(suggestionList.get(i-1).getSuggestionDate());
	        	
	            // Access the cell at the specified column index
	        	Cell cell5 = row.get(i-1).createCell(headers.get("processedStatus"), CellType.BOOLEAN); // Create the cell if it doesn't exist
	        	// Set the value of the cell with location
	        	cell5.setCellValue(suggestionList.get(i-1).getProcessedStatus());
	        	
	            // Access the cell at the specified column index
	        	Cell cell6 = row.get(i-1).createCell(headers.get("approvedOrDeclined"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with campCommitteeSlots
	        	cell6.setCellValue(suggestionList.get(i-1).getApprovedOrDeclined());
            	
            }   
            // Now, you can save the changes back to the Excel file
        	try (FileOutputStream fos = new FileOutputStream("src/Data/suggestion_list.xlsx")) {
        	    workbook.write(fos);
        	}      
            // Close the workbook
            workbook.close();

        } catch (IOException IOE) {
        	System.out.println("No such file");
        }
	}
		

    /**
     * Appends the data extracted from the excel sheet into the ArrayList.
     * @param suggestionList The excel data will be extracted into this suggestionList.
     */
	@Override
	public void load(SuggestionList suggestionList) {
		//initialize everything
		String suggestionID, userID, campName, suggestionQuestion, approvedOrDeclined;
		boolean processedStatus;
		Date suggestionDate;
		
		try {
			//Load the Excel file
			Workbook workbook = WorkbookFactory.create(new FileInputStream("src/Data/suggestion_list.xlsx"));
			// The data is in the first sheet (index 0)
			Sheet sheet = workbook.getSheetAt(0);
			
			//map the column name to index
	        Map<String, Integer> headers = new HashMap<>();
	        //Get the header from file and put in map with Index value
	        for (Cell cell : sheet.getRow(0)) {
	        headers.put(cell.getStringCellValue(), cell.getColumnIndex());
	        }
			
	        // Load excel data into campList
	        int count=0;
			for (Row row : sheet) {
				if(count==0) {
					count++;
					continue;
				}
				suggestionID = row.getCell(headers.get("suggestionID")).getStringCellValue();
				userID = row.getCell(headers.get("userID")).getStringCellValue();
				campName = row.getCell(headers.get("campName")).getStringCellValue();
				suggestionQuestion = row.getCell(headers.get("suggestion")).getStringCellValue();
				suggestionDate = row.getCell(headers.get("suggestionDate")).getDateCellValue(); 
				processedStatus = row.getCell(headers.get("processedStatus")).getBooleanCellValue(); 
				approvedOrDeclined = row.getCell(headers.get("approvedOrDeclined")).getStringCellValue();
                
				suggestionList.add(new Suggestion(suggestionID, userID, campName, suggestionQuestion, suggestionDate, processedStatus, approvedOrDeclined));
			    count++;
			}
			
			
			// Now, you can save the changes back to the Excel file
	    	try (FileOutputStream fos = new FileOutputStream("src/Data/suggestion_list.xlsx")) {
	    	    workbook.write(fos);
	    	}
	   
	        // Close the workbook
	        workbook.close();
	
	    } catch (IOException IOE) {
	    	System.out.println("No such file");
	    }
	}

}
