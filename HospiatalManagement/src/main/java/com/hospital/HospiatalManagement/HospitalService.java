package com.hospital.HospiatalManagement;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Hospital;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Hospitals")
public class HospitalService
{
	Hospital HospitalObj = new Hospital();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return HospitalObj.readHospital();
	}
	
	

	
}

