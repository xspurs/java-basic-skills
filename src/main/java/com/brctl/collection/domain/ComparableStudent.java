package com.brctl.collection.domain;

/**
 * Created by Orclover on 2016-12-08.
 */
public class ComparableStudent implements java.lang.Comparable<ComparableStudent> {

    /* attributes */
    private String name;
    private int score;

    /* constructors */
    public ComparableStudent() {

    }

    public ComparableStudent(String name, int score) {
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
    @Override
    public int compareTo(ComparableStudent t) {
        return t.score - this.score;
    }

}
