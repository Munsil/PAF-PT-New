$(document).ready(function()
{
	 $("#alertSuccess").hide();
	 $("#alertError").hide();
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear -------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation----------------
		var status = validateItemForm();
		// If not valid-------------------
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid----------------------
		$("#formHospital").submit(); 
		
		
});
// REMOVE==========================================
$(document).on("click", ".remove", function(event)
		{
				 $(this).closest(".hospital").remove();
				
				 $("#alertSuccess").text("Removed successfully.");
				 $("#alertSuccess").show();
		}); 

// CLIENT-MODEL=================================================================
	function validateItemForm()
	{
	// NAME
			if ($("#hospitalid").val().trim() == "")
			 {
					return "Insert Hospital Id.";
			 }
			if ($("#name").val().trim() == "")
			 {
					return "Insert Hospital name.";
			 }
			if ($("#address").val().trim() == "")
			 {
					return "Insert Address.";
			 }
			if ($("#charge").val().trim() == "")
			 {
					return "Insert Charge.";
			 }
			if ($("#phonenumber").val().trim() == "")
			 {
					return "Insert Phone number.";
			 }
			if ($("#roomcount").val().trim() == "")
			 {
					return "Insert Room count.";
			 }
			
			return true; 
	}
/*	
	function getHospitalCard(hospitalid, name, address, charge, phone, count)
	{
			
			var hospital = "";
			hospital += "<div class=\"hospital card bg-light m-2\"style=\"max-width: 15rem; float: left;\">"; 
			hospital += "<div class=\"card-body\">";
			hospital += "Hospital Id :"+ hospitalid + " ";
			hospital += "<br>";
			hospital += "Name :"+ name + " ";
			hospital += "<br>";
			hospital += "Address :"+address + " ";
			hospital += "<br>";
			hospital += "Rs: "+charge +".00";
			hospital += "<br>";
			hospital += "Phone Number :"+phone + " ";
			hospital += "<br>";
			hospital += "Room Count :"+count + " ";
			hospital += "</div>";
			hospital += "<input type=\"button\" value=\"Remove\"class=\"btn btn-danger remove\">";
			 
			hospital += "</div>";
			return hospital;
	}

	*/