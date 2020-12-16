<%@page import="management.AirlineManagementSystem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>FMS - Airline List</title>
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


 <h3 id="airlinelist" >Airline list </h3>
  <%! AirlineManagementSystem airlinems = new AirlineManagementSystem(); %>
  
  <table style="width:100%">
  <tr>
    <th>Airline Codename</th>
    <th>Airline Callsign</th>
    <th>Airline Country</th>
  </tr>
  <tr>
  <% 
 for (int i = 0; i<airlinems.fetchDatabaseContentToList().size(); i++) {
	 
	 %>
	 <td>  <%= airlinems.fetchDatabaseContentToList().get(i).getAirlineCodename() %> </td>
	 <td> <%= airlinems.fetchDatabaseContentToList().get(i).getAirlineCallsign() %> </td>
	 <td> <%= airlinems.fetchDatabaseContentToList().get(i).getAirlineCountry() %> </td>
	 </tr>
	 <%
 }
		 %>
</table>
  

 
</body>

</html>
