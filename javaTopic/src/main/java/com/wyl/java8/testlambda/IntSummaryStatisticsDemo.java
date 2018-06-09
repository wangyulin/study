package com.wyl.java8.testlambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by wangyulin on 22/01/2018.
 */
public class IntSummaryStatisticsDemo {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>() {
            {
                add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
                add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
                add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
                add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
                add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
                add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
                add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
                add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
                add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
            }
        };

        int totalAge4 = students.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println(totalAge4);

        List<Integer> integers = new ArrayList<Integer>(){{
            add(13);
            add(29);
            add(32);
            add(10);
        }};

        int max = integers.stream().mapToInt(x -> x.intValue()).max().getAsInt();
        System.out.println(max);
        //integers.stream().mapToInt(t -> t.intValue()).collect(Collectors.summarizingInt(Integer::intValue));

    }



    @Data
    @AllArgsConstructor
    static class Student {
        /** 学号 */
        private long id;
        private String name;
        private int age;
        /** 年级 */
        private int grade;
        /** 专业 */
        private String major;
        /** 学校 */
        private String school;
    }

}
