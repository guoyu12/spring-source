/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * 允许自定义修改应用程序上下文的工厂钩子
 * * bean定义，适应上下文底层的bean属性值
 * * bean工厂。
 *
 * 对于针对系统管理员的自定义配置文件很有用
 * *覆盖在应用程序上下文中配置的bean属性。看到
 * * {@link PropertyResourceConfigurer}及其具体实现
 * *满足这种配置需求的开箱即用的解决方案。
 *
 *<p>A {@code BeanFactoryPostProcessor}可以与bean交互并修改bean
 * *定义，但从不使用bean实例。这样做可能会导致豆类过早
 * *实例化，破坏容器并导致意外的副作用。
 * 如果需要bean实例交互，考虑实现
 * * {@link BeanPostProcessor}代替。
 *
 * <h3>Registration</h3>
 * <p>一个{@code ApplicationContext}自动检测{@code BeanFactoryPostProcessor}
 * *在它的bean定义中的bean，并在创建任何其他bean之前应用它们。
 * *一个{@code BeanFactoryPostProcessor}也可以通过编程方式注册
 * *使用{@code ConfigurableApplicationContext}。
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanFactoryPostProcessor}
 * * {@code ApplicationContext}将根据
 * * {@link org.springframework.core。PriorityOrdered},
 * * {@link org.springframework.core。命令}语义。相比之下,
 * 以编程方式注册的bean
 * *与{@code ConfigurableApplicationContext}一起应用，顺序为
 * *注册;的实现所表达的任何排序语义
 * * {@code PriorityOrdered}或{@code Ordered}接口将被忽略
 * 以编程方式注册后处理器。此外,
 * * {@link org.springframework.core.annotation。Order @Order}注释不是
 * *考虑了{@code BeanFactoryPostProcessor} bean。
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 06.07.2003
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 *根据标准修改应用程序上下文的内部bean工厂
	 * *初始化。所有bean定义都已加载，但没有bean
	 * *还没有实例化。这允许重写或添加
	 * *属性，甚至急于初始化bean。
	 * @param beanFactory 应用程序上下文使用的bean工厂
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
