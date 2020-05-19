package com.covid.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
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
		doGet(request, response);
		
		String FILE_NAME =request.getServletContext().getRealPath("/Resources/CovidPatientslist.xls");

	
		String casenumber = request.getParameter("casenum");
		String testresult = request.getParameter("testresult");
		String recovery = request.getParameter("recoverstatus");
		String quarantinestatus = request.getParameter("Qtnstatus");
		System.out.println("check the updates "+recovery+ " test "+testresult);
		
		InputStream inp = new FileInputStream(FILE_NAME); 
		HSSFWorkbook wb = new HSSFWorkbook(inp); 
	    HSSFSheet sheet = wb.getSheetAt(0); 
	    int num = sheet.getLastRowNum(); 
	    Row row = null;
	    HSSFCell cell = null;
	    for(int i=1;i<num+1;i++)
	    {
	    	row = sheet.getRow(i);
	    	cell = (HSSFCell) row.getCell(1);
	    //	System.out.println("get the cell details case details"+casenumber);
	    //	System.out.println("check few details atleast"+row.getRowNum()+" and cell values "+cell.getStringCellValue());
	    	if(casenumber.equals(cell.getStringCellValue()))
	    	{
	    	//	System.out.println("now in row "+sheet.getRow(i).getRowNum());
	    		//row.createCell(12).setCellValue(testresult);
	    		row.createCell(13).setCellValue(recovery);
	    		row.createCell(17).setCellValue(quarantinestatus);
	    		row.getCell(12).setCellValue(testresult);
	    		break;
	    	}
	    	
	    }
	    FileOutputStream fileOut = new FileOutputStream(FILE_NAME); 
	    try {
	    	wb.write(fileOut); 
		    fileOut.close();
			
	    }
	    catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("update after sometime");
		}
	    
	    
	    wb.close();
	    inp.close();
	    
	    String message = "Patient details Updated succesfully!!";
	    RequestDispatcher rd = request.getRequestDispatcher("Updatestatus.jsp");//getServletContext().getRequestDispatcher("Search.jsp");
	    request.setAttribute("msg",message);
		rd.forward(request, response);
	  
	}
	

}
