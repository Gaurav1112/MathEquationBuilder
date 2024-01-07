package com.project.MathEquationBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.MathEquationBuilder")
public class MathEquationBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathEquationBuilderApplication.class, args);
	}

}
