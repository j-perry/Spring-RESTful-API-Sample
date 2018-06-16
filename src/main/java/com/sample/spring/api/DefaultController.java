package com.sample.spring.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
	
	@RequestMapping(value="/",
		method = RequestMethod.GET)
	public String hello() {
		return "Hello";
	}
	
}