package com.brctl.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * CPU
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
public class CPU {

    public void freeze() {
        log.info("cpu freeze...");
    }

    public void jump(int position) {
        log.info("cpu jump to position: {}...", position);
    }

    public void execute() {
        log.info("cpu execute...");
    }

    public void stop() {
        log.info("cpu stop...");
    }

}
