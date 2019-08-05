/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core.io;

import org.springframework.lang.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 *
 * Spring 框架所有资源的抽象和访问接口
 *	由子类 AbstractResource 提供统一的默认实现
 *	抽象了所有spring内部使用到的底层资源：File URL Classpath等
 *
 *	{@link InputStreamSource#getInputStream()}返回资源的inputStream
 *
 * @author Juergen Hoeller
 * @since 28.12.2003
 */
public interface Resource extends InputStreamSource {

	/**
	 * 	判断当前资源是否存在
	 */
	boolean exists();

	/**
	 * 	判断当前资源是否可读
	 */
	default boolean isReadable() {
		return exists();
	}

	/**
	 * 	当前资源是否处于打开状态
	 */
	default boolean isOpen() {
		return false;
	}

	/**
	 * 	是否为 File
	 */
	default boolean isFile() {
		return false;
	}

	/**
	 * 	当前资源到URL的转换
	 */
	URL getURL() throws IOException;

	/**
	 * 	当前资源到 URI转换
	 */
	URI getURI() throws IOException;

	/**
	 * 	当前资源到 File的转换
	 */
	File getFile() throws IOException;

	/**
	 * 	返回 ReadableByteChannel
	 */
	default ReadableByteChannel readableChannel() throws IOException {
		return Channels.newChannel(getInputStream());
	}

	/**
	 * 	资源内容的长度
	 */
	long contentLength() throws IOException;

	/**
	 *	资源最后的修改时间
	 */
	long lastModified() throws IOException;

	/**
	 * 	根据资源的相对路径创建新资源
	 */
	Resource createRelative(String relativePath) throws IOException;

	/**
	 * 	资源的文件名
	 */
	@Nullable
	String getFilename();

	/**
	 * 	资源的描述
	 */
	String getDescription();

}
