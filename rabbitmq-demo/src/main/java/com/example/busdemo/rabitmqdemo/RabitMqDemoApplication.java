package com.example.busdemo.rabitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class RabitMqDemoApplication{
	//非泛型类的泛型方法
	public static  <T> List<T> aaa(Class<T> aa){
		return new ArrayList<T>();
	}

	public static  <K,V>  Map<K,V> aa(){
		return new HashMap<K,V>();
	}
	public static List<?> a(List<?> aa){
		return aa;
	}

	public static void main(String[] args) {

		SpringApplication.run(RabitMqDemoApplication.class, args);
	}
}
