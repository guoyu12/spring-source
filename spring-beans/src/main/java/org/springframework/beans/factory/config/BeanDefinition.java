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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.AttributeAccessor;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * BeanDefinition描述一个bean实例，该实例具有属性值，
 * *构造函数参数值，和进一步的信息提供
 * *具体实现。
 * *
 * 这只是一个最小的接口:主要目的是允许
 * * {@link BeanFactoryPostProcessor}来内省和修改属性值
 * *和其他bean元数据。
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @since 19.03.2004
 * @see ConfigurableListableBeanFactory#getBeanDefinition
 * @see org.springframework.beans.factory.support.RootBeanDefinition
 * @see org.springframework.beans.factory.support.ChildBeanDefinition
 */
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
	 * 标准单例范围的范围标识符:{@value}。
	 * 注意扩展的bean工厂可能支持进一步的范围。
	 * @see #setScope
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
	 */
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
	 * 标准原型范围的范围标识符:{@value}。
	 * 注意扩展的bean工厂可能支持进一步的范围。
	 * @see #setScope
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
	 */
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	/**
	 * 指示{@code BeanDefinition}是主要部分的角色提示
	 * *有关的申请。通常对应于用户定义的bean。
	 */
	int ROLE_APPLICATION = 0;

	/**
	 * 指示{@code BeanDefinition}是支持的角色提示
	 * *某些较大配置的一部分，通常是外部配置
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 *{@code SUPPORT} bean被认为非常重要，值得注意
	 * *表示当更仔细地观察某一特定事物时
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition},
	 * 但在查看应用程序的总体配置时就不是这样了。
	 */
	int ROLE_SUPPORT = 1;

	/**
	 * 角色提示，指示{@code BeanDefinition}正在提供
	 * *完全是后台角色，与终端用户无关。这个提示是
	 * *在注册完全属于内部工作的bean时使用
	 * of a {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 */
	int ROLE_INFRASTRUCTURE = 2;


	// 修改属性

	/**
	 * 设置这个bean定义的父定义的名称(如果有的话)。
	 */
	void setParentName(@Nullable String parentName);

	/**
	 * 返回这个bean定义的父定义的名称(如果有的话)。
	 */
	@Nullable
	String getParentName();

	/**
	 *指定这个bean定义的bean类名。
	 * * <p>可以在bean工厂后处理时修改类名，
	 * *通常用解析后的类名替换原来的类名。
	 * @see #setParentName
	 * @see #setFactoryBeanName
	 * @see #setFactoryMethodName
	 */
	void setBeanClassName(@Nullable String beanClassName);

	/**
	 *返回此bean定义的当前bean类名。
	 * 注意，这不必是在运行时使用的实际类名
	 * *子定义覆盖/继承其父类名的情况。
	 * *此外，这可能只是工厂方法被调用的类，也可能是
	 * *在方法被调用的工厂bean引用时，即使是空的。
	 * *因此，do <i>不</i>认为这是在运行时确定的bean类型，但是
	 * 而只是在单独的bean定义级别上使用它进行解析。
	 * @see #getParentName()
	 * @see #getFactoryBeanName()
	 * @see #getFactoryMethodName()
	 */
	@Nullable
	String getBeanClassName();

	/**
	 * 覆盖此bean的目标范围，指定一个新的范围名称。
	 * @see #SCOPE_SINGLETON
	 * @see #SCOPE_PROTOTYPE
	 */
	void setScope(@Nullable String scope);

	/**
	 * 返回此bean的当前目标作用域的名称，
	 * *或{@code null}(如果还不知道)。
	 */
	@Nullable
	String getScope();

	/**
	 * 设置是否延迟初始化这个bean。
	 * * <p>如果{@code false}， bean将在启动时实例化
	 * *执行单例急切初始化的工厂。
	 */
	void setLazyInit(boolean lazyInit);

	/**
	 * 返回这个bean是否应该延迟初始化，即不
	 * *在启动时急于实例化。只适用于单例bean。
	 */
	boolean isLazyInit();

	/**
	 * 设置这个bean所依赖的被初始化的bean的名称。
	 * bean工厂将保证这些bean首先被初始化。
	 */
	void setDependsOn(@Nullable String... dependsOn);

	/**
	 * 返回此bean所依赖的bean名称。
	 */
	@Nullable
	String[] getDependsOn();

	/**
	 * 设置这个bean是否可以自动加载到其他bean中。
	 * 注意，这个标志被设计为只影响基于类型的自动装配。
	 * *它不影响显式的名称引用，甚至会被解析
	 * *如果指定的bean没有标记为自动装配候选。因此,
	 * 如果名称匹配，按名称自动装配将注入一个bean。
	 */
	void setAutowireCandidate(boolean autowireCandidate);

	/**
	 * 返回这个bean是否是自动加载到其他bean中的候选bean。
	 */
	boolean isAutowireCandidate();

	/**
	 * 设置此bean是否为主要自动装配候选对象。
	 * * <p>，如果该值恰好是{@code true}，用于多个bean中的一个
	 * *匹配候选人，它将成为决定胜负的关键因素。
	 */
	void setPrimary(boolean primary);

	/**
	 * 返回此bean是否是主要自动装配的候选对象。
	 */
	boolean isPrimary();

	/**
	 * 指定要使用的工厂bean(如果有的话)。
	 * *这是要调用指定工厂方法的bean的名称。
	 * @see #setFactoryMethodName
	 */
	void setFactoryBeanName(@Nullable String factoryBeanName);

	/**
	 *返回工厂bean名称(如果有的话)。
	 */
	@Nullable
	String getFactoryBeanName();

	/**
	 *指定工厂方法(如果有的话)。将调用此方法
	 * *构造函数参数，或者如果没有指定参数，则不带参数。
	 * *该方法将在指定的工厂bean上调用，如果有的话，
	 * *或者作为本地bean类上的静态方法。
	 * @see #setFactoryBeanName
	 * @see #setBeanClassName
	 */
	void setFactoryMethodName(@Nullable String factoryMethodName);

	/**
	 * 返回工厂方法(如果有的话)。
	 */
	@Nullable
	String getFactoryMethodName();

	/**
	 * 返回这个bean的构造函数参数值。
	 * 可以在bean工厂后处理期间修改返回的实例。
	 * @return the ConstructorArgumentValues object (never {@code null})
	 */
	ConstructorArgumentValues getConstructorArgumentValues();

	/**
	 * 返回是否为这个bean定义了构造函数参数值。
	 * @since 5.0.2
	 */
	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	/**
	 *返回应用于bean的新实例的属性值。
	 * 可以在bean工厂后处理期间修改返回的实例。
	 * @return the MutablePropertyValues object (never {@code null})
	 */
	MutablePropertyValues getPropertyValues();

	/**
	 * 返回是否为该bean定义了属性值。
	 * @since 5.0.2
	 */
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	/**
	 * 设置初始化器方法的名称。
	 * @since 5.1
	 */
	void setInitMethodName(@Nullable String initMethodName);

	/**
	 * 返回初始化器方法的名称。
	 * @since 5.1
	 */
	@Nullable
	String getInitMethodName();

	/**
	 * 设置销毁方法的名称。
	 * @since 5.1
	 */
	void setDestroyMethodName(@Nullable String destroyMethodName);

	/**
	 * 返回销毁方法的名称。
	 * @since 5.1
	 */
	@Nullable
	String getDestroyMethodName();

	/**
	 * 为这个{@code BeanDefinition}设置角色提示。提示作用
	 * *提供框架以及工具的指示
	 * *特定{@code BeanDefinition}的角色和重要性。
	 * @since 5.1
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	void setRole(int role);

	/**
	 * 获取这个{@code BeanDefinition}的角色提示。提示作用
	 * *提供框架以及工具的指示
	 * *特定{@code BeanDefinition}的角色和重要性。
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	int getRole();

	/**
	 * 设置这个bean定义的人类可读的描述。
	 * @since 5.1
	 */
	void setDescription(@Nullable String description);

	/**
	 * 返回这个bean定义的人类可读的描述。
	 */
	@Nullable
	String getDescription();


	// Read-only attributes

	/**
	 * 返回此bean定义的可解析类型，
	 * *基于bean类或其他特定元数据。
	 * 这通常在运行时合并bean定义上完全解决
	 * 但不一定在配置时间定义实例上。
	 * @return the resolvable type (potentially {@link ResolvableType#NONE})
	 * @since 5.2
	 * @see ConfigurableBeanFactory#getMergedBeanDefinition
	 */
	ResolvableType getResolvableType();

	/**
	 * 返回是否<b>单例</b>，带有一个共享实例
	 * @see #SCOPE_SINGLETON
	 */
	boolean isSingleton();

	/**
	 * 返回是否a <b>原型</b>，带有一个独立的实例
	 * *为每个调用返回。
	 * @since 3.0
	 * @see #SCOPE_PROTOTYPE
	 */
	boolean isPrototype();

	/**
	 * 返回这个bean是否“抽象”，也就是说，是否意味着不需要实例化。
	 */
	boolean isAbstract();

	/**
	 * 返回此bean定义的资源的描述
	 * *来自(为了在出现错误时显示上下文)。
	 */
	@Nullable
	String getResourceDescription();

	/**
	 * 返回原始BeanDefinition，如果没有，则返回{@code null}。
	 * 允许检索修饰后的bean定义(如果有的话)。
	 * 注意，这个方法返回的是直接的发起者。遍历的
	 * *发起者链，查找由用户定义的原始BeanDefinition。
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}
