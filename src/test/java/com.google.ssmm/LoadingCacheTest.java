package com.google.ssmm;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created by Walter on 16-11-14.
 * 通常来说，Guava Cache适用于：
 你愿意消耗一些内存空间来提升速度。
 你预料到某些键会被查询一次以上。
 缓存中存放的数据总量不会超出内存容量。（Guava Cache是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。如果这不符合你的需求，请尝试Memcached这类工具）
 */
public class LoadingCacheTest {
    @Test
    public void testCreate() throws ExecutionException {
        //超过设计的最大值时 就会驱除最不经常使用的那些entries  。 evict：驱逐收回的意思 in populating:填充
        LoadingCache cache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String,Integer>() {
            @Override public Integer load(String key) throws Exception {
                if("1".equals(key)){
                    return 1;
                }else if("2".equals(key)){
                    return 2;
                }
                return 3;
            }
        });

        Object o = cache.get("1");
        System.out.println(o.getClass());
        System.out.println(o);
    }
}
