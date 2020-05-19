package com.covid.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.covid.service.Prechecker;

/**
 * Servlet implementation class ProfileUpdater
 */
//@WebServlet(urlPatterns="/ProfileUpdater")
public class ProfileUpdater extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProfileUpdater() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		
		String FILE_NAME =request.getServletContext().getRealPath("/Resources/CovidPatientslist.xls");
		String casenum = request.getParameter("casenum");
		String name = request.getParameter("pname");
		String age = request.getParameter("age");
		String id = request.getParameter("Pid");
		String phnumber = request.getParameter("phnumber");
		String street = request.getParameter("streetnum");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String date = request.getParameter("admitdate");
		String testresult = request.getParameter("testresult");
		String recoverystatus = request.getParameter("recoverstatus");
		String PInteract = request.getParameter("picontact");
		String Pcontamin = request.getParameter("parentid");
		String qstatus = request.getParameter("Qtnstatus");
		String place = request.getParameter("place");
		String health = request.getParameter("healthIssue");
		
		System.out.println("checking issue with id"+id);
		Prechecker pc =  new Prechecker();
		
		File file = new File(FILE_NAME);
		System.out.println("check file availability");
		 if (file.isFile() && file.exists()) { 
	            System.out.println("CovidPatientslist.xls open");   
	        } 
	        else { 
	            System.out.println("CovidPatientslist.xls either not exist or can't open"); 
	           // pc.createsheet();
	            
	            System.out.println("Creating new file with same file name"+file.createNewFile());
	            
	            // Creating Workbook instances 
	            Workbook wb = new HSSFWorkbook(); 
	       
	            // An output stream accepts output bytes and sends them to sink. 
	            OutputStream fileOut = new FileOutputStream(FILE_NAME); 
	              
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
	    	    row.createCell(19).setCellValue("Health Issues");
	    	    
	    	    
	            System.out.println("Sheets Has been Created successfully"); 
	      
	            wb.write(fileOut); 
	    	
	       
	        } 
		 if(date!=null) {
		
		InputStream inp = new FileInputStream(FILE_NAME); 
	    Workbook wb = null;
		try {
			if(inp != null) {
			wb = WorkbookFactory.create(inp);
			}
		} catch (EncryptedDocumentException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    Sheet sheet = wb.getSheetAt(0); 
	    int num = sheet.getLastRowNum(); 
	    Row row = sheet.createRow(++num); 
	  //    System.out.println(row.getCell(0));
	    row.createCell(0).setCellValue(num); 
		row.createCell(1).setCellValue(casenum); 
	    row.createCell(2).setCellValue(name); 
	    row.createCell(3).setCellValue(age); 
	    row.createCell(4).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	    row.createCell(4).setCellValue(phnumber); 
	    row.createCell(5).setCellValue(id); 
	    row.createCell(6).setCellValue(street); 
	    row.createCell(7).setCellValue(city); 
	    row.createCell(8).setCellValue(district); 
	    row.createCell(9).setCellValue(state); 
	    row.createCell(10).setCellValue(country); 
	    row.createCell(11).setCellValue(date); 
	    row.createCell(12).setCellValue(testresult); 
	    row.createCell(13).setCellValue(recoverystatus); 
	    row.createCell(14).setCellValue(PInteract); 
	    row.createCell(15).setCellValue(Pcontamin); 
	    row.createCell(16).setCellValue("0"); 
	    //Will set automatically
	    row.createCell(17).setCellValue(qstatus); 
	    row.createCell(18).setCellValue(place);    
	    row.createCell(19).setCellValue(health);    

	    System.out.println("just checking its status");
	    inp.close();
//	    ..... 
//	        .. 
	        // Now this Write the output to a file 
	        FileOutputStream fileOut = new FileOutputStream(FILE_NAME); 
	    try {
	    	wb.write(fileOut); 
		    fileOut.close();
			
	    }
	    catch (Exception e) {
			// TODO: handle exception
	    	PrintWriter pw = new PrintWriter("Index.jsp");
	    	pw.println("Please try again after sometime");
		}
	     } 
	    response.sendRedirect("Index.jsp");
	    
	}

}
