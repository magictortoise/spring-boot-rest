package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class represents the entire JSON response
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapper {

	private ATMLocationWrapper address;
	private String type;
	
	public ATMLocationWrapper getAddress() {
		return address;
	}

	public void setAddress(ATMLocationWrapper address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}