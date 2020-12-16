<%@page import="management.AirportManagementSystem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>FMS - Airport List</title>
    <link rel="icon" href="img/icons/fmsround.png" type="image/x-icon">
</head>


<body>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href='https://fonts.googleapis.com/css?family=Bubbler One ' rel='stylesheet '>


    <header>
        <img id="logo" src="img/icons/fms transparent.png">

        <a id="navbarCreate" href="">Create</a>
        <a id="navbarList" href="">List</a>
        <a id="navbarModify" href="">Modify</a>
        <a id="navbarBook" href="">Book</a>
    </header>



    <input type="submit" value="Submit" />


 <h3 id="airlinelist" >Airport list </h3>
  <%! AirportManagementSystem airportms = new AirportManagementSystem(); %>
  
  <table style="width:100%">
  <tr> 
  
    <th>Airport Codename</th>
    <th>Airport Fullname</th>
    <th>Airport Type</th>
    <th>Airport City</th>
    <th>Airport Country</th>
  </tr>
  <tr>
  <% 
 for (int i = 0; i< airportms.fetchDatabaseContentToList().size(); i++) {
	 
	 %>
	 <td>  <%= airportms.fetchDatabaseContentToList().get(i).getAirportCodename() %> </td>
	 <td> <%= airportms.fetchDatabaseContentToList().get(i).getAirportFullname() %> </td>
	 <td> <%= airportms.fetchDatabaseContentToList().get(i).getAirportType() %> </td>
	 <td> <%= airportms.fetchDatabaseContentToList().get(i).getAirportCity() %> </td>
	 <td> <%= airportms.fetchDatabaseContentToList().get(i).getAirportCountry() %> </td>
	 </tr>
	 <%
 }
		 %>
</table>
  

 
</body>

</html>
