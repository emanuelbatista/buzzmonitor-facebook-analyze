package com.buzzmonitor.facebook.analyze.service;

import static com.buzzmonitor.facebook.analyze.util.Constants.ACCESS_TOKEN;
import static com.buzzmonitor.facebook.analyze.util.Constants.GRAPH_API_BASE_URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.buzzmonitor.facebook.analyze.dto.ListDTO;
import com.buzzmonitor.facebook.analyze.factory.RestTemplateDefault;
import com.buzzmonitor.facebook.analyze.model.Post;

/**
 * Service to handle Facebook datas from Graph API
 * 
 * @author emanuel
 *
 */
public class FacebookAnalyzeService {

	private static final String TIME_ZONE_DEFAULT = "+0000";
	private static final int AMOUNT_LIMIT_PAGE = 100;

	private RestTemplate restTemplate;

	public FacebookAnalyzeService() {
		super();
		this.restTemplate = RestTemplateDefault.getInstance();
	}

	public int consumerPosts(int lastDays, String page) {
		LocalDateTime date = LocalDate.now().atStartOfDay();
		LocalDateTime dateLastDays = date.minusDays(lastDays);
		long milliSecounds = dateLastDays.toInstant(ZoneOffset.of(TIME_ZONE_DEFAULT)).getEpochSecond();
		ListDTO<List<Post>> listDTO = this.restTemplate.getForObject(
				GRAPH_API_BASE_URL + "/{page}/posts?limit={limit}&since={since}&access_token={access_token}",
				ListDTO.class, page, AMOUNT_LIMIT_PAGE, (int) milliSecounds, ACCESS_TOKEN);
		int count = 0;
		boolean hasNext = false;
		do {
			List<Post> posts = listDTO.getData();
			count += posts.size();
			if (hasNext = (listDTO.getPaging() != null && listDTO.getPaging().getNext() != null)) {
				listDTO = this.restTemplate.getForObject(listDTO.getPaging().getNext(), ListDTO.class);
			}
		} while (hasNext);
		return count;
	}

}
