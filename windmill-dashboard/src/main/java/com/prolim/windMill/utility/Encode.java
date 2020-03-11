package com.prolim.windMill.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Encode {

	public String getToken() {

		String originalInput = "prolim-assettracking-1.0.0:fFAE9XO2RrHfQYA4rkvZMl9w8zdewMvAKNnBSuFbx84";
		Base64 base64 = new Base64();
		String encodedString = new String(base64.encode(originalInput.getBytes()));

		System.out.println(encodedString);

		String url = "https://gateway.eu1.mindsphere.io/api/technicaltokenmanager/v3/oauth/token";
	
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		StringEntity params = null;
		try {
			params = new StringEntity("{\r\n  \"appName\": \"assettracking\",\r\n  \"appVersion\": \"1.0.0\",\r\n  \"hostTenant\":\"prolim\",\r\n\"userTenant\":\"prolim\"\r\n}");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		request.addHeader("X-SPACE-AUTH-KEY", "Basic " + encodedString);
		request.addHeader("Content-Type", "application/json");
		request.addHeader("User-Agent", "PostmanRuntime/7.11.0");
		request.addHeader("Accept", "*/*");
		request.addHeader("Cache-Control", "no-cache");
		request.addHeader("Postman-Token", "6e7e378e-7700-404f-8008-899f82d2112e,6a788f2f-c06c-4894-99e6-64fe121ff7da");
		request.addHeader("Host", "gateway.eu1.mindsphere.io");
		request.addHeader("cookie", "SESSION=85270b7f-afb7-4b0c-97fc-be6a2f68f7bf");
		request.addHeader("accept-encoding", "gzip, deflate");
		request.addHeader("Connection", "keep-alive");
		request.addHeader("cache-control", "no-cache");
		
		BufferedReader rd = null;
		HttpResponse response;		
		StringBuffer result = new StringBuffer();
		JSONObject json = new JSONObject();
		try {
			request.setEntity(params);
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
				
			}
			System.out.println("fdsdh    " + result);
			
			
			  JSONParser parser = new JSONParser(); 
			  json = (JSONObject) parser.parse(result.toString()); 
			  System.out.println("Result :  " + json.get("access_token"));
			 
			
		} catch (IOException e) {
				e.printStackTrace();
			
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return json.get("access_token").toString();
	}

public static void main(String[] args) {
	new Encode().getToken();
	
}
}
