package com.example.aspect;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class AspectApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("aop-developer-annotations-config.xml");
		Developer developer = (Developer) context.getBean("developer");
		developer.setName("Dmitriy");
		developer.setSpecialty("Programmer");
		String name = developer.getName();
		String specialty  = developer.getSpecialty();
	}

}

