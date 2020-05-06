package model;

import java.sql.*;



public class Hospital
			{ //A common method to connect to the DB
				private Connection connect()
						 {
						 Connection con = null;
						 try
						 {
						 Class.forName("com.mysql.cj.jdbc.Driver");
						
						 //Provide the correct details: DBServer/DBName, username, password
						 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/testpaf?serverTimezone=UTC", "root", "");
						 }
						 catch (Exception e)
						 {e.printStackTrace();}
						 return con;
				 }
		public String insertHospital(String name, String address, String charge, String phonenumber, String roomcount)
			{
				 String output = "";
				 try
					{
						 Connection con = connect();
						 
						 if (con == null)
						 {return "Error while connecting to the database for inserting."; }
						 
						 // create a prepared statement
						 String query = "insert into hospital values(?,?,?,?,?,?)";
						 
					
					
						
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 // binding values
						 preparedStmt.setInt(1, 0);
						 preparedStmt.setString(2, name);
						 preparedStmt.setString(3, address);
						 preparedStmt.setDouble(4, Double.parseDouble(charge));
						 preparedStmt.setString(5, phonenumber);
						 preparedStmt.setInt(6, Integer.parseInt(roomcount));
						// execute the statement
						 preparedStmt.execute();
						 con.close();
						 String newhospital = readHospital();
						 output = "{\"status\":\"success\", \"data\": \"" +
								 newhospital + "\"}";
						 
				  }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Hospital.\"}";
					 System.err.println(e.getMessage());
				 }
				 
				  return output;
		}
						
		
	public String readHospital()
	{
				String output = "";
				
				try
			    {
						 Connection con = connect();
						 
						 if (con == null)
						 {return "Error while connecting to the database for reading."; }
						 
						 // Prepare the html table to be displayed
						 output = "<table class=\"table table-dark\" border=\"3\"><tr><th>Name</th><th>Address</th><th>Charge</th><th>Phone Number</th><th>Room Count</th><th>Update</th><th>Remove</th></tr>";
								 	
						
						 String query = "select * from hospital";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
						 String hospitalid = Integer.toString(rs.getInt("hospitalid"));
						 String name = rs.getString("name");
						 String address = rs.getString("address");
						 String charge = Double.toString(rs.getDouble("charge"));
						 String phonenumber = rs.getString("phonenumber");
						 String roomcount = Integer.toString(rs.getInt("roomcount"));
						 
						 
						 // Add into the html table
						 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + hospitalid + "'>" 
						 + name + "</td>";
						 output += "<td>" + address + "</td>";
						 output += "<td>" + charge + "</td>";
						 output += "<td>" + phonenumber + "</td>";
						 output += "<td>" + roomcount + "</td>";
						 
						 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btnUpdate btn btn-secondary'></td>"
							 		+ "<td><input name='btnRemove' type='button'  value='Remove'  class='btnRemove btn btn-danger' data-hospitalid='"
									 + hospitalid + "'>" + "</td></tr>";
						  
						 
						 
					
						 }
						 con.close();
 
						 // Complete the html table
						 output += "</table >";
						 }
				catch (Exception e)
					{
						 output = "Error while reading the Hospital.";
						 System.err.println(e.getMessage());
					}
				 return output;
			} 
	
	public String updateHospital(String hospitalid, String name, String address, String charge, String phonenumber, String roomcount)
			 {
			 	String output = "";
			 try
			 {
				 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for updating."; }
					 // create a prepared statement
					 String query = "UPDATE hospital SET name=?,address=?,charge=?,phonenumber=?,roomcount=? WHERE hospitalid=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 
					 preparedStmt.setString(1, name);
					 preparedStmt.setString(2, address);
					 preparedStmt.setDouble(3, Double.parseDouble(charge));
					 preparedStmt.setString(4, phonenumber);
					 preparedStmt.setInt(5, Integer.parseInt(roomcount));
					 preparedStmt.setInt(6, Integer.parseInt(hospitalid));
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newhospital = readHospital();
					 output = "{\"status\":\"success\", \"data\": \"" +
							 newhospital + "\"}";
					 
					 
					 
					 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Hospital.\"}";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	
	
	public String deleteHospital(String hospitalid)
			 {
			 		String output = "";
			 try
			 {
				 	Connection con = connect();
							 if (con == null)
							 {return "Error while connecting to the database for deleting."; }
							 // create a prepared statement
							 String query = "delete from hospital where hospitalid=?";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 // binding values
							 preparedStmt.setInt(1, Integer.parseInt(hospitalid));
							 // execute the statement
							 preparedStmt.execute();
							 con.close();
							 String newhospital = readHospital();
							 output = "{\"status\":\"success\", \"data\": \"" +
									 newhospital + "\"}";
							 
							 }
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Hospital.\"}";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	} 
