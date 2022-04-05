package com.arturofilio.codebin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodebinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodebinApplication.class, args);
		System.out.println("Executando en puerto 8080");
	}

}
