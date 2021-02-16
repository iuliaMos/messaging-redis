package com.example.messagingredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class MessagingRedisApplication {

	private static final Logger log = LoggerFactory.getLogger(MessagingRedisApplication.class);

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext ctx = SpringApplication.run(MessagingRedisApplication.class, args);

		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
		Receiver receiver = ctx.getBean(Receiver.class);

		while(receiver.getCount() == 0) {
			log.info("Sending message...");
			template.convertAndSend("chat", "Hello from redis!");
			Thread.sleep(500l);
		}

		System.exit(0);
	}

}
