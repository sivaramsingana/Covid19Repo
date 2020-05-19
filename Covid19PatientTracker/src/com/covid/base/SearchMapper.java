package com.covid.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.omg.CORBA.Request;

import com.covid.bean.PatientBean;
import com.covid.service.Prechecker;

/**
 * Servlet implementation class SearchMapper
 */
@WebServlet("/SearchMapper")
public class SearchMapper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMapper() {
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
		System.out.println("Welcome to search");
		String FILE_NAME =request.getServletContext().getRealPath("/Resources/CovidPatientslist.xls");
		ArrayList<PatientBean> pbean = new ArrayList<>();
		
		Prechecker pc = new Prechecker();
		PatientBean pb=null;
		String query= request.getParameter("patientsearch").toLowerCase().trim();
		//	int rownum;
		InputStream inp = new FileInputStream(FILE_NAME); 
		HSSFWorkbook wb = new HSSFWorkbook(inp); 
	    HSSFSheet sheet = wb.getSheetAt(0); 
	    System.out.println("sheet name pls:"+sheet.getSheetName());
	    ArrayList<Row> rows = pc.searchSheet(query, sheet);
	    System.out.println("getting these rows"+rows);
	    for(Row r: rows)
	    {
	    	pb =new PatientBean();
			
	    	pb.setCaseNumber(r.getCell(1).getStringCellValue());
	    	pb.setPatientName(r.getCell(2).getStringCellValue());
	    	pb.setAge(r.getCell(3).getStringCellValue());
	    	pb.setPhoneNumber(r.getCell(4).getStringCellValue());
	    	pb.setAadharNumber(r.getCell(5).getStringCellValue());
	    	pb.setStreetNumber(r.getCell(6).getStringCellValue());
	    	pb.setCity(r.getCell(7).getStringCellValue());
	    	pb.setDistrict(r.getCell(8).getStringCellValue());
	    	pb.setState(r.getCell(9).getStringCellValue());
	    	pb.setCountry(r.getCell(10).getStringCellValue());
	    	pb.setAdmissionDate(r.getCell(11).getStringCellValue());
	    	pb.setTestResult(r.getCell(12).getStringCellValue());
	    	pb.setRecoveryStatus(r.getCell(13).getStringCellValue());
	    	pb.setPersonInteracted(r.getCell(14).getStringCellValue());
	    	pb.setParentContaminated(r.getCell(15).getStringCellValue());
	    	pb.setQuarantineDays(r.getCell(16).getStringCellValue());
	    	pb.setQuarantineStatus(r.getCell(17).getStringCellValue());
	    	pb.setQuarantinePlace(r.getCell(18).getStringCellValue());
	    	pb.setQuarantinePlace(r.getCell(19).getStringCellValue());
	    	
	    	
	//    	System.out.println("some values"+pb.getCaseNumber()+" name"+pb.getPatientName());
	    	pbean.add(pb);
	    	
	    	}
	    
	    System.out.println("checking the list... "+pbean.toString());
	    
	/*    for(Row row:sheet)
	    {
	    	for(Cell cell : row) {
                    if(cell.getRichStringCellValue().getString () == query);{
                            rownum = row.getRowNum();
                    }
	    }
	    
	    }   
	*/
	    RequestDispatcher rd = request.getRequestDispatcher("Search.jsp");//getServletContext().getRequestDispatcher("Search.jsp");
	    request.setAttribute("resultarray",pbean);
		rd.forward(request, response);
	  
	  //  response.sendRedirect("Search.jsp");
	}

	
}
