package com;
import java.sql.*;
public class Supplier {

	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop",
	 "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}

	
	public String insertsupplier(String code, String name, String phone)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into supplier(`supplierID`,`Supplier_code`,`Name`,`phone`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, code);
	 preparedStmt.setString(3, name);
	
	 preparedStmt.setString(4, phone); 
	
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while inserting";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	public String readsupplier()
	{
	 String output = "";
	 
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Supplier Code</th>"
	 +"<th>Name</th><th>Phone</th>"
	 
	 + "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from supplier";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String supplierID = Integer.toString(rs.getInt("supplierID"));
	 String Supplier_code= rs.getString("Supplier_code");
	 String Name = rs.getString("Name");
	 String Phone = rs.getString("Phone");
	 // Add a row into the html table
	 output += "<tr><td>" + Supplier_code + "</td>";
	 output += "<td>" + Name + "</td>";
	 output += "<td>" + Phone + "</td>"; 
	
	
	 // buttons
	 output += "<td><input name='btnUpdate' "
	 + " type='button' value='Update'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' "
	 + " type='submit' value='Remove'>"
	 + "<input name='itemID' type='hidden' "
	 + " value='" + supplierID + "'>" + "</form></td></tr>";
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
	
	public String deletesupplier(String supplierID)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 }
	 // create a prepared statement
	 String query = "delete from supplier where supplierID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(supplierID));

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while deleting the supplier.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
}




