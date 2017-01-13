package com.pablorens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pablorens.controller",
								"com.pablorens.config"})
public class SampleController {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
