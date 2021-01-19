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
 * 拦截新对象的构造。
 * *
 * *用户应该实现{@link
 * * #construct(ConstructorInvocation)}方法来修改原始的
 * *行为。例如，下面的类实现了一个单例
 * 拦截器(只允许一个被拦截的唯一实例
 * *类):
 *
 * <pre class=code>
 * class DebuggingInterceptor implements ConstructorInterceptor {
 *   Object instance=null;
 *
 *   Object construct(ConstructorInvocation i) throws Throwable {
 *     if(instance==null) {
 *       return instance=i.proceed();
 *     } else {
 *       throw new Exception("singleton does not allow multiple instance");
 *     }
 *   }
 * }
 * </pre>
 *
 * @author Rod Johnson
 */
public interface ConstructorInterceptor extends Interceptor  {

	/**
	 * 实现此方法以在和之前执行额外的处理
	 * *在新对象构造之后。礼貌的实现
	 * *当然要调用{@link Joinpoint#proceed()}。
	 * * @param调用构造连接点
	 * * @return新创建的对象，它也是的结果
	 * *调用{@link Joinpoint#proceed()};可能被取代
	 * *拦截器
	 * @throws Throwable 如果拦截器或目标对象
	 * throws an exception
	 */
	Object construct(ConstructorInvocation invocation) throws Throwable;

}
