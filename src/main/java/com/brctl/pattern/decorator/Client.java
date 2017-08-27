package com.brctl.pattern.decorator;

/**
 * Test Scene
 * @author duanxiaoxing
 * @created 2017/8/27
 */
public class Client {

    public static void main(String[] args) {
        ScoreSheet scoreSheet = new PrimarySchoolScoreSheet();
        // decorate with high score decorator
        scoreSheet = new HighScoreDecorator(scoreSheet);
        // decorate with sort decorator
        scoreSheet = new SortDecorator(scoreSheet);
        Father father = new Father(scoreSheet);
        father.readScoreSheet();
        father.signName();
    }
}
