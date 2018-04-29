package com.kangde.collection.util;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.SerializationUtils;

import com.kangde.commons.web.filter.SpringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis操作工具类
 * 
 * @author lisuo
 *
 */
public abstract class RedisUtil {
	/** 日志 */
	private static Logger log = Logger.getLogger(RedisUtil.class);
	/** Redis Pool */
	private static ShardedJedisPool shardedJedisPool;
	private static Jedis jedis = SpringUtil.getBean("jedis",Jedis.class);
	static{
		shardedJedisPool = SpringUtil.getBean(ShardedJedisPool.class);
	}

	/**
	 * 回调函数
	 * @author lisuo
	 *
	 * @param <T> 执行函数的返回类型
	 */
	public interface Function<T> {
		public T callback(ShardedJedis e);
	}
	
	/**
	 * 执行Redis 命令
	 * @param fun 回调函数
	 * @return
	 */
	public static <T> T execute(Function<T> fun) {
		ShardedJedis shardedJedis = null;
		try {
			// 从连接池中获取到jedis分片对象
			shardedJedis = shardedJedisPool.getResource();
			// 从redis中获取数据
			return fun.callback(shardedJedis);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			if (null != shardedJedis) {
				// 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
				shardedJedis.close();
			}
		}
		return null;
	}
	
	/**
     * 保存数据到redis中
     * 
     * @param key
     * @param value
     * @return
     */
    public static String set(final String key, final String value) {
        return execute(new Function<String>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }
    
    /**
     * 保存对象到redis中
     * @param key
     * @param value
     * @return
     */
    public static String setObject(final String key, final Object value) {
    	return execute(new Function<String>() {
    		@Override
    		public String callback(ShardedJedis shardedJedis) {
    			if(key!=null && value!=null){
    				byte[] bs = SerializationUtils.serialize(value);
    				return shardedJedis.set(key.getBytes(), bs);
    			}
    			return null;
    		}
    	});
    }

    /**
     * 从redis中获取数据
     * 
     * @param key
     * @param value
     * @return
     */
    public static String get(final String key) {
        return execute(new Function<String>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }
    
    /**
     * 从redis中获取对象数据
     * @param key
     * @return
     */
    public static <T> T getObject(final String key) {
    	return execute(new Function<T>() {
    		@SuppressWarnings("unchecked")
			@Override
    		public T callback(ShardedJedis shardedJedis) {
    			byte[] bs = shardedJedis.get(key.getBytes());
    			Object object = SerializationUtils.deserialize(bs);
    			return (T) object;
    		}
    	});
    }

    /**
     * 从redis中删除数据
     * 
     * @param key
     * @param value
     * @return
     */
    public static Long del(final String key) {
        return execute(new Function<Long>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    /**
     * 保存数据到redis中,并设置生存时间
     * @param key
     * @param value
     * @param time 生存时间，单位是秒
     * @return
     */
    public static Long set(final String key, final String value, final Integer time) {
        return execute(new Function<Long>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                shardedJedis.set(key, value);
                return shardedJedis.expire(key, time);
            }
        });
    }

    /**
     * 保存对象数据到redis中,并设置生存时间
     * @param key
     * @param value
     * @param time 生存时间，单位是秒
     * @return
     */
    public static Long setObject(final String key, final Object value,final Integer time) {
    	return execute(new Function<Long>() {
    		@Override
    		public Long callback(ShardedJedis shardedJedis) {
				byte[] bs = SerializationUtils.serialize(value);
				shardedJedis.set(key.getBytes(), bs);
				return shardedJedis.expire(key.getBytes(), time);
    		}
    	});
    }
    
    /**
     * 根据key，设置数据的生存时间
     * @param key
     * @param time
     * @return
     */
    public static Long expire(final String key, final Integer time) {
        return execute(new Function<Long>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, time);
            }
        });
    }
	
    /**
     * 根据key，设置对象数据的生存时间
     * @param key
     * @param time
     * @return
     */
    public static Long expireObject(final String key, final Integer time) {
    	return execute(new Function<Long>() {
    		@Override
    		public Long callback(ShardedJedis shardedJedis) {
    			return shardedJedis.expire(key.getBytes(), time);
    		}
    	});
    }
    
    /**
     * 根据key，自增(Number)
     * @param key
     * @return 
     */
    public static Long incr(final String key) {
    	return execute(new Function<Long>() {
    		@Override
    		public Long callback(ShardedJedis shardedJedis) {
    			return shardedJedis.incr(key);
    		}
    	});
    }
    
    /**
     * 根据key，检测Key是否存在
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
    	return execute(new Function<Boolean>() {
    		@Override
    		public Boolean callback(ShardedJedis shardedJedis) {
    			return shardedJedis.exists(key);
    		}
    	});
    }

    /**
     * 获取keys
     * @param pattern 
     * @return
     */
    public static Set<String> keys(String pattern){
    	Set<String> keys = jedis.keys(pattern);
		return keys;
    }
    
    public static void flushAll(){
    	jedis.flushAll();
    }
    
    public static void flushDB(){
    	jedis.flushDB();
    }
    
}
