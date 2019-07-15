package com.auth0.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.auth0.ads")
@EnableJpaRepositories("com.auth0.ads")
class AdsApplication {

	public static void main(String[] args)

	{
		SpringApplication.run(AdsApplication.class, args);
	}

}
