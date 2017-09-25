package com.sibisoft.api.samplecode;



public class Starter {

	/** 
	 	****************************************
		set following values in HttpClient
		****************************************

	 	protocol = "http";
		host = "[IP OR DOMAIN NAME]";
		port = [PORT];
		appContext = "/[APPLICATION NAME LIKE northstar, northstar-training]";
		webServiceURI = "/api/jsonws";
		validationCode = "[VALIDATION CODE PROVIDED BY NORTHSTAR]";
		

		*****************************************
		Control Flow
		************GET Request Sample **********
		HttpHost : protocol : http, host : [IP OR DOMAIN NAME], port : [PORT]
		fullWebServiceURI : /northstar/api/jsonws/member/get-member/1
		Made HttpGet request with URI : /northstar/api/jsonws/member/get-member/1
		--- Authorization: AUTHORIZATION[VALIDATION-CODE-HERE]TIMESTAMP08/31/2016 12:30:14 PM
		--- file:.../bin/public.key
		--- .../bin/public.key
		--- Encrypted Autorization: q1rsTewpKveOOaqm1w39xNiTQ8QYldFZtuUcHeBWavW57cAFi1UHZc0mucCPTdWNacV560pKAxEQgVMoDa3AyOo3YX4PfSPasowRL4Zh7I1MtkXbufGmmcBolleZSV7U/GmoTHEEXsYC0oQ984wjABUZ9ayC4ulS3FREvdCaQng=
		Made Authorization key : q1rsTewpKveOOaqm1w39xNiTQ8QYldFZtuUcHeBWavW57cAFi1UHZc0mucCPTdWNacV560pKAxEQgVMoDa3AyOo3YX4PfSPasowRL4Zh7I1MtkXbufGmmcBolleZSV7U/GmoTHEEXsYC0oQ984wjABUZ9ayC4ulS3FREvdCaQng=
		Set Authorization key in Request Header
		request Sent
		Got response 
		Converting it to string {"responseCode":"20","responseMessage":"","response":{"memberNumber":"35","encryptedMemberNumber":"PpuZmY1lKSY=","memberId":1,"firstName":"The","middleName":"","lastName":"American Club of Hong Kong","displayName":"American Club of Hong Kong, The","onlineName":"The American Club of Hong Kong","memberSinceDate":"12/07/1989","businessPhone":"","businessPhoneExt":0,"cellPhone":"","homePhone":"","faxPhone":"","occupation":0,"occupationName":"","gender":"Male","maritalStatus":"Single","primaryEmail":"","dateOfBirth":null,"pictureImageURL":"servlet/PictureServlet?column=picture&memberID=1&0.4849342728917849","pictureImageBase64DataURI":null,"onlinePictureImageURL":"servlet/PictureServlet?column=picture&memberID=1&0.5277072821628096&pictureForConnect=1","memberTypeId":4,"memberTypeName":"DAC News Recipients","email":"","phone":"","secondaryEmail":"","memberDescription":"","billToMemberId":1,"device":0,"deviceToken":"","employerId":0,"employerName":"","companyId":1,"siteCompanyId":1,"spouseName":null,"addresses":null,"suffixName":"","prefixName":"","nickName":""}}
		{"responseCode":"20","responseMessage":"","response":{"memberNumber":"35","encryptedMemberNumber":"PpuZmY1lKSY=","memberId":1,"firstName":"The","middleName":"","lastName":"American Club of Hong Kong","displayName":"American Club of Hong Kong, The","onlineName":"The American Club of Hong Kong","memberSinceDate":"12/07/1989","businessPhone":"","businessPhoneExt":0,"cellPhone":"","homePhone":"","faxPhone":"","occupation":0,"occupationName":"","gender":"Male","maritalStatus":"Single","primaryEmail":"","dateOfBirth":null,"pictureImageURL":"servlet/PictureServlet?column=picture&memberID=1&0.4849342728917849","pictureImageBase64DataURI":null,"onlinePictureImageURL":"servlet/PictureServlet?column=picture&memberID=1&0.5277072821628096&pictureForConnect=1","memberTypeId":4,"memberTypeName":"DAC News Recipients","email":"","phone":"","secondaryEmail":"","memberDescription":"","billToMemberId":1,"device":0,"deviceToken":"","employerId":0,"employerName":"","companyId":1,"siteCompanyId":1,"spouseName":null,"addresses":null,"suffixName":"","prefixName":"","nickName":""}}
		************End**********
		
		
		************POST Request Sample **********
		HttpHost : protocol : http, host : [IP OR DOMAIN NAME], port : [PORT]
		fullWebServiceURI : /northstar/api/jsonws/member/get-valid-member
		request payload : {memberNumber : "108" , password : "108"}
		Made HttpPost request with URI : /northstar/api/jsonws/member/get-valid-member
		--- Authorization: AUTHORIZATION[VALIDATION-CODE-HERE]TIMESTAMP08/31/2016 12:30:15 PM
		--- file:.../bin/public.key
		--- .../bin/public.key
		--- Encrypted Autorization: CtTc7egD6+fSWHXSTADDoCBOB8RSfquMQICd4KsaarX0sGxaC+Edf719KlMvUvyuTuitO4JLq36m6zX0swIw1lnRaDrJThY16ZucYTwYVkg5bTYehdOe1kGQMYthVUfYNgLqOEnuNlAoYSTzLvy2tEybgo3AFdw58G1gwgd4k4Q=
		Made Authorization key : CtTc7egD6+fSWHXSTADDoCBOB8RSfquMQICd4KsaarX0sGxaC+Edf719KlMvUvyuTuitO4JLq36m6zX0swIw1lnRaDrJThY16ZucYTwYVkg5bTYehdOe1kGQMYthVUfYNgLqOEnuNlAoYSTzLvy2tEybgo3AFdw58G1gwgd4k4Q=
		Set Authorization key in Request Header
		set Content Type as JSON
		set payload org.apache.http.entity.StringEntity@52194e8b
		request Sent
		Got response 
		Converting it to string {"responseCode":"00","responseMessage":"User Name or Password Incorrect!","response":null}
		{"responseCode":"00","responseMessage":"User Name or Password Incorrect!","response":null}
		************End**********
	 */
	public static void main(String[] args) {
		
		Starter starter = new Starter();
		System.out.println("1 --------------");
		System.out.println(starter.getMemberGet(1));///member/get-member/
		System.out.println("2 --------------");
		System.out.println(starter.getMemberPOST("108","108"));///member/get-member/
		System.out.println("3 --------------");
		System.out.println(starter.getEventsWithReservation(100));//events/get-upcoming-events-with-member-reservations
		System.out.println("4 --------------");
		System.out.println(starter.getMemberRoster());///member/get-member-roster
		System.out.println("5 --------------");
		System.out.println(starter.getUpcommingEvents(100));///events/get-upcoming-events/
		System.out.println("6 --------------");
		System.out.println(starter.getEvents());///events/get-events
		System.out.println("7 --------------");
	}

	public String getMemberGet(int memberId){
		String getMemberByIdURI = "/member/get-member/" + memberId;		
		
		HttpClient httpClient = new HttpClient();
		try {			
			return httpClient.excuteHttpGetRequest(getMemberByIdURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getMemberPOST(String memberNumber,String memberPassword){
		String requestURI = "/member/get-valid-member";		
		String jsonString = "{memberNumber : \""+memberNumber+"\" , password : \""+memberPassword+"\"}";
		HttpClient httpClient = new HttpClient();
		try {			
			return httpClient.excuteHttpPostRequest(requestURI, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getMemberRoster(){
		String requestURI = "/member/get-member-roster";		
		String jsonString = "{\"query\": \"A\",	\"pageSize\": 25,\"pageNumber\": 1}";
		HttpClient httpClient = new HttpClient();
		try {			
			return httpClient.excuteHttpPostRequest(requestURI, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getEventsWithReservation(int memberId){
		String getMemberByIdURI = "/events/get-upcoming-events-with-member-reservations/" + memberId;		
		
		HttpClient httpClient = new HttpClient();
		try {			
			return httpClient.excuteHttpGetRequest(getMemberByIdURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getUpcommingEvents(int memberId){
		String getMemberByIdURI = "/events/get-upcoming-events/" + memberId;		
		
		HttpClient httpClient = new HttpClient();
		try {			
			return httpClient.excuteHttpGetRequest(getMemberByIdURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/** get events with criteria.
	 * Event Statuses as follow
	 * DRAFT = 2
     * OPEN_FOR_RESERVATIONS = 1
     * CLOSED_FOR_RESERVATION = 3
     * BILLED = 4
     * CANCELLED = 5
     * SOFT_CLOSE = 6
     * NO_REGISTRATION_REQUIRED = 7
     * 
	 * @return
	 */
	public String getEvents(){
		String requestURI = "/events/get-events";
		String jsonString =  "{\"fromDate\" : \"09/14/2016\",\"toDate\" : \"11/30/2016\", \"statuses\" : [1,2,3,4,5,6,7]}";

		HttpClient httpClient = new HttpClient();
		try {			
			return httpClient.excuteHttpPostRequest(requestURI, jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}