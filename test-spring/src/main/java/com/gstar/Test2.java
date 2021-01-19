package com.gstar;

import com.gstar.app.AppConfig;

import com.gstar.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test2 {

	public static void main(String[] args) {

		//初始化spring容器
		AnnotationConfigApplicationContext ac =
				new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = (UserService) ac.getBean("userService");
		System.out.println(userService.say());
	}
}
