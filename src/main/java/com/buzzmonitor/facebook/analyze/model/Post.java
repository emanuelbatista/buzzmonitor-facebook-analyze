package com.buzzmonitor.facebook.analyze.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity Post - Represent the data Post from Facebook
 * 
 * @author emanuel
 *
 */
@Entity
public class Post {

	@Id
	private String id;
	private OffsetDateTime createdTime;
	@Column(columnDefinition = "text")
	private String message;

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

	@Override
	public String toString() {
		return "Post [id=" + id + ", createdTime=" + createdTime + ", message=" + message + "]";
	}

}
