package models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import database.FlightDatabase;

public class Flight {
	private ArrayList<Seat> listOfSeats = new ArrayList<Seat>();

	private Airline airline;
	private Airport airport;
	private Airport destinationAirport;
	private String flightClass;
	private Calendar dateOfFlight;
	private char seatRow;
	private int seatNumber;
	private double flightPrice;
	private int flightId = FlightDatabase.generateFlightId();

	public Flight(int flightId, Airline airline, Airport airport, Airport destinationAirport, String flightClass,
			Calendar dateOfFlight, char seatRow, int seatNumber, double flightPrice) {

		this.airline = airline;
		this.airport = airport;
		this.destinationAirport = destinationAirport;
		this.flightClass = flightClass;
		this.dateOfFlight = dateOfFlight;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.flightPrice = flightPrice;
		this.flightId = flightId;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public  int getFlight_id() {
		return flightId;
	}

	public  void setFlight_id(int flight_id) {
		this.flightId = flight_id;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public Calendar getDateOfFlight() {
		return dateOfFlight;
	}

	public Timestamp getDateOfFlightInDateTime(Calendar dateOfFlight) {
		Timestamp timestamp = new Timestamp(dateOfFlight.getTimeInMillis());
		return timestamp;
	}

	public void setDateOfFlight(Calendar dateOfFlight) {
		this.dateOfFlight = dateOfFlight;
	}

	public char getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(char seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public double getFlightPrice() {
		return flightPrice;
	}

	public void setFlightPrice(double flightPrice) {
		this.flightPrice = flightPrice;
	}

	public ArrayList<Seat> getListOfSeats() {
		return listOfSeats;
	}

	public void addToListOfSeats(Seat seat) {

		listOfSeats.add(seat);
	}

	@Override
	public String toString() {
		return "\n\n\n" + "\n Flight ID: " + flightId + " |\n Airline  :  "  + airline + "\n Airport  :  " + airport
				+ "\n Destination airport  :  " + destinationAirport + "\n Flight class  :  " + flightClass
				+ "\n Date of flight  :  " + dateOfFlight.getTime() + "\n Maximum seat row  :  " + seatRow
				+ "\n Number of seats per row :  " + seatNumber + "\n Flight price  :  " + flightPrice + "KM|\n\n\n --------------------------------- \n";
	}

	public void setListOfSeats(ArrayList<Seat> listOfSeats) {
		this.listOfSeats = listOfSeats;
	}

}
