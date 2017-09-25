
package com.sibisoft.api.samplecode;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

public class HttpClient {


	protected HttpHost targetHost = null;
	protected DefaultHttpClient httpclient = null;
	protected BasicHttpContext ctx = null;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHORIZATION_KEY_NAME = "AUTHORIZATION";
	private static final String TIMESTAMP_KEY_NAME = "TIMESTAMP";
	

	protected static String protocol;
	protected static String host;
	protected static Integer port;
	protected static String appContext;
	protected static String webServiceURI;
	public static String completeURL;
	protected static String validationCode;

	static {

		// Setting Default values
		protocol = "http";
		host = "175.45.99.26";
		port = 8080;
		appContext = "/northstar";
		webServiceURI = "/api/jsonws";
		validationCode = "0";
		completeURL = protocol + "://" + host + ":" + port + appContext + "/";

	}

	public String getFullWebServicesURI(String uri) {
		return appContext + webServiceURI + uri;
	}

	public static String getCompleteURL() {
		return completeURL;
	}

	
	public HttpClient() {

		super();

		try {
			targetHost = new HttpHost(host, port, protocol);
			System.out.println("HttpHost : protocol : "+protocol+", host : "+host+", port : "+port);
			httpclient = new DefaultHttpClient();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String excuteHttpGetRequest(String requestURI)
		throws Exception {

		String jsonResponse = new String();

		try {
			String fullWebServiceURI = getFullWebServicesURI(requestURI);
			System.out.println("fullWebServiceURI : "+fullWebServiceURI);

			HttpGet httpGet = new HttpGet(fullWebServiceURI);
			System.out.println("Made HttpGet request with URI : "+fullWebServiceURI);
			String authorizationKey = getAuthorizationKey();
			System.out.println("Made Authorization key : "+authorizationKey);
			httpGet.setHeader(AUTHORIZATION_PROPERTY, authorizationKey);
			System.out.println("Set Authorization key in Request Header");
			
			HttpResponse httpResponse = httpclient.execute(targetHost, httpGet, ctx);
			System.out.println("request Sent");
			// Converting response to JSON
			InputStream is= httpResponse.getEntity().getContent();
			System.out.println("Got response ");
			jsonResponse = convertInputStreamToString(is);
			System.out.println("Converting it to string "+jsonResponse);
		}
		catch (HttpHostConnectException ex) {
			ex.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return jsonResponse;
	}
	public static String convertInputStreamToString(InputStream is) {

		String encoding = "UTF-8";
		String str = new String();

		try {
			str = org.apache.commons.io.IOUtils.toString(is, encoding);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return str;
	}
	protected HttpResponse excuteHttpGetRequestFile(String requestURI)
		throws Exception {

		try {
			String fullWebServiceURI = getFullWebServicesURI(requestURI);

			HttpGet httpGet = new HttpGet(fullWebServiceURI);

			// Setting Security Headers to access Northstar Web Services
			httpGet.setHeader(AUTHORIZATION_PROPERTY, getAuthorizationKey());

			HttpResponse resp = httpclient.execute(targetHost, httpGet, ctx);

			return resp;

		}
		catch (Exception ex) {
			throw ex;
		}
	}

	public String excuteHttpPostRequest(String requestURI, String jsonString)
		throws Exception {

		String jsonResponse = new String();

		try {
			String fullWebServiceURI = getFullWebServicesURI(requestURI);
			System.out.println("fullWebServiceURI : "+fullWebServiceURI);
			System.out.println("request payload : "+jsonString);

			HttpPost httpPost = new HttpPost(fullWebServiceURI);
			System.out.println("Made HttpPost request with URI : "+fullWebServiceURI);
			String authorizationKey = getAuthorizationKey();
			System.out.println("Made Authorization key : "+authorizationKey);
			httpPost.setHeader(AUTHORIZATION_PROPERTY, authorizationKey);
			System.out.println("Set Authorization key in Request Header");
			
			StringEntity input = new StringEntity(jsonString);
			input.setContentType("application/json");
			System.out.println("set Content Type as JSON");
			httpPost.setEntity(input);
			System.out.println("set payload "+input);
			HttpResponse httpResponse = httpclient.execute(targetHost, httpPost, ctx);
			System.out.println("request Sent");
			
			InputStream is= httpResponse.getEntity().getContent();
			System.out.println("Got response ");
			jsonResponse = convertInputStreamToString(is);
			System.out.println("Converting it to string "+jsonResponse);
		}
		catch (HttpHostConnectException ex) {
				ex.printStackTrace();
				throw ex;
		}
		catch (Exception ex) {
			throw ex;
		}

		return jsonResponse;
	}
	// Setting up the Security Header for HTTP GET Request
	private String getAuthorizationKey() {

		String encryptedAuthKey = null;

		try {

			// Authorization: AUTHORIZATIONv@lid@ti@nc@deTIMESTAMP07/29/2016 01:34:19 PM
			String authKey = AUTHORIZATION_KEY_NAME + validationCode + TIMESTAMP_KEY_NAME +getTodayUTCDate("MM/dd/yyyy hh:mm:ss aaa");
			System.out.println("--- Authorization: " + authKey);

			 ClassLoader classLoader = this.getClass().getClassLoader();
			 URL url = classLoader.getResource("public.key");
			 System.out.println("--- "+url); // I get a nullpointerexception here!
			 String publicKeyFilename = url.getPath();
			 System.out.println("--- "+publicKeyFilename); // I get a nullpointerexception here!

			PublicKey key = getPublicKey(publicKeyFilename);
			encryptedAuthKey = encrypt(authKey, key);
			System.out.println("--- Encrypted Autorization: " + encryptedAuthKey);

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return encryptedAuthKey;
	}
	public static String encrypt(String text, PublicKey key) {

		String cipherText = null;
		try {
	    	final Cipher cipher = Cipher.getInstance("RSA");
	    	cipher.init(Cipher.ENCRYPT_MODE, key);
	    	byte[] encryptedBytes = cipher.doFinal(text.getBytes());
            cipherText = Base64.encodeBase64String(encryptedBytes);// new String(Base64.getEncoder().encode(encryptedBytes));

		}
		catch (Exception e) {
			e.printStackTrace();
//			throw e;
		}

		return cipherText;
	}
	public static PublicKey getPublicKey(String publicKeyFilename) throws Exception {

		// Load the public key bytes
	    FileInputStream fis = new FileInputStream(publicKeyFilename);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    int theByte = 0;
	    while ((theByte = fis.read()) != -1)
	    {
	      baos.write(theByte);
	    }
	    fis.close();

	    byte[] keyBytes = baos.toByteArray();
	    baos.close();

	    // Turn the encoded key into a real RSA public key.
	    // Public keys are encoded in X.509.
	    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    PublicKey publicKey = keyFactory.generatePublic(keySpec);
	    
		return publicKey;
	}
	
	  public static String getTodayUTCDate(String format) {
		  final SimpleDateFormat sdf = new SimpleDateFormat(format);
	      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	      final String utcDate = sdf.format(new Date());
	      	        
	      return utcDate;
	  }
}
