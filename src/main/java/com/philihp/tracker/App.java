package com.philihp.tracker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {
	public static void main(String[] args) throws Exception {
		Connection con = null;
		try {
			URL url = new URL("${tracker.url}");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			Reader reader = new InputStreamReader(conn.getInputStream());
	
			Gson gson = new GsonBuilder().registerTypeAdapter(Squawk.class, new SquawkDeserializer()).create();
			Squawk squawk = gson.fromJson(reader, Squawk.class);
	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("${jdbc.server}", "${jdbc.user}", "${jdbc.pass}");
			PreparedStatement stmt = con.prepareStatement("INSERT INTO squawk (lat,lng,ts) VALUES (?,?,?)");
			stmt.setFloat(1, squawk.getLat());
			stmt.setFloat(2, squawk.getLng());
			stmt.setTimestamp(3, new Timestamp(squawk.getTimestamp().getTime()));
			if(stmt.executeUpdate() != 1) {
				throw new Exception("ERROR: A squawk was not lodged");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			if(con != null) con.close();
		}
	
	}
}
