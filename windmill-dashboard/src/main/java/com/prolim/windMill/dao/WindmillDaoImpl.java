package com.prolim.windMill.dao;
/**
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.prolim.windMill.utility.TokenManagementImpl;

/**
 * @author SalmanZubair
 *		
 */


@SuppressWarnings({ "deprecation", "resource" })
public class WindmillDaoImpl implements WindmillDao {

	final static Logger logger = Logger.getLogger(WindmillDaoImpl.class);
	
	static JSONObject tokenObj = TokenManagementImpl.getToken();
	
	static String token = tokenObj.get("access_token").toString();
	
	
	
	
	public String getLocation(String farmId) throws Exception {

		logger.info("In DAO Layer calling API to fetch all the location of WindMill. ");

		String url = "https://pf10twbn27.execute-api.us-east-1.amazonaws.com/getWindmillMetaAlertInfo/" + farmId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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


	/**
	 * 
	 */
	
	public String getPower(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Power Trend for WindMill Id  :  " + windmillId);

		String url = "https://jfls4zmbm7.execute-api.us-east-1.amazonaws.com/getPowerEfficiency/" + windmillId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	
	/**
	 * 
	 */
	public String getPowerTrend(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Power Trend for WindMill Id  :  " + windmillId);

		String url = "https://sseewisrx3.execute-api.us-east-1.amazonaws.com/power_trend/" + windmillId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	
	/**
	 * 
	 */
	public String getWindSpeed(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch current Wind Speed for WindMill Id  :  " + windmillId);

		String url = "https://s7xq67og07.execute-api.us-east-1.amazonaws.com/windmillinfocurrentavg/" + windmillId
				+ "/wind_speed";
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	
	/**
	 * 
	 */
	public String getWindTrend(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Wind Speed Trend for WindMill Id  :  " + windmillId);

		String url = "https://dcq7pyakrd.execute-api.us-east-1.amazonaws.com/windmillinfo/" + windmillId
				+ "/wind_speed";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	public String getEnergyTrend(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Energy Trend for WindMill Id  :  " + windmillId);

		String url = "https://mnxjvp0zgf.execute-api.us-east-1.amazonaws.com/energyProductionDaily/" + windmillId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	/**
	 * 
	 */
	public String getTemperatures(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch current Temperatures for WindMill Id  :  " + windmillId);

		String url = "https://02wtzyhyx7.execute-api.us-east-1.amazonaws.com/windmilltemperatures/" + windmillId;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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
		return result.toString();
	}

	/**
	 * 
	 */
	public String getAlerts(String windmillId) throws Exception {

		logger.info("In DAO Layer calling API to fetch Alerts for WindMill Id  :  " + windmillId);

		String url = "https://sxhwjbjlph.execute-api.us-east-1.amazonaws.com/alertbywindmillid/" + windmillId;

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	/**
	 * 
	 */
	public String updateAlerts(String windmillId, String alertId) throws Exception {

		logger.info("In DAO Layer calling API to update Alerts for WindMill Id  :  " + windmillId + "\t" + alertId);

		String url = "https://88dvhh93sk.execute-api.us-east-1.amazonaws.com/updateAlertResolved/" + windmillId + "/"
				+ alertId;

		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		BufferedReader rd = null;
		HttpResponse response;
		StringBuffer result = new StringBuffer();
		if(Instant.now().getEpochSecond() < (Long) tokenObj.get("expires_in")) {
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

	public static void main(String[] args) {
		// try {
		//
		// System.out.println("\n\n The next json from generated_power is:\n");
		// System.out.println(new WindmillDaoImpl().getPower("wm101"));
		//
		// System.out.println("\n\n The next json from power Trend is:\n");
		// System.out.println(new WindmillDaoImpl().getPowerTrend("wm101"));
		//
		// System.out.println("\nThe next json from windspeed is:\n");
		// System.out.println(new WindmillDaoImpl().getWindSpeed("wm101"));
		//
		// System.out.println("\n\n The next json from Wind Speed Trend is:\n");
		// System.out.println(new WindmillDaoImpl().getWindTrend("wm101"));
		//
		// System.out.println("\n\n The next json from Energy Trend is:\n");
		// System.out.println(new WindmillDaoImpl().getEnergyTrend("wm101"));
		//
		// System.out.println("\n The next json from getTemperatures is:\n");
		// System.out.println(new WindmillDaoImpl().getTemperatures("wm101"));
		//
		// System.out.println("\n\n The next json from get_alerts is:\n");
		// System.out.println(new WindmillDaoImpl().getAlerts("wm101"));
		//
		// System.out.println("\n\n The next json from updatealerts is:\n");
		// System.out.println(new WindmillDaoImpl().updateAlerts("wm101",
		// "1576"));
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		try {
			new WindmillDaoImpl().getWindTrend("wm101");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
