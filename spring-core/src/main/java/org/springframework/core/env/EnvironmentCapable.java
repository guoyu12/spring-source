/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.core.env;

/**
 * 接口，该接口指示包含并公开{@link Environment}引用的组件。
 * *
 * 所有的Spring应用程序上下文都是可环境的，并且主要使用接口
 * *用于在接受BeanFactory的框架方法中执行{@code instanceof}检查
 * *为了进行交互，实例可能是也可能不是ApplicationContext实例
 * *使用环境，如果环境确实可用。
 * *
 * * <p>如前所述，{@link org.springframework.context。
 * ApplicationContext ApplicationContext}
 * *扩展EnvironmentCapable，从而公开{@link #getEnvironment()}方法;
 * 然而,
 * * {@link org.springframework.context。
 * ConfigurableApplicationContext ConfigurableApplicationContext}
 * *重新定义{@link org.springframework.context.ConfigurableApplicationContext#getEnvironment
 * * getEnvironment()}，并缩小签名以返回{@link ConfigurableEnvironment}。
 * *其效果是，环境对象在被访问之前都是“只读”的
 * 一个ConfigurableApplicationContext，在这一点上它也可以被配置。
 * *
 * Interface indicating a component that contains and exposes an {@link Environment} reference.
 *
 * <p>All Spring application contexts are EnvironmentCapable, and the interface is used primarily
 * for performing {@code instanceof} checks in framework methods that accept BeanFactory
 * instances that may or may not actually be ApplicationContext instances in order to interact
 * with the environment if indeed it is available.
 *
 * <p>As mentioned, {@link org.springframework.context.ApplicationContext ApplicationContext}
 * extends EnvironmentCapable, and thus exposes a {@link #getEnvironment()} method; however,
 * {@link org.springframework.context.ConfigurableApplicationContext ConfigurableApplicationContext}
 * redefines {@link org.springframework.context.ConfigurableApplicationContext#getEnvironment
 * getEnvironment()} and narrows the signature to return a {@link ConfigurableEnvironment}.
 * The effect is that an Environment object is 'read-only' until it is being accessed from
 * a ConfigurableApplicationContext, at which point it too may be configured.
 *
 * @author Chris Beams
 * @since 3.1
 * @see Environment
 * @see ConfigurableEnvironment
 * @see org.springframework.context.ConfigurableApplicationContext#getEnvironment()
 */
public interface EnvironmentCapable {

	/**
	 * 返回与此组件关联的{@link Environment}。
	 * Return the {@link Environment} associated with this component.
	 */
	Environment getEnvironment();

}
