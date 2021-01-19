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

package org.aopalliance.aop;

/**
 * 所有AOP基础结构异常的超类。
 * *未检查，因为这样的异常是致命的和最终用户
 * 不应该强迫代码去捕捉它们。
 *
 * @author Rod Johnson
 * @author Bob Lee
 * @author Juergen Hoeller
 */
@SuppressWarnings("serial")
public class AspectException extends RuntimeException {

	/**
	 * AspectException构造函数。
	 * @param message the exception message
	 */
	public AspectException(String message) {
		super(message);
	}

	/**
	 *AspectException构造函数。
	 * @param message the exception message
	 * @param cause the root cause, if any
	 */
	public AspectException(String message, Throwable cause) {
		super(message, cause);
	}

}
