package org.appops.demo.common.dto;

import java.io.Serializable;
import java.util.Date;

import jsinterop.annotations.JsType;

@SuppressWarnings("serial")
@JsType (namespace = "JsHost")
public class Country implements Serializable{

	private String country;
	private String city;
	private double latitude;
	private double longitude;
	private Date createdOn ;
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Country(String state, String region, double latitude, double longitude){
		this.country = state;
		this.city = region;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Date fetchSomeDate(){
		Date dt = new Date() ;
		return dt ;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
