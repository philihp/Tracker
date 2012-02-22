package com.philihp.tracker;

import java.util.Date;

public class Squawk {
	private Float lat;
	private Float lng;
	private Date timestamp;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public String toString() {
		return lat + ", " + lng + " @ " + timestamp;
	}
}
