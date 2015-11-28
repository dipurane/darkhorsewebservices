package com.synerzip.billfold.util;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SMSUtil {

	private String smsURL = "http://bulksms.mysmsmantra.com:8080/WebSMS/SMSAPI.jsp?username=drane&password=123redknee&sendername=netsms&mobileno=";

	public String sendActivationCodeSMS(String mobileNo){
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		int randomPIN = (int)(Math.random()*9000)+1000;
		String activation = String.valueOf(randomPIN);
		String queryString = smsURL + mobileNo +"&message=Hi,Your Billfold Activation code is "+activation;
		HttpEntity<String> entity = new HttpEntity<String>("parameters", null);
		restTemplate.exchange(queryString,HttpMethod.GET,entity,String.class);
		return activation;
		
	}
}
