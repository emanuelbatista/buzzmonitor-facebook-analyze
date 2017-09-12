package com.buzzmonitor.facebook.analyze.service;

import static com.buzzmonitor.facebook.analyze.util.Constants.GRAPH_API_BASE_URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.web.client.RestTemplate;

import com.buzzmonitor.facebook.analyze.dto.ListDTO;
import com.buzzmonitor.facebook.analyze.factory.RestTemplateDefault;
import com.buzzmonitor.facebook.analyze.model.Post;

public class FacebookAnalyzeService {

	private RestTemplate restTemplate;

	public FacebookAnalyzeService() {
		super();
		this.restTemplate = RestTemplateDefault.getInstance();
	}

	public void getPosts(int lastDays, String page) {
		LocalDateTime date = LocalDate.now().atStartOfDay();
		LocalDateTime dateLastDays = date.minusDays(lastDays);
		long milli = dateLastDays.toInstant(ZoneOffset.of("+0000")).toEpochMilli();
		ListDTO<Post> response = this.restTemplate.getForObject(GRAPH_API_BASE_URL + "/" + page, ListDTO.class);
	}

}
