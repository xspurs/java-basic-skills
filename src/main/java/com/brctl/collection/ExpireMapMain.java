package com.brctl.collection;

import com.brctl.collection.custom.ExpireHashMap;

import java.util.Map;
import java.util.Timer;

/**
 * Created by duanxiaoxing on 17/1/11.
 */
public class ExpireMapMain {

    public static void main(String[] args) {
        ExpireHashMap<String, String> expireMap = new ExpireHashMap<>();
        //expireMap.put("name", "lucy", 5000L);
        expireMap.put("name", "lucy", 5000L);
        for(Map.Entry<String, String> entry: expireMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        //for()
        Timer timer = new Timer();
    }
}
