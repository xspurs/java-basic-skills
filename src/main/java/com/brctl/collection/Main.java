package com.brctl.collection;

import com.brctl.collection.comparator.StudentComparator;
import com.brctl.collection.domain.ComparableStudent;
import com.brctl.collection.domain.Student;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Duan Xiaoxing on 2016-12-08.
 */
public class Main {

    static Random random = new Random();
    private static final int listLength = 20;
    private static List<Student> studentList;
    private static List<ComparableStudent> comparableStudentList;

    // initialization
    static {
        studentList = getStudentList(listLength, Student.class);
        comparableStudentList = getStudentList(listLength, ComparableStudent.class);
    }

    // main function
    public static void main(String[] args) {
        System.out.println("========== Student List Begins ==========");
        System.out.println("========== BEFORE SORT ==========");
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
        // sorted Student list
        studentList.sort(new StudentComparator());
        System.out.println("========== AFTER SORT ==========");
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
        System.out.println("========== Student List Ends ==========");


        System.out.println("========== ComparableStudent List Begins ==========");
        for (ComparableStudent student : comparableStudentList) {
            System.out.println(student.toString());
        }
        // sorted ComparableStudent list
        comparableStudentList.sort(null);
        System.out.println("========== AFTER SORT ==========");
        for (ComparableStudent student : comparableStudentList) {
            System.out.println(student.toString());
        }
        System.out.println("========== ComparableStudent List Ends ==========");
    }

    // use generic type to generate Student or ComparableStudent list at corresponding length
    public static <T> List<T> getStudentList(int number, Class<T> clazz) {
        List<T> studentList = new ArrayList<>(number);
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class, int.class);
            for(int i = 0; i < number; ++i) {
                T t = constructor.newInstance(getRandomName(), getRandomScore());
                studentList.add(t);
            }
        } catch(Exception ex) {

        }
        return studentList;
    }

    // get random name at length between 4 and 10
    public static String getRandomName() {
        int length = 4 + random.nextInt(6);
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            if(i == 0) {
                // first char is upper case
                stringBuilder.append((char) (65 + random.nextInt(25)));
                continue;
            }
            // other chars are lower case
            stringBuilder.append((char) (97 + random.nextInt(25)));
        }
        return stringBuilder.toString();
    }

    // get random score between 40 and 100
    public static int getRandomScore() {
        return 40 + random.nextInt(61);
    }

}
