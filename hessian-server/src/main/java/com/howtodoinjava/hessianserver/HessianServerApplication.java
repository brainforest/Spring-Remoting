package com.howtodoinjava.hessianserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class HessianServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HessianServerApplication.class, args);
	}
}
