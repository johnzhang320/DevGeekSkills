package com.apple.cert.rest;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


 
 
public class TestGemini {
 
	 public static final String REST_SERVICE_URI = "https://api.sandbox.gemini.com/v1/marketdata/BTCUSD";
 
	  private final String URL = "https://api.sandbox.gemini.com/v1/marketdata/BTCUSD";
	  private final String APIKEY = "-- redacted --";
	  private final String PAYLOAD = "-- redacted --";
	  private final String SIGNATURE = "-- redacted --";
	  private final String CONTEXT_TYPE = "Mozilla/5.0";
	  private final String CONTEXT_LENGTH = "Mozilla/5.0";
	  public static void main(String[] args) throws Exception {
		TestGemini http = new TestGemini();
	  //  System.out.println(http.sendRequest("POST", "/deposits/crypto", "{ \"currency\":\"ETH\", \"method\":\"ethereum\" }"));
	    //System.out.println(http.sendRequest("POST","/deposits/crypto","{ \"currency\":\"BTC\", \"method\":\"bitcoin\" }"));
	    //System.out.println(http.sendRequest("POST","/deposits/crypto","{ \"currency\":\"BCH\", \"method\":\"bitcoincash\" }"));
	  }

	/*  private String sendRequest(String method, String path, String body) throws Exception {
	    String localUrl;
	    if (method.equals("GET"))
	      localUrl = URL + path + body;
	    else
	      localUrl = URL + path;

	    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
	    String sign = createSign(timestamp, method, path, body);

	    URL url = new URL(localUrl);
	    HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod(method);
	    con.setRequestProperty("Content-Type", "text/plain");
	    con.setRequestProperty("X-GEMINI-APIKEY", accessKey);
	    con.setRequestProperty("X-GEMINI-PAYLOAD", sign);
	    con.setRequestProperty("X-GEMINI-SIGNATURE", passphrase);
	    con.setRequestProperty("AC-ACCESS-TIMESTAMP", timestamp);

	    if (method.equals("POST")) {
	      con.setRequestProperty("Content-type", "application/json");
	      con.setDoOutput(true);
	      OutputStream os = con.getOutputStream();
	      os.write(body.getBytes("UTF-8"));
	      os.close();
	    }
	    int responseCode = con.getResponseCode();

	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
	      response.append(inputLine);
	    }
	    in.close();
	    return response.toString();
	  }

	  private String createSign(String timestamp, String method, String path, String queryParameters)
	      throws NoSuchAlgorithmException, InvalidKeyException {
	    String queryArgs = timestamp + method + path + queryParameters;
	    Mac shaMac = Mac.getInstance("HmacSHA256");
	    SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
	    shaMac.init(keySpec);
	    final byte[] macData = shaMac.doFinal(queryArgs.getBytes());
	    return Base64.getEncoder().encodeToString(macData);
	  }*/
 
	 
}