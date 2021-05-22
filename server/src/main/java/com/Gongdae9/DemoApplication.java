package com.Gongdae9;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		log.info("2021.4.29 - logback 적용");
		log.info("2021.5.22 - spring security 적용");
	}
}
