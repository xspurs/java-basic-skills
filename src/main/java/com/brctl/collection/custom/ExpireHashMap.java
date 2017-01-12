package com.brctl.collection.custom;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 扩展HashMap
 * <br>添加可以设置过期时间的key
 * Created by duanxiaoxing on 17/1/11.
 */
public class ExpireHashMap<K, V> extends java.util.HashMap<K, V> {

    public V put(K key, V value, long expire) {
        V rv = put(key, value);
        Timer timer= new Timer(true);
        TimerTask task  = new TimerTask() {
            @Override
            public void run() {
                remove(key);
                System.out.println("key: " + key + " is removed.");
                for(Map.Entry<K, V> entry: entrySet()) {
                    System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
                }
                //timer.cancel();
                System.exit(0);
            }
        };
        timer.schedule(task, expire);
        return rv;
    }
}
