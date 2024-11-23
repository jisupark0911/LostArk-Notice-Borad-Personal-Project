package com.example.LostArkNoticeBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing //JPA Auditing 기능활성 created_at의 자동 날짜 삽입때문에 필요
@SpringBootApplication
public class LostArkNoticeBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostArkNoticeBoardApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
