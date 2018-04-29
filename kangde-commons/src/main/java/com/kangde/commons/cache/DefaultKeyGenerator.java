package com.kangde.commons.cache;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 实现spring cache的默认缓存实现策略
 * @author lisuo
 *
 */
public class DefaultKeyGenerator implements Serializable,KeyGenerator {

	private static final long serialVersionUID = -6694030316361551113L;

	@Override
	public Object generate(Object target, Method method, Object... params) {
		return new DefaultKey(target, method, params);
	}

}
