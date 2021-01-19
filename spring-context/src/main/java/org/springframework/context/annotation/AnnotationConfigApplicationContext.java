/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.util.function.Supplier;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * 独立应用程序上下文，接受<em>组件类</em>作为输入&mdash;
 * *特别是{@link Configuration @Configuration}注释的类，而且是纯格式的
 * * {@link org.springframework.stereotype。Component @Component}类型和JSR-330兼容
 * *使用{@code javax的类。注入}注释。
 * *
 * * <p>允许使用{@link #register(Class…)}逐个注册类
 * *以及使用{@link #scan(String…)}进行类路径扫描。
 * *
 * * <p>对于多个{@code @Configuration}类，{@link Bean @Bean}方法
 * *在后面的类中定义的将覆盖在前面的类中定义的。这可以
 * *通过一个额外的函数有意地覆盖某些bean定义
 * *类。
 *
 * <p>See {@link Configuration @Configuration}'s javadoc for usage examples.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 3.0
 * @see #register
 * @see #scan
 * @see AnnotatedBeanDefinitionReader
 * @see ClassPathBeanDefinitionScanner
 * @see org.springframework.context.support.GenericXmlApplicationContext
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

	private final AnnotatedBeanDefinitionReader reader;

	private final ClassPathBeanDefinitionScanner scanner;


	/**
	 *创建一个新的需要填充的AnnotationConfigApplicationContext
	 * *通过{@link #register}调用，然后手动{@linkplain #refresh refresh}。
	 */
	public AnnotationConfigApplicationContext() {
		//AnnotationConfigApplicationContext实现了BeanDefinitionRegistry
		//由注解生成的方式 注解处理器
		this.reader = new AnnotatedBeanDefinitionReader(this);
		//由xml配置文件生成的方式
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * 使用给定的DefaultListableBeanFactory创建一个新的AnnotationConfigApplicationContext。
	 * @param beanFactory 用于此上下文的DefaultListableBeanFactory实例
	 */
	public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		super(beanFactory);
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 *创建一个新的AnnotationConfigApplicationContext，派生bean定义
	 * *从给定的组件类和自动刷新上下文。
	 * @param componentClasses 一个或多个组件类;例如,
	 * {@link Configuration @Configuration} classes
	 */
	public AnnotationConfigApplicationContext(Class<?>... componentClasses) {
		//调用构造方法
		//初始化AnnotatedBeanDefinitionReader
		//初始化ClassPathBeanDefinitionScanner
		this();
		//是否允许循环依赖 false不允许
//		setAllowCircularReferences(false);
		//注册配置类，因为配置需要解析，一般不需要自己扫描
		//beanDefinitionMap.put("appConfig",bean);
		register(componentClasses);
		refresh();
	}

	/**
	 * 创建一个新的AnnotationConfigApplicationContext，扫描组件
	 * *在给定的包中，为这些组件注册bean定义，
	 * *和自动刷新上下文。
	 * @param basePackages 要扫描组件类的包
	 */
	public AnnotationConfigApplicationContext(String... basePackages) {
		this();
		scan(basePackages);
		refresh();
	}


	/**
	 * 将给定的自定义{@code环境}传播到底层
	 * {@link AnnotatedBeanDefinitionReader} and {@link ClassPathBeanDefinitionScanner}.
	 */
	@Override
	public void setEnvironment(ConfigurableEnvironment environment) {
		super.setEnvironment(environment);
		this.reader.setEnvironment(environment);
		this.scanner.setEnvironment(environment);
	}

	/**
	 * 提供一个定制的{@link BeanNameGenerator}用于{@link AnnotatedBeanDefinitionReader}
	 * *和/或{@link ClassPathBeanDefinitionScanner}(如果有)。
	 * * <p>默认为{@link AnnotationBeanNameGenerator}。
	 * * <p>对该方法的任何调用必须发生在对{@link #register(Class…)}的调用之前
	 * *和/或{@link #scan(String…)}。
	 * @see AnnotatedBeanDefinitionReader#setBeanNameGenerator
	 * @see ClassPathBeanDefinitionScanner#setBeanNameGenerator
	 * @see AnnotationBeanNameGenerator
	 * @see FullyQualifiedAnnotationBeanNameGenerator
	 */
	public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
		this.reader.setBeanNameGenerator(beanNameGenerator);
		this.scanner.setBeanNameGenerator(beanNameGenerator);
		getBeanFactory().registerSingleton(
				AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
	}

	/**
	 * 为注册的组件类设置{@link ScopeMetadataResolver}。
	 * * <p>默认为{@link AnnotationScopeMetadataResolver}。
	 * * <p>对该方法的任何调用必须发生在对{@link #register(Class…)}的调用之前
	 * *和/或{@link #scan(String…)}。
	 */
	public void setScopeMetadataResolver(ScopeMetadataResolver scopeMetadataResolver) {
		this.reader.setScopeMetadataResolver(scopeMetadataResolver);
		this.scanner.setScopeMetadataResolver(scopeMetadataResolver);
	}


	//---------------------------------------------------------------------
	// 实现AnnotationConfigRegistry
	//---------------------------------------------------------------------

	/**
	 * 注册一个或多个要处理的组件类。
	 * 注意，必须按上下文顺序调用{@link #refresh()}
	 * *来完全处理新类。
	 * 一个或多个组件类&mdash;例如,
	 * {@link Configuration @Configuration} classes
	 * @see #scan(String...)
	 * @see #refresh()
	 */
	@Override
	public void register(Class<?>... componentClasses) {
		//提示至少指定一个组件类
		Assert.notEmpty(componentClasses, "At least one component class must be specified");
		//处理组件类
		this.reader.register(componentClasses);
	}

	/**
	 * 在指定的基本包内执行扫描。
	 * 注意，必须按上下文顺序调用{@link #refresh()}
	 * *来完全处理新类。
	 * @param basePackages 要扫描组件类的包
	 * @see #register(Class...)
	 * @see #refresh()
	 */
	@Override
	public void scan(String... basePackages) {
		Assert.notEmpty(basePackages, "At least one base package must be specified");
		this.scanner.scan(basePackages);
	}


	//---------------------------------------------------------------------
	// 调整超类registerBean调用到AnnotatedBeanDefinitionReader
	//---------------------------------------------------------------------

	@Override
	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass,
			@Nullable Supplier<T> supplier, BeanDefinitionCustomizer... customizers) {

		this.reader.registerBean(beanClass, beanName, supplier, customizers);
	}

}
