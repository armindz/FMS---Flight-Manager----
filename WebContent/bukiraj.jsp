<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>FMS - Book a flight</title>
    <link rel="icon" href="img/icons/fmsround.png" type="image/x-icon">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
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

        <div class="ticketPreviewTable1">
       
            <h2>BOARDING PASS</h2>
            <div class="table1left">

                <label for="airportFROM">FROM</label>
                <h3 class="airportFROM" > SJJ </h3>



                <label for="name">NAME</label>
                <h5 class="name" > John Doe</h5>

                <label for="flightID">FLIGHT ID</label>
                <h5 class="flightID" > 0145</h5>

                <label for="date">DATE</label>
                <h5 class="DATE" > 22.5.2020</h5>
            </div>

            <div class="table1right">


                <label for="airportTO">TO</label>
                <h3 class="airportTO" > BGR</h3>

                <label for="airline">AIRLINE</label>
                <h4 class="airline"> WZZ</h4>

                    <label for="seat">SEAT</label>
                    <h5 class="seat" > C</h5>

                    <label for="row">ROW</label>
                    <h5 class="row" > 6</h5>

                    <label for="flightClass">FLIGHT CLASS</label>
                    <h5 class="flightClass" > ECONOMY</h5>
            </div>

        </div>
        <div class="ticketPreviewTable2">
            <h3> Boarding Pass</h3>

            <label for=" airportFROM">FROM</label>
            <h5 class="airportFROM" > SJJ </h5>

                <label for="airportTO">TO</label>
                <h5 class="airportTO" > BGR</h5>
                    <br/><br/><br/>
                    <label for="airline">AIRLINE</label>
                    <h5 class="airline"> WZZ</h5>
                        <br/><br/><br/>

                        <label for="name">NAME</label>
                        <h5 class="name" > John Doe</h5>
                        <br/><br/><br/>
                        <label for="flightID">FLIGHT ID</label>
                        <h5 class="flightID" > 0145</h5>
                        <br/><br/><br/>
                        <label for="date">DATE</label>
                        <h5 class="DATE" > 22.5.2020</h5>
                        <br/><br/><br/>
                        <label for="seat">SEAT</label>
                        <h5 class="seat" > C</h5>

                        <label for="row">ROW</label>
                        <h5 class="row"> 6</h5>

        </div>




    </div>



   


    



</body>

</html>