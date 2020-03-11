package com.prolim.windMill.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class TokenManagementImpl /*implements TokenManagement*/ {

//	@Override
	@SuppressWarnings("unchecked")
	public static JSONObject getToken() {

		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("3u194c0n93uevblhp5j2oecut7",
				"7pl4mevkr4iad5sc08p7kdb64mnq81cm35pp9oeljdhfjmdvc78");
		provider.setCredentials(AuthScope.ANY, credentials);

		HttpClient accessTokeClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();

		HttpPost postReq = new HttpPost(
				"https://productapi1.auth.us-east-1.amazoncognito.com/oauth2/token?grant_type=client_credentials&scope=product-api/dynamo_read");
		postReq.setHeader("Content-Type", "application/x-www-form-urlencoded");
		postReq.addHeader("Authorization",
				"Basic M3UxOTRjMG45M3VldmJsaHA1ajJvZWN1dDc6N3BsNG1ldmtyNGlhZDVzYzA4cDdrZGI2NG1ucTgxY20zNXBwOW9lbGpkaGZqbWR2Yzc4");

		BufferedReader rd1 = null;
		HttpResponse response1;
		StringBuffer result1 = new StringBuffer();
		JSONObject jsonAccess = null;
		try {
			response1 = accessTokeClient.execute(postReq);
			rd1 = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
			String line = "";
			while ((line = rd1.readLine()) != null) {
				result1.append(line);
			}
			JSONParser parser = new JSONParser();
			jsonAccess = (JSONObject) parser.parse(result1.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		jsonAccess.put("expires_in", /* (Long) jsonAccess.get("expires_in") */ 60 + Instant.now().getEpochSecond());
		
//		Long currentEpoch = Instant.now().toEpochMilli();
		return jsonAccess;
	}

}
