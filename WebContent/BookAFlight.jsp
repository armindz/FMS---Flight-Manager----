 <%@ page import="models.Flight" %>
      <%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>FMS - Book a flight</title>
    <link rel="icon" href="img/icons/fmsround.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href='https://fonts.googleapis.com/css?family=Bubbler One ' rel='stylesheet '>
</head>


<body>
    


    <header>

        <a href="index.html"> <img id="logo" src="img/icons/fms transparent.png"></a>
        <div class="navbarsections">
            <div class="dropdown">
                <button class="dropbtn">Create</button>
                <div class="dropdown-content">
                    <a href="AirlineForm.html">Airline</a>
                    <a href="AirportForm.html">Airport</a>
                    <a href="FlightForm.jsp">Flight</a>
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

            <form class="logoutButton" action="LogoutServlet" method="GET">
                <button class="logoutbtn">Log out!</button>
            </form>
        </div>
    </header>

    <div class="ticketPreview">

<% Flight flight = (Flight)request.getAttribute("flightData"); %>
        <div class="ticketPreviewTable1">
            <img class="ticketPreviewAirplaneIcon" src="img/icons/ticketpreviewicon.png">
            <h2>BOARDING PASS</h2>
            <div class="table1left">

                <label for=" airportFROM ">FROM</label>
                <h3 class="airportFROM " > <%= flight.getAirport().getAirportCodename() %> </h3>



                <label for="name ">NAME</label>
                <h5 class="name " > John Doe</h5>

                <label for="flightID ">FLIGHT ID</label>
                <h5 class="flightID " ><%= flight.getFlight_id() %></h5>

                <label for="date ">DATE</label>
                <h5 class="DATE " ><%= flight.getDateOfFlight().getTime() %></h5>
            </div>

            <div class="table1right ">


                <label for="airportTO ">TO</label>
                <h3 class="airportTO " > <%= flight.getDestinationAirport().getAirportCodename() %></h3>

                <label for="airline ">AIRLINE</label>
                <h4 class="airline "> <%=flight.getAirline().getAirlineCodename() %></h4>

                    <label for="seat ">SEAT</label>
                    <h5 class="seat " > <%= flight.getSeatRow() %></h5>

                    <label for="row ">NUMBER</label>
                    <h5 class="row " > <%= flight.getSeatNumber() %></h5>

                    <label for="flightClass ">FLIGHT CLASS</label>
                    <h5 class="flightClass " > <%= flight.getFlightClass() %></h5>
            </div>

        </div>
        <div class="ticketPreviewTable2 ">
            <h3> Boarding Pass</h3>

            <label for=" airportFROM ">FROM</label>
            <h3 class="airportFROM " >  <%= flight.getAirport().getAirportCodename() %>  </h3>

                <label for="airportTO ">TO</label>
                <h3 class="airportTO " ><%= flight.getDestinationAirport().getAirportCodename() %></h3>
                    <br/><br/><br/>
                    <label for="airline ">AIRLINE</label>
                    <h4 class="airline "> <%=flight.getAirline().getAirlineCodename() %></h4>
                        <br/><br/><br/>

                        <label for="name ">NAME</label>
                        <h5 class="name " > John Doe</h5>
                        <br/><br/><br/>
                        <label for="flightID ">FLIGHT ID</label>
                        <h5 class="flightID " ><%= flight.getFlight_id() %></h5>
                        <br/><br/><br/>
                        <label for="date ">DATE</label>
                        <h5 class="DATE" ><%= flight.getDateOfFlight().getTime() %></h5>
                        <br/><br/><br/>
                        <label for="seat ">SEAT</label>
                        <h5 class="seat " >  <%= flight.getSeatRow() %></h5>

                        <label for="row ">ROW</label>
                        <h5 class="row " ><%= flight.getSeatNumber() %></h5>

        </div>




    </div>



    <div class="ticketReservation">

        <div class="ticketReservationForm">

            <form class="ticketReservationForm" action="#" method="GET">

                <label for="flightID">Flight ID:</label><br>
                <input type="text" name="flightID" /><br/><br>

                <label for="name">Name:</label><br>
                <input type="text" name="name" /> <br/><br>


                <label for="seatRow">SEAT ROW:</label><br>
                <input type="text" name="seatRow" /> <br/><br/>

                <label for="seatNumber">SEAT NUMBER:</label><br>
                <input type="text" name="seatRow" /> <br/><br/>

                <input class="buttonform" type="submit" value="Create" />

            </form>


        </div>

        <div class="ticketReservationSeatPreview">

            <img id="seatIcon" src="img/icons/seaticon.png">

        </div>


    </div>



</body>

</html>