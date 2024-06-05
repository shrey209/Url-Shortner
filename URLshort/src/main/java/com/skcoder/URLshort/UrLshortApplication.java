package com.skcoder.URLshort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UrLshortApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrLshortApplication.class, args);
	}

}
