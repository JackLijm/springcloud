package com.example.busdemo.rabitmqdemo;

import com.example.rabitmqdemo.useramqd.AmqpSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//指定spring boot 的启动类
@SpringApplicationConfiguration(classes = RabitMqDemoApplication.class)
public class RabitMqDemoApplicationTests {

	@Autowired
	private AmqpSender amqpSender;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testHello(){
		amqpSender.send();
	}
}
