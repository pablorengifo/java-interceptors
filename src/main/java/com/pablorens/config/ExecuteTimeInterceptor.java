package com.pablorens.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(ExecuteTimeInterceptor.class);

	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler)
	    throws Exception {
		
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
	
		if(method.equals("GET")){
			@SuppressWarnings("unchecked")
			LinkedHashMap<String,Object> restOfTheUrl =  (LinkedHashMap<String,Object>) request.getAttribute(
			        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			String id = (String)restOfTheUrl.get("id");
			logger.info("map       =====>" + restOfTheUrl);
			logger.info("id        =====>" + id);
		}else if(method.equals("POST")){
			
			
		}
		return true;
	}

	//after the handler is executed
	public void postHandle(
		HttpServletRequest request, HttpServletResponse response,
		Object handler, ModelAndView modelAndView)
		throws Exception {

		logger.info("======================postHandle method=========================");
		long startTime = (Long)request.getAttribute("startTime");
		
		long endTime = System.currentTimeMillis();
		logger.info("endTime =====>" + endTime);
		long executeTime = endTime - startTime;

		//modified the exisitng modelAndView
		logger.info("executeTime =====>" + executeTime);

		//log it
		if(logger.isDebugEnabled()){
		   logger.debug("[" + handler + "] executeTime : " + executeTime + "ms");
		}
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		
		System.out.println("---Request Completed---");
	}
}
