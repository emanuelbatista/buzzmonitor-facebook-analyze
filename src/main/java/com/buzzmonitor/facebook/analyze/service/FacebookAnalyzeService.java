package com.buzzmonitor.facebook.analyze.service;

import static com.buzzmonitor.facebook.analyze.util.Constants.ACCESS_TOKEN;
import static com.buzzmonitor.facebook.analyze.util.Constants.GRAPH_API_BASE_URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.buzzmonitor.facebook.analyze.dto.ListDTO;
import com.buzzmonitor.facebook.analyze.dto.response.PostDTO;
import com.buzzmonitor.facebook.analyze.model.Post;
import com.buzzmonitor.facebook.analyze.repository.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service to handle Facebook datas from Graph API
 * 
 * @author emanuel
 *
 */
@Service
public class FacebookAnalyzeService {

	private static final String TIME_ZONE_DEFAULT = "+0000";
	private static final int AMOUNT_LIMIT_PAGE = 100;
	private static final String DATA_FORMAT_DEFAULT = "yyyyMMdd";

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Consume the page posts according to with parameter lastDays and save them
	 * in a base data.
	 * 
	 * @param lastDays
	 *            - amount days previous
	 * @param page
	 *            - ID or name page to get the posts
	 * @return amount posts got.
	 */
	public int consumePosts(int lastDays, String page) {
		LocalDateTime date = LocalDate.now(ZoneOffset.of(TIME_ZONE_DEFAULT)).atStartOfDay();
		LocalDateTime dateLastDays = date.minusDays(lastDays);
		long milliSecounds = dateLastDays.toInstant(ZoneOffset.of(TIME_ZONE_DEFAULT)).getEpochSecond();

		ResponseEntity<ListDTO<PostDTO>> responseEntity = this.restTemplate.exchange(
				GRAPH_API_BASE_URL + "/{page}/posts?limit={limit}&since={since}&access_token={access_token}",
				HttpMethod.GET, null, new ParameterizedTypeReference<ListDTO<PostDTO>>() {
				}, page, AMOUNT_LIMIT_PAGE, (int) milliSecounds, ACCESS_TOKEN);

		ListDTO<PostDTO> listDTO = responseEntity.getBody();
		int count = 0;
		boolean hasNext = false;
		do {
			List<PostDTO> postsDTO = listDTO.getData();

			List<Post> posts = postsDTO.stream().map(postDTO -> {
				Post post = new Post();
				BeanUtils.copyProperties(postDTO, post);
				return post;
			}).collect(Collectors.toList());

			this.postRepository.save(posts);

			count += postsDTO.size();
			if (hasNext = (listDTO.getPaging() != null && listDTO.getPaging().getNext() != null)) {
				responseEntity = this.restTemplate.exchange(listDTO.getPaging().getNext(), HttpMethod.GET, null,
						new ParameterizedTypeReference<ListDTO<PostDTO>>() {
						}, page, AMOUNT_LIMIT_PAGE, (int) milliSecounds, ACCESS_TOKEN);

				listDTO = responseEntity.getBody();
			}
		} while (hasNext);
		return count;
	}

	/**
	 * it get all posts save in base data
	 * 
	 * @param sinceData
	 *            - data since interval in yyyyMMdd format. ex. 20170101
	 * @param untilData
	 *            - data until interval in yyyyMMdd format. ex. 20171231
	 * @return Post list
	 */
	public String getPosts(String sinceData, String untilData) {
		OffsetDateTime since = OffsetDateTime.of(
				LocalDate.parse(sinceData, DateTimeFormatter.ofPattern(DATA_FORMAT_DEFAULT)), LocalTime.MIN,
				OffsetDateTime.now().getOffset());
		OffsetDateTime until = OffsetDateTime.of(
				LocalDate.parse(untilData, DateTimeFormatter.ofPattern(DATA_FORMAT_DEFAULT)), LocalTime.MAX,
				OffsetDateTime.now().getOffset());
		//
		List<Post> posts = this.postRepository.findAllBySinceDataAndUntilData(since, until);
		try {
			return objectMapper.writeValueAsString(posts);
		} catch (JsonProcessingException e) {
			return null;
		} finally {

		}
	}

}
