package com.hospital.HospiatalManagement;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.Hospital;





@WebServlet("/HospitalAPI") 

/**
 * Servlet implementation class HospitalAPI
 */
public class HospitalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Hospital HospitalObj = new Hospital();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String output = HospitalObj.insertHospital(request.getParameter("hospitalid"),
				 request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("charge"),
				request.getParameter("ponenumber"),
				request.getParameter("roomcount"));
				response.getWriter().write(output);
		
	}

	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = HospitalObj.updateHospital(paras.get("hidItemIDSave").toString(),
				
				paras.get("name").toString(),
				paras.get("address").toString(),
				paras.get("charge").toString(),
				paras.get("phonenumber").toString(), 
				paras.get("roomcount").toString());
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		 String output = HospitalObj.deleteHospital(paras.get("hospitalid").toString());
		 
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 	{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ?
			 scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 String[] params = queryString.split("&");
			 for (String param : params)
			 { 
				 String[] p = param.split("=");
				 map.put(p[0], p[1]);
				 }
				 }
				catch (Exception e)
				 {
				 }
		return map;
		}
	
	

	
}
