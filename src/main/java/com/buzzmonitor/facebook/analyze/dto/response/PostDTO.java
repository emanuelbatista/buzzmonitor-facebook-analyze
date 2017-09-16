package com.buzzmonitor.facebook.analyze.dto.response;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {

	@JsonProperty(required = true)
	private String id;
	@JsonProperty(value = "created_time", required = true)
	private OffsetDateTime createdTime;
	private String message;
	private String story;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OffsetDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(OffsetDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

}
