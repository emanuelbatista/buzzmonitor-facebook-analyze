package com.buzzmonitor.facebook.analyze.service;

import org.junit.Before;
import org.junit.Test;

public class FacebookAnalyzeServiceTest {

	private FacebookAnalyzeService service;

	@Before
	public void before() {
		this.service = new FacebookAnalyzeService();
	}

	@Test
	public void responseSuccessfull() {
		this.service.consumerPosts(3, "998036520310119");
	}
}
