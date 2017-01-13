package com.pablorens.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	@RequestMapping("/starter")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
