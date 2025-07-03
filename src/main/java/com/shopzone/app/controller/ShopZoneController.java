package com.shopzone.app.controller;

import java.util.concurrent.TimeUnit;

//import org.jbpm.services.api.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class ShopZoneController {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
//	@Autowired
//	private ProcessService processService;

	@GetMapping("/test-redis")
	public String testRedis() {
	    redisTemplate.opsForValue().set("hello", "redis server works", 10, TimeUnit.SECONDS);
	    return redisTemplate.opsForValue().get("hello");
	}
	
}
