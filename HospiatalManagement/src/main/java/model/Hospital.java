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
						 String query = " insert into hospital(`hospitalid`,`name`,`address`,`charge`,`phonenumber`, 'roomcount')values (?, ?, ?, ?, ? ,?)";
						 
					
					
						
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
						 output = "Inserted successfully";
				  }
				  catch (Exception e)
				  {
						 output = "Error while inserting the item.";
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
						 output = "<table border=\"1\"><tr><th>ID</th><th>Name</th><th>Adress</th><th>Charge</th><th>Phone Number</th><th>Room Count</th><th>Update</th><th>Remove</th></tr>";
								 	
						
						 String query = "select * from hospital";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
						 String hospitalID = Integer.toString(rs.getInt("hospitalid"));
						 String hospitalName = rs.getString("name");
						 String hospitalAddress = rs.getString("address");
						 String hospitalCharge = Double.toString(rs.getDouble("charge"));
						 String hospitalPhone = rs.getString("phonenumber");
						 String hospitalcount = Integer.toString(rs.getInt("roomcount"));
						 // Add into the html table
						 output += "<tr><td>" + hospitalID + "</td>";
						 output += "<td>" + hospitalName + "</td>";
						 output += "<td>" + hospitalAddress + "</td>";
						 output += "<td>" + hospitalCharge + "</td>";
						 output += "<td>" + hospitalPhone + "</td>";
						 output += "<td>" + hospitalcount + "</td>";
						 
						 // buttons
						 output += "<td><input name=\"btnUpdate\" type=\"button\""
						 +"value=\"Update\" class=\"btn btn-secondary\"></td>"
						 + "<td><form method=\"post\" action=\"hospital.jsp\">"
						 + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\""
						 + "class=\"btn btn-danger\">"
						 + "<input name=\"itemID\" type=\"hidden\" value=\"" + hospitalID
						 + "\">" + "</form></td></tr>";
						 }
						 con.close();
						 // Complete the html table
						 output += "</table>";
						 }
				catch (Exception e)
					{
						 output = "Error while reading the items.";
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
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "Updated successfully";
					 }
			 catch (Exception e)
			 {
			 output = "Error while updating the item.";
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
							 String query = "delete from hospital where hospitlid=?";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 // binding values
							 preparedStmt.setInt(1, Integer.parseInt(hospitalid));
							 // execute the statement
							 preparedStmt.execute();
							 con.close();
							 output = "Deleted successfully";
							 }
			 catch (Exception e)
			 {
					 output = "Error while deleting the item.";
					 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	} 
