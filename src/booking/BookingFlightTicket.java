package booking;

import java.util.ArrayList;

import database.SeatDatabase;

import management.FlightManagementSystem;

import models.Seat;

public class BookingFlightTicket {

	FlightManagementSystem flightms = new FlightManagementSystem();
	SeatDatabase seatdb = new SeatDatabase();
	private ArrayList<Seat> listOfSeats = flightms.getListOfSeats();
	

	public void bookAFlight(int flightId, String flightClass, char seatRow, int seatNumber) {

		if (isSeatAvailable(flightId, seatRow, seatNumber)) {
			flightms.markSeatAsUnavailable(flightId, seatRow, seatNumber);
			System.out.println("Successfully booked!");

		} else {
			System.out.println("Problem with booking a flight. Seat is not available. Be aware of typing mistakes! ");
		}
	}

	public boolean isSeatAvailable(int flightId, char seatRow, int seatNumber) {

		ArrayList <Seat> listOfSeats = seatdb.fetchDatabaseContent();
		for (int i = 0; i < listOfSeats.size(); i++) {

			int flightIdFromList = listOfSeats.get(i).getFlightId();
			char seatRowFromList = listOfSeats.get(i).getSeatRow();
			int seatNumberFromList = listOfSeats.get(i).getSeatNumber();
			boolean isSeatAvailableFromList = listOfSeats.get(i).isSeatAvailable();

			if ((flightIdFromList == flightId) && (seatRow == seatRowFromList) && (seatNumber == seatNumberFromList)
					&& (isSeatAvailableFromList)) {
				return true;
			}
		}

		return false;
	}

}
