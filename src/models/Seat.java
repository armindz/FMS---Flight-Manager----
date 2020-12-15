package models;

public class Seat {
	private int flightId;
	private char seatRow;
	private int seatNumber;
	private boolean isSeatAvailable = true;


public Seat (int flightId, char seatRow, int seatNumber, boolean isSeatAvailable) {
	
	this.flightId = flightId;
	this.seatRow = seatRow;
	this.seatNumber = seatNumber;
	this.isSeatAvailable = isSeatAvailable;
}


public int getFlightId() {
	return flightId;
}


public void setFlightId(int flightId) {
	this.flightId = flightId;
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


public boolean isSeatAvailable() {
	return isSeatAvailable;
}


public void setSeatAvailable(boolean isSeatAvailable) {
	this.isSeatAvailable = isSeatAvailable;
}


@Override
public String toString() {
	return "|Row   :  " + seatRow + "\t| Number  :  " + seatNumber + "\t| " + isSeatAvailable + "|\n";
}


}