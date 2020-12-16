<%@page import="management.FlightManagementSystem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>FMS - Flight List</title>
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

 <h3 id="airlinelist" >Flight list </h3>
  <%! FlightManagementSystem flightms = new FlightManagementSystem(); %>
  
  <table style="width:100%">
  <tr> 
  	
  	<th>Flight ID</th>
    <th>Airline</th>
    <th>Airport</th>
    <th>Destination Airport</th>
    <th>Flight class</th>
    <th>Date of flight</th>
    <th>Latest seat row</th>
    <th>Seats per row</th>
    <th>Flight price</th>
  </tr>
  <tr>
  <% 
 for (int i = 0; i< flightms.fetchFlightDatabaseContentToList().size(); i++) {
	 
	 %>
	 <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getFlight_id() %> </td>
	 <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getAirline().getAirlineCodename() %> </td>
	  <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getAirport().getAirportCodename() %> </td>
	   <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getDestinationAirport().getAirportCodename() %> </td>
	    <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getFlightClass() %> </td>
	     <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getDateOfFlight().getTime() %> </td>
	      <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getSeatRow() %> </td>
	       <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getSeatNumber() %> </td>
	        <td>  <%= flightms.fetchFlightDatabaseContentToList().get(i).getFlightPrice() %> </td>
	 </tr>
	 <%
 }
		 %>
</table>
  

 
</body>

</html>
