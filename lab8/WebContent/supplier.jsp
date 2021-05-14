
<%@page import="com.Supplier"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
    
    <%
    //Insert Supplier
	if (request.getParameter("Supplier_code") != null)
 		{
 			Supplier supplierObj = new Supplier();
 			String stsMsg = supplierObj.insertsupplier(request.getParameter("Supplier_code"),
 			request.getParameter("Name"),
 			request.getParameter("Phone"));
 			session.setAttribute("statusMsg", stsMsg);
 		}
    
  //Delete Supplier----------------------------------
    if (request.getParameter("itemID") != null)
     {
     Supplier supplierObj = new Supplier();
     String stsMsg = supplierObj.deletesupplier(request.getParameter("supplierID"));
     session.setAttribute("statusMsg", stsMsg);
     }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="Views/bootstrap.min.css">  
<title>Supplier Management</title>
</head>


<body>
	<h1>Supplier Management</h1>
<form method="post" action="supplier.jsp">
 Supplier code: <input name="Supplier_code" type="text"><br>
 Supplier name: <input name="Name" type="text"><br>
 Supplier phone: <input name="Phone" type="text"><br>
 
 <input name="btnSubmit" type="submit" value="Save">
</form>

	<%
 		out.print(session.getAttribute("statusMsg"));
	%>
	<br> 

	<%
 		Supplier supplierObj = new Supplier();
 		out.print(supplierObj.readsupplier());
	%>
	

	<input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
</body>
</html>