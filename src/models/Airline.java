package models;

public class Airline {

	private String airlineCodename;
	private String airlineCallsign;
	private String airlineCountry;
	
	
	public Airline (String airlineCodename, String airlineCallsign, String airlineCountry) {
		
		this.airlineCodename = airlineCodename;
		this.airlineCallsign = airlineCallsign;
		this.airlineCountry = airlineCountry;
	}


	public String getAirlineCodename() {
		return airlineCodename;
	}


	public void setAirlineCodename(String airlineCodename) {
		this.airlineCodename = airlineCodename;
	}


	public String getAirlineCallsign() {
		return airlineCallsign;
	}


	public void setAirlineCallsign(String airlineCallsign) {
		this.airlineCallsign = airlineCallsign;
	}


	public String getAirlineCountry() {
		return airlineCountry;
	}


	public void setAirlineCountry(String airlineCountry) {
		this.airlineCountry = airlineCountry;
	}


	@Override
	public String toString() {
		return "\n|Airline codename :  " + airlineCodename + "\n Airline callsign :  " + airlineCallsign
				+ "\n Airline country :  " + airlineCountry + "|\n";
	}
	
	
}
