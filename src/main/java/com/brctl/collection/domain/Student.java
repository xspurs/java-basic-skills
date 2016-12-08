package com.brctl.collection.domain;

/**
 * Created by Orclover on 2016-12-08.
 */
public class Student {

    /* attributes */
    private String name;
    private int score;

    /* constructors */
    public Student() {

    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /* getters & setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /* implements */
    @Override
    public String toString() {
        return "NAME: " + this.name + ", SCORE: " + this.score;
    }

}
