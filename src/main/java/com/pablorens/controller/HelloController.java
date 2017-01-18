package com.pablorens.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/starter/{id}")
    @ResponseBody
    String home2(@PathVariable Integer id) {
        return "Hello World 00>>>> " + id;
    }
	
	@RequestMapping(value="/starter/save/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<Map<String,String>> home3Post(@RequestBody Map<String,String> map,@PathVariable Integer id) {
        try {
        	String s = (String)map.get("id");
            System.out.println("[ID] = "+s);
            map.put("date", "HOLAAAAAa");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
    }

}
