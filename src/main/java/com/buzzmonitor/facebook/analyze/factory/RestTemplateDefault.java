package com.buzzmonitor.facebook.analyze.factory;

import org.springframework.web.client.RestTemplate;

public class RestTemplateDefault {

	private static RestTemplate restTemplate;

	public static RestTemplate getInstance() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		return restTemplate;
	}
}
