package com.prolim.windMill.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Test {

		public static void main(String[] args) throws ParseException {
		  try {
			  
			  HttpClient client = new DefaultHttpClient();
			  HttpGet request = new HttpGet("https://sxhwjbjlph.execute-api.us-east-1.amazonaws.com/alertbywindmillid/wm103");
			  HttpResponse response = client.execute(request);

			  BufferedReader rd = new BufferedReader
			      (new InputStreamReader(
			      response.getEntity().getContent()));
			  String res="";
			  String line = "";
			  while ((line = rd.readLine()) != null) {
				res += line;
			  }			
			JSONParser parser = new JSONParser();
			JSONObject res1 = (JSONObject) parser.parse(res);
			System.out.println(res1.get("data").toString());


			client.getConnectionManager().shutdown();

		  } catch (ClientProtocolException e) {
		
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }	
	}
	
}
