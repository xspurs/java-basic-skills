package com.brctl.collection.comparator;

import com.brctl.collection.domain.Student;

/**
 * student comparator
 * <br>
 * compare students by their scores
 * Created by Duan XiaoXing on 2016-12-08.
 */
public class StudentComparator implements java.util.Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return s2.getScore() - s1.getScore();
    }
}
