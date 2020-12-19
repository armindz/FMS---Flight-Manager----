<%@page import="management.FlightManagementSystem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Flight"%>
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
        <div class="navbarsections">
            <div class="dropdown">
                <button class="dropbtn">Create</button>
                <div class="dropdown-content">
                    <a href="AirlineForm.html">Airline</a>
                    <a href="AirportForm.html">Airport</a>
                    <a href="FlightForm.html">Flight</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropbtn">List</button>
                <div class="dropdown-content">
                    <a href="AirlineList.jsp">Airline</a>
                    <a href="AirportList.jsp">Airport</a>
                    <a href="FlightList.jsp">Flight</a>
                </div>
            </div>


            <div class="dropdown">
                <button class="dropbtn">Modify</button>
                <div class="dropdown-content">
                    <a href="#">Airline</a>
                    <a href="#">Airport</a>
                    <a href="#">Flight</a>
                </div>
            </div>
            <a id="navbarBook" href="">Book</a>

        </div>
    </header>

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
 ArrayList <Flight> fetchDataToList =  flightms.fetchFlightDatabaseContentToList();
 for (int i = 0; i < fetchDataToList.size(); i++) {
	 
	 %>
	 <td>  <%= fetchDataToList.get(i).getFlight_id() %> </td>
	 <td>  <%= fetchDataToList.get(i).getAirline().getAirlineCodename() %> </td>
	  <td>  <%= fetchDataToList.get(i).getAirport().getAirportCodename() %> </td>
	   <td>  <%= fetchDataToList.get(i).getDestinationAirport().getAirportCodename() %> </td>
	    <td>  <%= fetchDataToList.get(i).getFlightClass() %> </td>
	     <td>  <%= fetchDataToList.get(i).getDateOfFlight().getTime() %> </td>
	      <td>  <%= fetchDataToList.get(i).getSeatRow() %> </td>
	       <td>  <%= fetchDataToList.get(i).getSeatNumber() %> </td>
	        <td>  <%= fetchDataToList.get(i).getFlightPrice() %> </td>
	 </tr>
	 <%
 }
		 %>
</table>
  

 
</body>

</html>
