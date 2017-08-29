package com.wyl.dp.clone.demo;

/**
 * Created by wangyulin on 2/22/16.
 */
public class Demo1 {
    public static void main(String[] args) throws Exception{
        Teacher teacher = new Teacher();
        teacher.setName("Teacher Zhang");
        teacher.setAge(40);

        Student student1 = new Student();
        student1.setName("ZhangSan");
        student1.setAge(20);
        student1.setTeacher(teacher);

        Student student2 = (Student) student1.clone();
        System.out.println("拷贝得到的信息");
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getTeacher().getName());
        System.out.println(student2.getTeacher().getAge());
        System.out.println("-------------");

        // 修改老师的信息
        teacher.setName("Teacher Zhang has changed");
        System.out.println(student1.getTeacher().getName());
        System.out.println(student2.getTeacher().getName());
    }
}
