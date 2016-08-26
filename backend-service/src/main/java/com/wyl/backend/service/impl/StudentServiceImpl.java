package com.wyl.backend.service.impl;

import com.wyl.backend.exception.CatchableException;
import com.wyl.backend.model.Gender;
import com.wyl.backend.model.Student;
import com.wyl.backend.service.StudentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulin on 8/26/16.
 */
public class StudentServiceImpl implements StudentService.Iface {

    private static List<Student>  stus = new ArrayList<Student>();

    static {
        String name = "A";
        for(int i = 1 ; i <= 10 ; i++) {
            Student stu = new Student(i,name, i%2==0?Gender.MAIL:Gender.FEMALE,i+10);
            stus.add(stu);
        }
    }

    public List<Student> findAllStudents() throws CatchableException {

        return stus;
    }

}
