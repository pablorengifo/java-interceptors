package com.pablorens.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(ExecuteTimeInterceptor.class);

	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler)
	    throws Exception {

		
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		logger.info("======================preHandle method=========================");
		logger.info("startTime =====>" + startTime);
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

}
