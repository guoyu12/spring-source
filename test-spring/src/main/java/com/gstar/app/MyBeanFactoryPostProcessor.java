//package com.gstar.app;
//
//import com.gstar.service.UserService;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//import org.springframework.stereotype.Component;
//
///**
// * 当前类属于扩展
// */
//@Component
//public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
//	@Override
//	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		//根据bean名称获取bean
//		GenericBeanDefinition indexService = (GenericBeanDefinition) beanFactory.getBeanDefinition("indexService");
//		//将indexService修改为UserService
//		indexService.setBeanClass(UserService.class);
//	}
//}
