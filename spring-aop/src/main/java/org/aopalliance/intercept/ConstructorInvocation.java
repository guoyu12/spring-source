/*
 * Copyright 2002-2018 the original author or authors.
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

import java.lang.reflect.Constructor;

/**
 *构造函数调用的描述，给定给
 * 拦截器在构造调用。
 * *
 * 构造函数调用是一个连接点，可以被拦截
 * *由构造函数拦截器。
 *
 * @author Rod Johnson
 * @see ConstructorInterceptor
 */
public interface ConstructorInvocation extends Invocation {

	/**
	 * 获取被调用的构造函数。
	 * 这个方法是一个友好的实现
	 * * * {@link Joinpoint#getStaticPart()}方法(相同结果)。
	 * @return 被调用的构造函数
	 */
	Constructor<?> getConstructor();

}
