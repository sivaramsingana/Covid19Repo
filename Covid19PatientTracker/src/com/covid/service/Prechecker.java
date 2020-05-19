package com.covid.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Prechecker {
	
	
	public void createsheet() throws IOException
	{
		 // Creating Workbook instances 
        Workbook wb = new HSSFWorkbook(); 
  
        
        // An output stream accepts output bytes and sends them to sink. 
        OutputStream fileOut = new FileOutputStream("/Resources/CovidPatientslist.xlsx"); 
        
        
          
        // Creating Sheets using sheet object 
        Sheet sheet1 = wb.createSheet("Patient DataBase"); 
        Sheet sheet2 = wb.createSheet("Statistics"); 
        
        Sheet sheet = wb.getSheetAt(0); 
        Row row = sheet.createRow(0); 
        row.createCell(0).setCellValue("S.No"); 
	    row.createCell(1).setCellValue("Case Number"); 
	    row.createCell(2).setCellValue("Patient Name"); 
	    row.createCell(3).setCellValue("Age"); 
	    row.createCell(4).setCellValue("Phone Number"); 
	    row.createCell(5).setCellValue("Aadhar Number"); 
	    row.createCell(6).setCellValue("Street Number"); 
	    row.createCell(7).setCellValue("City"); 
	    row.createCell(8).setCellValue("District"); 
	    row.createCell(9).setCellValue("State"); 
	    row.createCell(10).setCellValue("Country"); 
	    row.createCell(11).setCellValue("AdmissionDate"); 
	    row.createCell(12).setCellValue("Test Result"); 
	    row.createCell(13).setCellValue("Recovery Status"); 
	    row.createCell(14).setCellValue("Person Interacted"); 
	    row.createCell(15).setCellValue("Parent Contaminated"); 
	    row.createCell(16).setCellValue("Quarantine Days"); 
	    row.createCell(17).setCellValue("Quarantine Status"); 
	    row.createCell(18).setCellValue("Quarantine Place");    
	    
	    
        System.out.println("Sheets Has been Created successfully"); 
  
        wb.write(fileOut); 
		
	}
	
	public static ArrayList<Row> searchSheet(String searchText, HSSFSheet sheet) {
        // This parameter is for appending sheet rows to mergedSheet in the end
        
        Double doubleValue = null;
        Boolean booleanValue = null;
        ArrayList<Row> filteredRows = new ArrayList<Row>();

        //Get double value if searchText is double
        try {
            doubleValue = Double.parseDouble(searchText);
        } catch(Exception e) {  
        }
        
        //Get boolean value if searchText is boolean
        try {
            booleanValue = Boolean.parseBoolean(searchText);
        } catch(Exception e) {  
        }
        
        
        
        //Iterate rows
        for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {
        	System.out.println("some tests here starts from: "+sheet.getFirstRowNum()+" last num "+sheet.getLastRowNum()+" now in row"+j);
            HSSFRow row = sheet.getRow(j);
           
            //Iterate columns
            for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                HSSFCell cell = row.getCell(k);
            //    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
           System.out.println("cell type here: "+cell.getCellType()+" row number: "+j+" row cell num: "+k+" last cell: "+row.getLastCellNum());//+" value"+cell.getStringCellValue());
               //Search value based on cell type 0 for numeric and 1 for string
         //  if((cell.getStringCellValue() == null)||("".equals(cell.getNumericCellValue())))
         //  {
        //	   continue;
        //   }
           switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if(doubleValue != null && doubleValue.doubleValue() == cell.getNumericCellValue()) {
                        filteredRows.add(row);
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    if(searchText != null && searchText.equals(cell.getStringCellValue())) {
                        filteredRows.add(row);
                    }
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    if(booleanValue != null && booleanValue.booleanValue() == cell.getBooleanCellValue()) {
                        filteredRows.add(row);
                    }
                    break;
                default:
                    if(searchText != null && searchText.equals(cell.getStringCellValue())) {
                        filteredRows.add(row);
                    }
                    break;
                }
            }
        }
        return filteredRows;
    }

	
}
