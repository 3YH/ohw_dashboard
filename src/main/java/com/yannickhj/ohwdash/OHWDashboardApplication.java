package com.yannickhj.ohwdash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OHWDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(OHWDashboardApplication.class, args);
	}
}
