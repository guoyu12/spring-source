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

/**
 *这个接口表示程序中的一个调用。
 * *
 * 一个调用是一个连接点，可以被一个接口截获
 * *拦截器。
 *
 * @author Rod Johnson
 */
public interface Invocation extends Joinpoint {

	/**
	 * 获取作为数组对象的参数。
	 * *可以更改其中的元素值
	 * *数组来更改参数。
	 * @return 调用的参数
	 */
	Object[] getArguments();

}