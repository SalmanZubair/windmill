package com.prolim.windMill.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.prolim.windMill.utility.TokenManagementImpl;

@SuppressWarnings({ "deprecation", "resource" })
@Repository
public class WindFarmDaoImpl implements WindFarmDao {

	final static Logger logger = Logger.getLogger(WindFarmDaoImpl.class);

	static JSONObject tokenObj = TokenManagementImpl.getToken();

	static String token = tokenObj.get("access_token").toString();

	public String getWindFarmEnergy(String windFarmId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Energy Trend for WindMill Id  :  ");

		String url = "https://knt68ql4e9.execute-api.us-east-1.amazonaws.com/getFarmData/" + windFarmId + "/daily";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		try {
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println(result.toString());
		return result.toString();
	}

	
	public String getFarmEnergyWindspeed(String windFarmId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Energy Trend for WindMill Id  :  ");

		String url = "https://2s6yffyx49.execute-api.us-east-1.amazonaws.com/getFarmEnergyWindspeed/" + windFarmId;

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		try {
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println(result.toString());
		return result.toString();
	}
	
	
	public String getWindmillAlertCountForWindFarm(String windFarmId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Energy Trend for WindMill Id  :  ");

		String url = "https://odoqbp0on2.execute-api.us-east-1.amazonaws.com/getAlertWindmillCount/" + windFarmId;

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		try {
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println(result.toString());
		return result.toString();
	}



	
	public String getWindFarmLocation() throws Exception {

		logger.info("In DAO Layer calling API to fetch all the location of WindMill. ");

		String url = "https://242xbcosfj.execute-api.us-east-1.amazonaws.com/getWindFarmLocationInfo";
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if (Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
			logger.info("Token still valid");
			request.addHeader("Authorization", token);
		} else {
			logger.info("Token Expired requesting new one");
			tokenObj = TokenManagementImpl.getToken();
			token = tokenObj.get("access_token").toString();
			request.addHeader("Authorization", token);
		}

		try {
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println(result.toString());
		return result.toString();
	}


	@Override
	public String getFarmMetaData(String windFarmId) throws Exception {
		logger.info("In DAO Layer calling API to fetch all the location of WindMill. ");

		String url = "https://r2ur4aax23.execute-api.us-east-1.amazonaws.com/getFarmMetaInfo/" + windFarmId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if (Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
			logger.info("Token still valid");
			request.addHeader("Authorization", token);
		} else {
			logger.info("Token Expired requesting new one");
			tokenObj = TokenManagementImpl.getToken();
			token = tokenObj.get("access_token").toString();
			request.addHeader("Authorization", token);
		}

		try {
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println(result.toString());
		return result.toString();
	}


	@Override
	public String getTopFiveWindmillEnergy(String windFarmId) throws Exception {
		logger.info("In DAO Layer calling API to fetch Top 5 performing Windmill Details ");

		String url = "https://t6nx1kkoad.execute-api.us-east-1.amazonaws.com/getTopFiveWindmillEnergy/" + windFarmId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if (Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
			logger.info("Token still valid");
			request.addHeader("Authorization", token);
		} else {
			logger.info("Token Expired requesting new one");
			tokenObj = TokenManagementImpl.getToken();
			token = tokenObj.get("access_token").toString();
			request.addHeader("Authorization", token);
		}

		try {
			response = client.execute(request);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		System.out.println(result.toString());
		return result.toString();
	}
}
