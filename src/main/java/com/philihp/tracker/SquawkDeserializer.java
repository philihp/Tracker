package com.philihp.tracker;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SquawkDeserializer implements
		JsonDeserializer<Squawk> {

	public Squawk deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		
		Squawk squawk = new Squawk();

		JsonObject root = json.getAsJsonObject();
		JsonObject update = root.get("update").getAsJsonObject();

		long cts = update.get("cts").getAsLong();
		long ts = update.get("ts").getAsLong();
		
		squawk.setTimestamp(new Date((ts-cts)*1000));
		squawk.setLat(update.get("lat").getAsFloat());
		squawk.setLng(update.get("lng").getAsFloat());
		
		return squawk;
	}
	
}
