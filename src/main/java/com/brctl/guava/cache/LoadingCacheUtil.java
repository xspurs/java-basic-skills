package com.brctl.guava.cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Callables;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * LoadingCache Util
 * @author duanxiaoxing
 * @created 2018/8/3
 */
@Slf4j
public class LoadingCacheUtil {

    private LoadingCache<String, Optional<String>> loadingCache;

    public LoadingCacheUtil(int maxCacheSize, int expireSeconds) {
        this.loadingCache = CacheBuilder.newBuilder()
                .maximumSize(maxCacheSize)
                .expireAfterAccess(expireSeconds, TimeUnit.SECONDS).build(
                        new CacheLoader<String, Optional<String>>() {
                            @Override
                            public Optional<String> load(String key) throws Exception {
                                String loadedValue = key + "'s value";
                                return Optional.fromNullable(loadedValue);
                            }
                        }
                );
    }


    public String get(String key) {
        try {
            Optional<String> optional = loadingCache.get(key);
            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (ExecutionException e) {
            log.error("get value error", e);
        }
        return null;
    }


//    public String get(String key, Callable call) {
//        loadingCache.get()
//    }


    /**
     * Example
     * @param args
     */
    public static void main(String[] args) {
        LoadingCacheUtil loadingCacheUtil = new LoadingCacheUtil(10, 3600);
        log.info(loadingCacheUtil.get("hello"));
    }

}
