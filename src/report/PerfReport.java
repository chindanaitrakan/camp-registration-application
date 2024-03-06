package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import camps.Camp;
import camps.CampList;
import filters.CampFilter;
import filters.FilterUI;
import models.Staff;
import models.User;
/**
 * Class for the performance report of committee members.
 * @author Sam Ye Zhi
 * @version 1.0
 * @since 2023-11-22
 */
public class PerfReport implements ReportGenerator{
	
	/**
	 * Generates the performance report of committee members in either txt or csv.
	 * @param user The user generating the report.
	 * @param campList The list of camps.
	 */
	@Override
	public void generate(User user, CampList campList) {
		Staff staff = (Staff) user;
		Scanner sc = new Scanner(System.in);
		String filename, filepath;
		CampList filteredCampList;
		
		System.out.println("Enter file format: \n[1] txt \n[2] csv");
        int choice = Integer.parseInt(sc.nextLine().trim());
        while(!(choice==1 || choice ==2)){
            System.out.println("Enter a valid choice:");
            choice = Integer.parseInt(sc.nextLine().trim());
        }
        
        //filter by custom
      	filteredCampList = FilterUI.chooseFilter(staff, campList);
        
        if (choice == 1) {
	        while(true) {
	        	System.out.println("Enter the file name:");
	        	filename = sc.nextLine().trim();
	            filepath = "src/Data/reports/"+filename+".txt";
	        	try {
	  		      File myObj = new File(filepath);
	  		      if (myObj.createNewFile()) {
	  		        System.out.println("File created: " + myObj.getName());
	  		        break;
	  		      } else {
	  		        System.out.println("File already exists.");
	  		      }
	  		    } catch (IOException e) {
	  		      System.out.println("An error occurred.");
	  		      e.printStackTrace();
	  		    }
	        }
			
			try {
				FileWriter myWriter = new FileWriter(filepath);
	  
				myWriter.write("Performance Report of The Camp Committees");
				myWriter.write("\r\n");
				
				ArrayList<String> committeeId =  new ArrayList<String>();
				for(int i=0; i<filteredCampList.size(); i++) {
					Camp selectedCamp = filteredCampList.get(i);
					
					for (String var : selectedCamp.getCommitteeIdList()) {
						if(!committeeId.contains(var)) {
							committeeId.add(var);
						}		  
					}
				}
				Collections.sort(committeeId);
				
				ArrayList<Integer> committeePoints = new ArrayList<Integer>(); 
				
	            try {
	                // Load the Excel file
	            	String path = "src/Data/student_list.xlsx";
	                Workbook workbook = WorkbookFactory.create(new FileInputStream(path));
	                
	                Sheet sheet = workbook.getSheetAt(0);
	                for(int i=0; i<committeeId.size();i++) {
	                	
		                for (Row row : sheet) {
		                    Cell emailCell = row.getCell(1); 
		                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
		                        String email = emailCell.getStringCellValue();
		                        String[] parts = email.split("@");
		                        if (parts.length > 1 && parts[0].equals(committeeId.get(i))) {
		                            
	                                try {
	                                	committeePoints.add((int) row.getCell(4).getNumericCellValue());
	                                } catch (NullPointerException NPE) {
	                                	
	                                	committeePoints.add(0);
	                                }
		                            break; 
		                        }
		                    }
		                }     	
	                }
	                
	                workbook.close();
	            } catch (IOException IOE) {
	            	System.out.println("No such file");
	            }
	
				
				for(int i=0; i<committeeId.size(); i++) {
					
					myWriter.write("["+(i+1)+"]"+" "+committeeId.get(i)+" responsible for ");
					for(int j=0; j<filteredCampList.size(); j++) {
						
						if(filteredCampList.get(j).getCommitteeIdList().contains(committeeId.get(i))) {
							myWriter.write(filteredCampList.get(i).getName()+" ");
						}
					}
					myWriter.write("have "+committeePoints.get(i)+" points");
					myWriter.write("\r\n");
				}
			  
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
			  System.out.println("An error occurred.");
			      e.printStackTrace();
			 }	
        }
			
        
        else if (choice == 2) {
        	while(true) {
        	    System.out.println("Enter the file name:");
        	    filename = sc.nextLine().trim();
        	    filepath = "src/Data/reports/" + filename + ".csv"; 
        	    try {
        	        File myObj = new File(filepath);
        	        if (myObj.createNewFile()) {
        	            System.out.println("File created: " + myObj.getName());
        	            break;
        	        } else {
        	            System.out.println("File already exists.");
        	        }
        	    } catch (IOException e) {
        	        System.out.println("An error occurred.");
        	        e.printStackTrace();
        	    }
        	}


        	try (FileWriter myWriter = new FileWriter(filepath)) {

        	    myWriter.write("Committee ID, Camps, Points\n");

    
				ArrayList<String> committeeId =  new ArrayList<String>();
				for(int i=0; i<filteredCampList.size(); i++) {
					Camp selectedCamp = filteredCampList.get(i);
					
					for (String var : selectedCamp.getCommitteeIdList()) {
						if(!committeeId.contains(var)) {
							committeeId.add(var);
						}		  
					}
				}
				Collections.sort(committeeId);
				
				ArrayList<Integer> committeePoints = new ArrayList<Integer>(); 
		
	            try {
	          
	            	String path = "src/Data/student_list.xlsx";
	                Workbook workbook = WorkbookFactory.create(new FileInputStream(path));
	             
	                Sheet sheet = workbook.getSheetAt(0);
	                for(int i=0; i<committeeId.size();i++) {
	                
		                for (Row row : sheet) {
		                    Cell emailCell = row.getCell(1); 
		                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
		                        String email = emailCell.getStringCellValue();
		                        String[] parts = email.split("@");
		                        if (parts.length > 1 && parts[0].equals(committeeId.get(i))) {
		                            
	                                try {
	                                	committeePoints.add((int) row.getCell(4).getNumericCellValue());
	                                } catch (NullPointerException NPE) {
	                                	
	                                	committeePoints.add(0);
	                                }
		                            break; 
		                        }
		                    }
		                }     	
	                }
	                
	                workbook.close();
	            } catch (IOException IOE) {
	            	System.out.println("No such file");
	            }
	

        	    
        	    for (int i = 0; i < committeeId.size(); i++) {
        	        String committeeCamps = "";
        	        for (int j = 0; j < filteredCampList.size(); j++) {
        	            if (filteredCampList.get(j).getCommitteeIdList().contains(committeeId.get(i))) {
        	                committeeCamps += campList.get(j).getName() + "; "; 
        	            }
        	        }

        	        
        	        if (!committeeCamps.isEmpty()) {
        	            committeeCamps = committeeCamps.substring(0, committeeCamps.length() - 2);
        	        }

        	        
        	        myWriter.write(committeeId.get(i) + "," + "\"" + committeeCamps + "\"," + committeePoints.get(i) + "\n");
        	    }

        	    System.out.println("Successfully wrote to the file.");
        	} catch (IOException e) {
        	    System.out.println("An error occurred.");
        	    e.printStackTrace();
        	}
        }
	}
}
