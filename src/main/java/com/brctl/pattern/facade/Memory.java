package com.brctl.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * Memory
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
public class Memory {

    public void load(long position, byte[] data) {
        log.info("memory load, position: {}, data: {}...", position, data);
    }

}
