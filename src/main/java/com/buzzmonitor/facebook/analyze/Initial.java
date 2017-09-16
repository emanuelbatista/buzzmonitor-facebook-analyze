package com.buzzmonitor.facebook.analyze;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Initial {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Initial.class).web(false).run(args);
	}
}
