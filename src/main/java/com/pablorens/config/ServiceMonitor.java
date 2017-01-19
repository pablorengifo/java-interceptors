/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pablorens.config;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {
	
	@Around("execution(* com.pablorens.controller.HelloController.home3Post(java.util.Map,java.lang.Integer)) && args(..,map,id)")
	public Object logServiceMapAccess(ProceedingJoinPoint pjp,Map<String,String> map,Integer id) throws Throwable {
		System.out.println("REQUEST BODY        : "+map);
		System.out.println("PATH PARAM          : "+id);
		System.out.println("ProceedingJoinPoint : "+pjp.getClass());
		return pjp.proceed();
	}
}