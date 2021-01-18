<%@page import="models.Airline"%>
    <%@page import="management.AirlineManagementSystem"%>
        <%@page import="java.util.ArrayList"%>
            <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="ISO-8859-1">
                    <title>FMS - Airline List</title>
                    <link rel="icon" href="img/icons/fmsround.png" type="image/x-icon">
                     <link rel="stylesheet" type="text/css" href="css/style.css">
                    <link href='https://fonts.googleapis.com/css?family=Bubbler One ' rel='stylesheet '>
                </head>


                <body>
                   


                    <header>

                        <a href="index.html"> <img id="logo" src="img/icons/fms transparent.png"></a>
                        <div class="navbarsections">
                            <div class="dropdown">
                                <button class="dropbtn">Create &#11206;</button>
                                <div class="dropdown-content">
                                    <a href="airlineForm.html">Airline</a>
                                    <a href="airportForm.html">Airport</a>
                                    <a href="flightForm.jsp">Flight</a>
                                </div>
                            </div>

                            <div class="dropdown">
                                <button class="dropbtn">List &#11206;</button>
                                <div class="dropdown-content">
                                    <a href="airlineList.jsp">Airline</a>
                                    <a href="airportList.jsp">Airport</a>
                                    <a href="flightList.jsp">Flight</a>
                                </div>
                            </div>


                            <div class="dropdown">
                                <button class="dropbtn">Modify &#11206;</button>
                                <div class="dropdown-content">
                                    <a href="#">Airline</a> <a href="#">Airport</a> <a href="#">Flight</a>
                                </div>
                            </div>
                          
 										<form class="logoutButton" action="LogoutServlet" method="GET">
                                        <button class="logoutbtn">Log out!</button>
                                    </form>
                        </div>
                    </header>



                   

                    <h3 id="airlinelist">Airline list</h3>
                    <%!AirlineManagementSystem airlinems = new AirlineManagementSystem();%>

                        <table class="airlineListTable" style="width: 100%">
                            <tr>
                                <th>Airline Codename</th>
                                <th>Airline Callsign</th>
                                <th>Airline Country</th>
                                <th>Functions</th>
                            </tr>
                            <tr>
                                <% 
			try {
				ArrayList<Airline> fetchDataToList = airlinems.fetchDatabaseContentToList();
	
			for (int i = 0; i < fetchDataToList.size(); i++) {
			%>
									<td>
                                   <form id="airlineFromList" action="AirlinePreviewServlet"
										method="GET" name="airlineFromList">
										<input type="hidden" name="product_id"
										value="<%=fetchDataToList.get(i).getAirlineCodename()%>" />
										<input type="submit" name="airline"
										value="<%=fetchDataToList.get(i).getAirlineCodename()%>" />
									</form>
									</td>
                                    <td>
                                        <%=fetchDataToList.get(i).getAirlineCallsign()%>
                                    </td>
                                    <td>
                                        <%=fetchDataToList.get(i).getAirlineCountry()%>
                                    </td>
                                    <td>
                                    <div class="tablefunctions">
                                        <form id="remove" action="AirlineRemoveServlet" method="GET" name="removeid">
                                            <input type="hidden" name="product_id" value="<%=fetchDataToList.get(i).getAirlineCodename()%>" /> 
                                            <input type="submit" name="remove" value="Remove" />
                                        </form>
                                        <form id="update" action="AirlineUpdateServlet" method="GET" name="updateid">
                                            <input type="hidden" name="product_id" value="<%=fetchDataToList.get(i).getAirlineCodename()%>" /> 
                                            <input type="submit" name="update" value="Update" />
                                        </form>
                                        <form id="viewAirline" action="AirlinePreviewServlet" method="GET" name="vievAirlineId">
                                            <input type="hidden" name="product_id" value="<%=fetchDataToList.get(i).getAirlineCodename()%>" /> 
                                            <input type="submit" name="view" value="View" />

                                        </form>
                                        </div>
                                    </td>
                            </tr>

                            <%
                                            
 						} }  catch (Exception e){
 						
 						%>
                                <h3 style="text-align:center">Something went wrong. List may be empty.</h3>

                                <% } %>
                        </table>



                </body>

                </html>