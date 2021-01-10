package models;

import java.sql.Timestamp;
import java.util.Calendar;

public class FlightTicket {
	
	
	private int flightId;
	private Airline airline;
	private Airport airport;
	private Airport destinationAirport;
	private String flightClass;
	private Calendar dateOfFlight;
	private char seatRow;
	private int seatNumber;
	private double flightPrice;
	private String buyersName;
	
	
	public FlightTicket (int flightId, Airline airline, Airport airport, Airport destinationAirport, String flightClass, Calendar dateOfFlight, char seatRow, int seatNumber, double flightPrice, String buyersName) {
		this.flightId = flightId;
		this.airline = airline;
		this.airport = airport;
		this.destinationAirport = destinationAirport;
		this.flightClass = flightClass;
		this.dateOfFlight = dateOfFlight;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.flightPrice = flightPrice;
		this.buyersName = buyersName;
		
	}


	public int getFlightId() {
		return flightId;
	}


	public void setFlightId(int flightId) {
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


	public String getFlightClass() {
		return flightClass;
	}


	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}


	public Calendar getDateOfFlight() {
		return dateOfFlight;
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


	public String getBuyersName() {
		return buyersName;
	}


	public void setBuyersName(String buyersName) {
		this.buyersName = buyersName;
	}
	
	public Timestamp getDateOfFlightTicketInDateTime(Calendar dateOfFlight) {
		Timestamp timestamp = new Timestamp(dateOfFlight.getTimeInMillis());
		return timestamp;
	}
}
