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

/**
 * 在接口通往目标的路上拦截对接口的调用。这些
 * *嵌套在目标的“顶部”。
 * *
 * * <p>用户应该实现{@link #invoke(MethodInvocation)}
 * 方法修改原始行为。例如:下节课
 * 实现跟踪拦截器(跟踪对象上的所有调用)
 * *拦截方法(s)):
 *
 * <pre class=code>
 * class TracingInterceptor implements MethodInterceptor {
 *   Object invoke(MethodInvocation i) throws Throwable {
 *     System.out.println("method "+i.getMethod()+" is called on "+
 *                        i.getThis()+" with args "+i.getArguments());
 *     Object ret=i.proceed();
 *     System.out.println("method "+i.getMethod()+" returns "+ret);
 *     return ret;
 *   }
 * }
 * </pre>
 *
 * @author Rod Johnson
 */
@FunctionalInterface
public interface MethodInterceptor extends Interceptor {

	/**
	 * 实现此方法以在和之前执行额外的处理
	 * *调用后。礼貌的实现当然会
	 * *喜欢调用{@link Joinpoint#proceed()}。
	 * @param invocation 方法调用连接点
	 * @return 调用{@link Joinpoint#proceed()}的结果;
	 * *可能被拦截器拦截
	 * @throws Throwable 如果拦截器或目标对象
	 * *抛出异常
	 */
	Object invoke(MethodInvocation invocation) throws Throwable;

}
