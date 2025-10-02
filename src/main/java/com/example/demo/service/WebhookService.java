package com.example.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

	private final RestTemplate restTemplate;
	
	public WebhookService(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
	
	public void sendWebhook(String url, Map<String , Object> payload)
	{
		restTemplate.postForObject(url, payload, String.class);
	}
	
	
}
