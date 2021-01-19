package com.gstar;

import com.gstar.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

 	public static void main(String[] args) {

		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
		ac.setAllowBeanDefinitionOverriding(true);
		//刷新上下文
		ac.refresh();
		User user = (User) ac.getBean("user");
		System.out.println("结果是"+user);

	}
}
