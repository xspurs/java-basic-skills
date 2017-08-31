package com.brctl.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * 四种内部类
 * static member class
 * non-static member class
 * anonymous class
 * local class
 * @author duanxiaoxing
 * @created 2017/8/31
 */
@Slf4j
public class InnerClass {

    // static member class
    // can be private/default/protected/public
    static class StaticMemberClass {

    }

    // non-static number class
    // can be private/default/protected/public
    class NonStaticMemberClass {

    }

    public void letItRun(String runnerName) {
        // anonymous class
        new Thread(new Runnable() {
            @Override
            public void run() {
                // in java 8, variable in anonymous class & local class is EFFECTIVELY FINAL
                log.info("{} is running", runnerName);
            }
        });
    }

    public void sayHello(String word) {
        // local class
        // can not be private/protected/public, just like normal local variables
        class LocalClass {
            public void say() {
                // in java 8, variable in anonymous class & local class is EFFECTIVELY FINAL
                log.info("say {}", word);
            }
        }
    }

}
