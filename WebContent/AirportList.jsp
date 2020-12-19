<%@page import="management.AirportManagementSystem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Airport"%>
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
  ArrayList <Airport> fetchDataToList =  airportms.fetchDatabaseContentToList();
 for (int i = 0; i< fetchDataToList.size(); i++) {
	 
	 %>
	 <td>  <%= fetchDataToList.get(i).getAirportCodename() %> </td>
	 <td> <%= fetchDataToList.get(i).getAirportFullname() %> </td>
	 <td> <%= fetchDataToList.get(i).getAirportType() %> </td>
	 <td> <%= fetchDataToList.get(i).getAirportCity() %> </td>
	 <td> <%= fetchDataToList.get(i).getAirportCountry() %> </td>
	 </tr>
	 <%
 }
		 %>
</table>
  

 
</body>

</html>
