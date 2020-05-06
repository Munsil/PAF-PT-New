
<%@page import="com.hospital.HospiatalManagement.HospitalService" %>
<%@page import="model.Hospital"%> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.3.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
		<div class="container">
			<div class="row">
				<div class="col-5">
					<h1 class="m-3">Hospital Details</h1>
			
						<form id="formHospital" name="formHospital" method="post" action="Hospital.jsp" >
											
											 
											<br> Hospital Name:
											<input id="name" name="name" type="text"
											 class="form-control form-control-sm">
											<br> Address:
											<input id="address" name="address" type="text"
											 class="form-control form-control-sm">
											<br> Hospital Charge:
											<input id="charge" name="charge" type="text"
											 class="form-control form-control-sm">
											 <br> Phone Number:
											<input id="phonenumber" name="phonenumber" type="text"
											 class="form-control form-control-sm">
											<br>Room Count:
											<input id="roomcount" name="roomcount" type="text"
											 class="form-control form-control-sm">
											 <br>
											<input id="btnSave" name="btnSave" type="button" value="Save"
											 class="btn btn-primary">
											<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
								</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
			<div id="divItemsGrid">
			 <%
				 Hospital HospitalObj = new Hospital();
				 out.print(HospitalObj.readHospital());
			 %>
			</div>
			
			</div>
			 </div>
		</div>

</body>
</html>