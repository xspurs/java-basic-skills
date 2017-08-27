package com.brctl.pattern.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * Primary Schoold Score Sheet
 * @author duanxiaoxing
 * @created 2017/8/27
 */
@Slf4j
public class PrimarySchoolScoreSheet implements ScoreSheet {

    @Override
    public void report() {
        log.info("chinese: {}, math: {}, english: {}", 66, 67, 62);
    }

    @Override
    public void sign() {
        log.info("father has signed name");
    }

}
