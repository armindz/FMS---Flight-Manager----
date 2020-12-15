package models;

public class Airport {

	private String airportCodename;
	private String airportFullname;
	private String airportType;
	private String airportCity;
	private String airportCountry;
	
	
	public Airport (String airportCodename, String airportFullname, String airportType, String airportCity, String airportCountry) {
		
		this.airportCodename = airportCodename;
		this.airportFullname = airportFullname;
		this.airportType = airportType;
		this.airportCity = airportCity;
		this.airportCountry = airportCountry;
		
	}


	public String getAirportCodename() {
		return airportCodename;
	}


	public void setAirportCodename(String airportCodename) {
		this.airportCodename = airportCodename;
	}


	public String getAirportFullname() {
		return airportFullname;
	}


	public void setAirportFullname(String airportFullname) {
		this.airportFullname = airportFullname;
	}


	public String getAirportType() {
		return airportType;
	}


	public void setAirportType(String airportType) {
		this.airportType = airportType;
	}


	public String getAirportCity() {
		return airportCity;
	}


	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}


	public String getAirportCountry() {
		return airportCountry;
	}


	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}


	@Override
	public String toString() {
		return "\n | Airport codename :  " + airportCodename + "\n AirportFullname :  " + airportFullname + "\n Airport type :  "
				+ airportType + "\n Airport city :  " + airportCity + "\n Airport country  :  " + airportCountry + "|\n";
	}
	
	
	
}
