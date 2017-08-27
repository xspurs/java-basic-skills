package com.brctl.pattern.decorator;

/**
 * @author duanxiaoxing
 * @created 2017/8/27
 */
public class Father {

    private ScoreSheet scoreSheet;

    public Father(ScoreSheet scoreSheet) {
        this.scoreSheet = scoreSheet;
    }

    public void readScoreSheet() {
        scoreSheet.report();
    }

    public void signName() {
        scoreSheet.sign();
    }

}
