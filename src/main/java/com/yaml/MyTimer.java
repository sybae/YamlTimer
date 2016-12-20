package com.yaml;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class MyTimer {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AtomicInteger loopCounter = new AtomicInteger();
	
	@Autowired
	private StopWatch watch;
	
	@Value("${spring.task.name}")
	private String taskNamePrefix;
	
	@Value("${spring.timerName}")
	private String timerName;
	
	@PostConstruct
	public void init() {
		logger.info("## Turn on : " + timerName);
		watch.start();
	}
	
	@Scheduled(fixedDelayString = "${spring.task.fixedDelay}")
	public void tick() throws InterruptedException {
		watch.stop();
		logger.info("## " + watch.prettyPrint());
		String taskName = taskNamePrefix + "-" + String.valueOf(loopCounter.getAndIncrement());
		watch.start(taskName);
	}
	
	@Bean
	public StopWatch watch() {
		return new StopWatch();
	}
}