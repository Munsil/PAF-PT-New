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
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.TEXT_HTML)
	public String readHospital()
	{
		return HospitalObj.readHospital();
	}
	
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital
	(
	 		
	 @FormParam("name") String name,
	 @FormParam("address") String address,
	 @FormParam("charge") String charge,
	 @FormParam("phonenumber") String phonenumber,
	 @FormParam("roomcount") String roomcount)
	{
	 String output = HospitalObj.insertHospital(name, address, charge, phonenumber, roomcount);
	 
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String HospitalData)
	{
	//Convert the input string to a JSON object
	 JsonObject HospitalObject = new JsonParser().parse(HospitalData).getAsJsonObject();
	//Read the values from the JSON object
	 String hospitalid = HospitalObject.get("hospitalid").getAsString();
	 String name = HospitalObject.get("name").getAsString();
	 String address = HospitalObject.get("address").getAsString();
	 String charge = HospitalObject.get("charge").getAsString();
	 String phonenumber = HospitalObject.get("phonenumber").getAsString();
	 String roomcount = HospitalObject.get("roomcount").getAsString();
	 
	 String output = HospitalObj.updateHospital(hospitalid, name, address, charge, phonenumber,roomcount);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String HospitalData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(HospitalData, "", Parser.xmlParser());

	//Read the value from the element <hospitalid>
	 String hopitalid = doc.select("hospitalid").text();
	 String output = HospitalObj.deleteHospital(hopitalid);
	return output;
	}

	
	
}

