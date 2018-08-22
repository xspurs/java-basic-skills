package com.brctl.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.UserManagedCache;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.impl.serialization.StringSerializer;

import java.util.concurrent.TimeUnit;

/**
 * EhcacheUtil
 * @author duanxiaoxing
 * @created 2018/8/3
 */
@Slf4j
public class EhcacheUtil {

    private static final long DEFAULT_ITEM_SIZE = 1L;

    private Cache<String, String> cache;

    public EhcacheUtil(long expireAfterWriteMinutes, int size, boolean isUseDirectMemory) {
        UserManagedCacheBuilder<String, String, UserManagedCache<String, String>> builder =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(String.class, String.class)
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(expireAfterWriteMinutes, TimeUnit.MINUTES)))
                .withKeySerializer(new StringSerializer())
                .withValueSerializer(new StringSerializer());
        if (isUseDirectMemory) {
            cache = builder.withResourcePools(ResourcePoolsBuilder.newResourcePoolsBuilder()
                    .offheap(size * DEFAULT_ITEM_SIZE, MemoryUnit.KB)).build(true);
        } else {
            cache = builder.withResourcePools(ResourcePoolsBuilder.heap(size)).build(true);
        }
    }

    /**
     * Default do not use direct memory
     * @param expireAfterWriteMinutes
     * @param size
     */
    public EhcacheUtil(long expireAfterWriteMinutes, int size) {
        this(expireAfterWriteMinutes, size, false);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }


    /**
     * Example
     * @param args
     */
    public static void main(String[] args) {
        String nameKey = "name";
        // default minimum to allocate 1M when use off-heap memory(direct memory)
        EhcacheUtil ehcacheUtil = new EhcacheUtil(10L, 1024, true);
        ehcacheUtil.put(nameKey, "Lucy");
        log.info(ehcacheUtil.get(nameKey));
    }

}
