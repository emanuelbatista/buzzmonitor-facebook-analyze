package com.buzzmonitor.facebook.analyze.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookAnalyzeServiceTest {

	@Autowired
	private FacebookAnalyzeService service;

	// @Before
	public void before() {
	}

	@Test
	public void consumePostsSuccessful() {
		this.service.consumePosts(1400, "998036520310119");
	}

	@Test
	public void getPostsBetweenDatesSucessful() {
		System.out.println(this.service.getPosts("20170101", "20171231"));
	}
}
