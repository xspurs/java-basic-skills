package com.brctl.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author duanxiaoxing
 * @created 2017/8/27
 */
@Slf4j
public class HighScoreDecorator extends Decorator {

    public HighScoreDecorator(ScoreSheet scoreSheet) {
        super(scoreSheet);
    }

    private void reportHignScore() {
        log.info("highest score, chinese: {}, math: {}, english: {}", 70, 72, 69);
    }

    @Override
    public void report() {
        reportHignScore();
        super.report();
    }

}
