package com.animore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AnimoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimoreApplication.class, args);
	}

}
