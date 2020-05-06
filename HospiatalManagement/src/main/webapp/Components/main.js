$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
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
		var status = validateHosForm();
		// If not valid-------------------
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid----------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
		
		$.ajax(
				{
				 url : "HospitalAPI",
				 type : type,
				 data : $("#formHospital").serialize(),
				 dataType : "text",
				 complete : function(response, status)
				 {
				 onHosSaveComplete(response.responseText, status);
				 }
				});
		
});

function onHosSaveComplete(response, status)
{
if (status == "success")
		 {
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully saved.");
			 $("#alertSuccess").show();
			 $("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
			 $("#alertError").text("Error while saving.");
			 $("#alertError").show();
		 } else
		 {
			 $("#alertError").text("Unknown error while saving..");
			 $("#alertError").show();
		 }
			 $("#hidItemIDSave").val("");
			 $("#formHospital")[0].reset();
}


// REMOVE==========================================

$(document).on("click", ".remove", function(event)
		{
			$.ajax(
					 {
					 url : "HospitalAPI",
					 type : "DELETE",
					 data : "hospitalid=" + $(this).data("hospitalid"),
					 dataType : "text",
					 complete : function(response, status)
					 {
					 onHosDeleteComplete(response.responseText, status);
					 }
			 });
		}); 

function onHosDeleteComplete(response, status)
{
		if (status == "success")
		 {
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
		 $("#alertSuccess").text("Successfully deleted.");
		 $("#alertSuccess").show();
		 $("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
		 $("#alertError").text(resultSet.data);
		 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
		 } else
		 {
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
		 }
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#address").val($(this).closest("tr").find('td:eq(1)').text());
 $("#charge").val($(this).closest("tr").find('td:eq(2)').text());
 $("#phonenumber").val($(this).closest("tr").find('td:eq(3)').text());
 $("#roomcount").val($(this).closest("tr").find('td:eq(3)').text());
});


// CLIENT-MODEL=================================================================
	function validateHosForm()
	{
	// NAME
			
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
			// is numerical value
			var tmpPrice = $("#charge").val().trim();
			if (!$.isNumeric(tmpPrice))
			 {
			 return "Insert a numerical value for Charging amount.";
			 }
			// convert to decimal price
			 $("#charge").val(parseFloat(tmpPrice).toFixed(2));
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
