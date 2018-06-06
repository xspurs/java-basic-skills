package com.brctl.pattern.decorator;

/**
 * Decorator / Forwarding Class
 * @author duanxiaoxing
 * @created 2017/8/27
 */
public abstract class Decorator implements ScoreSheet {

    private ScoreSheet scoreSheet;

    protected Decorator(ScoreSheet scoreSheet) {
        this.scoreSheet = scoreSheet;
    }

    @Override
    public void report() {
        scoreSheet.report();
    }

    @Override
    public void sign() {
        scoreSheet.sign();
    }
}
