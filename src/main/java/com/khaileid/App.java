package com.khaileid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

//		String encoded= new BCryptPasswordEncoder().encode("1414");
//		System.out.println(encoded);

	}
}
