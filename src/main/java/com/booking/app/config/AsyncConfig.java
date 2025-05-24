package com.booking.app.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean(name="taskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor exe= new ThreadPoolTaskExecutor();
		exe.setCorePoolSize(5);
		exe.setMaxPoolSize(10);
		exe.setQueueCapacity(50);
		exe.setThreadNamePrefix("Async-");
		exe.initialize();
		return exe;
		
	}
}
