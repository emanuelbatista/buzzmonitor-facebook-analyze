package com.buzzmonitor.facebook.analyze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.buzzmonitor.facebook.analyze.service.FacebookAnalyzeService;

@Component
public class Application implements CommandLineRunner {

	@Autowired
	private FacebookAnalyzeService service;

	@Override
	public void run(String... args) throws Exception {
		this.service.consumePosts(3, "998036520310119");

	}

}
