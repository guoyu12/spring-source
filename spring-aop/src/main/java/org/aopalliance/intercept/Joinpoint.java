/*
 * Copyright 2002-2016 the original author or authors.
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

package org.aopalliance.intercept;

import java.lang.reflect.AccessibleObject;

/**
 * 这个接口表示一个通用的运行时连接点(在AOP中)
 * *术语)。
 * *
 * 一个运行时连接点是一个发生在静态上的<i>事件</i>
 * 连接点(即程序中的一个位置)。例如,一个
 * *调用是方法上的运行时连接点(静态连接点)。
 * *给定连接点的静态部分可以被通用地检索
 * *使用{@link #getStaticPart()}方法。
 * *
 * 在截取框架的上下文中，一个运行时连接点
 * *则是对可访问对象(a)的访问的具体化
 * 方法，构造函数，字段)，即的静态部分
 * *的连接点。它被传递给安装的拦截器
 * *静态连接点。
 *
 * @author Rod Johnson
 * @see Interceptor
 */
public interface Joinpoint {

	/**
	 * 继续到链中的下一个拦截器。
	 * 这个方法的实现和语义取决于
	 * *在实际的joinpoint类型上(参见子接口)。
	 * @return 请参阅子接口的proceed定义
	 * @throws Throwable 如果连接点抛出异常
	 */
	Object proceed() throws Throwable;

	/**
	 * 返回持有当前连接点静态部分的对象。
	 * 以>为例，调用的目标对象。
	 * @return 对象(如果可访问对象是静态的，可以为空)
	 */
	Object getThis();

	/**
	 * 返回此连接点的静态部分。
	 * 静态部分是一个可访问的对象，在该对象上有一个链
	 * *已安装拦截器。
	 */
	AccessibleObject getStaticPart();

}
