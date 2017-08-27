package com.brctl.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author duanxiaoxing
 * @created 2017/8/27
 */
@Slf4j
public class SortDecorator extends Decorator {

    public SortDecorator(ScoreSheet scoreSheet) {
        super(scoreSheet);
    }

    private void reportSort() {
        log.info("I rank {} in my class", 39);
    }

    @Override
    public void report() {
        super.report();
        reportSort();
    }

}
