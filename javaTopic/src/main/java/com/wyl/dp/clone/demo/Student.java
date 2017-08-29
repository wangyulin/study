package com.wyl.dp.clone.demo;

/**
 * Created by wangyulin on 2/22/16.
 */
public class Student implements Cloneable{
    private String name;
    private int age;
    private Teacher teacher;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {

        return name;
    }

    public int getAge() {
        return age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    /*
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Object object = super.clone();
        return object;
    }
    */

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // 浅复制时：
        // Object object = super.clone();
        // return object;

        // 改为深复制：
        Student student = (Student) super.clone();
        // 本来是浅复制，现在将Teacher对象复制一份并重新set进来
        student.setTeacher((Teacher) student.getTeacher().clone());
        return student;
    }
}
