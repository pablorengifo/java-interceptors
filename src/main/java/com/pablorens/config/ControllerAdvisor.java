package com.pablorens.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@ControllerAdvice(annotations = RestController.class)
public class ControllerAdvisor {
	
	private static final Logger logger = Logger.getLogger(ControllerAdvisor.class);
	
	@ModelAttribute
    public void addAttributes(HttpServletRequest request, HttpServletResponse response,Model model, 
    		@RequestBody String requestString, @RequestHeader(value = "User-Agent") String userAgent) {
        // do whatever you want to do on the request body and header. 
        // with request object you can get the request method and request path etc.
		
		Map<String, String> map = new HashMap<String, String>();
		String pathInfo = request.getRequestURI();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		
		String method = request.getMethod();
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		logger.info("======================preHandle method=========================");
		logger.info("method    =====>" + method);
		logger.info("startTime =====>" + startTime);
		logger.info("ID        =====>" + pathInfo);
		logger.info("header    =====>" + map);
	
		
		@SuppressWarnings("unchecked")
		LinkedHashMap<String,Object> restOfTheUrl =  (LinkedHashMap<String,Object>) request.getAttribute(
			        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String id = (String)restOfTheUrl.get("id");
		logger.info("map       =====>" + restOfTheUrl);
		logger.info("id        =====>" + id);
		
        System.out.println("requestString" + requestString);
        System.out.println("userAgent" + userAgent);
        
		
	}

}
