package NewPackage;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	XSSFWorkbook wb;
  	//Creating a constructor of the same class that would load excel file using the File I/O stream
  	public ReadExcel(){  	
  	File src = new File("./Data/TestData.xlsx");     	//You need to specify the location where excel file is saved in your system.
  	try {
         	FileInputStream fis = new FileInputStream(src);
       wb = new XSSFWorkbook(fis); 	//After creating an object of File Input Stream, this line of code          would load my file into the XSSFWorkbook
  	}
  	catch (Exception e) {
         	System.out.println("The exception is: " +e.getMessage());
         	        	} 	
  	}
   //This method would fetch the data from the corresponding cell of the excel sheet and return as a String.
         	public String getData(int sheet,int row, int column){ 	
         		String data;
         		try {data = wb.getSheetAt(sheet).getRow(row).getCell(column).getStringCellValue();}
         		catch(Exception ex) {data = String.valueOf((int) Math.round(wb.getSheetAt(sheet).getRow(row).getCell(column).getNumericCellValue()));
         		}
         	
         	return data;
  	}
         	
}
