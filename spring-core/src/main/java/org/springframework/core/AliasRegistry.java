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

package org.springframework.core;

/**
 * 用于管理别名的公共接口
 */
public interface AliasRegistry {

	/**
	 * 注册一个别名
	 * @param name 名称
	 * @param alias 注册的别名
	 * @throws IllegalStateException 如果别名已经在用
	 * and may not be overridden
	 */
	void registerAlias(String name, String alias);

	/**
	 * 移出这个别名
	 * @param alias the alias to remove
	 * @throws IllegalStateException if no such alias was found
	 */
	void removeAlias(String alias);

	/**
	 * 验证是否为别名
	 * @param name the name to check
	 * @return whether the given name is an alias
	 */
	boolean isAlias(String name);

	/**
	 * 返回已定义别名
	 * @param name 别名
	 * @return 别名
	 */
	String[] getAliases(String name);

}
