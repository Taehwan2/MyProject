package com.example.service.prag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

/* Todo 이부분은 BaseEntity쓰기위한 부분 */
@EnableJpaAuditing
public class PragApplication {

	public static void main(String[] args) {
		SpringApplication.run(PragApplication.class, args);
	}

}
