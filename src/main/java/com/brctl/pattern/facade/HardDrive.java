package com.brctl.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * Hard Drive
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
public class HardDrive {

    public byte[] read(long lba, int size) {
        log.info("hard drive read, lba: {}, size: {}...", lba, size);
        return new byte[]{};
    }

}
