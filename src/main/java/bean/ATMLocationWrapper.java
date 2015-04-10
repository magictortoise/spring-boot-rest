package bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ATMLocationWrapper {
	
	private String street;
	private String housenumber;
	private String postalcode;
	private String city;
	private GeoLocationWrapper location;
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public GeoLocationWrapper getLocation() {
		return location;
	}

	public void setLocation(GeoLocationWrapper location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "[ " + housenumber + " " + street + " " + city + " " + postalcode  + " ]";
	}

}