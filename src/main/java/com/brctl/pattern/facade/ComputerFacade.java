package com.brctl.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @author duanxiaoxing
 * @created 2018/8/20
 */
@Slf4j
public class ComputerFacade {

    private static final int BOOT_ADDRESS = 0;
    private static final byte[] BOOT_MEMORY_DATA = new byte[]{};

    private CPU cpu;
    private HardDrive hardDrive;
    private Memory memory;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.hardDrive = new HardDrive();
        this.memory = new Memory();
    }

    public void start() {
        cpu.freeze();
        memory.load(BOOT_ADDRESS, BOOT_MEMORY_DATA);
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }

    public void shutdown() {
        cpu.stop();
    }

    public static void main(String[] args) {
        new ComputerFacade().start();
    }

}
