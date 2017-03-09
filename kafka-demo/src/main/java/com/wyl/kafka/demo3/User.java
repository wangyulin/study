package com.wyl.kafka.demo3;

/**
 * Created by wangyulin on 08/03/2017.
 */
public class User {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private int age;

    public User() {

    }

    public User(Long id, String userName, String firstName, String lastName, int age) {
        super();
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
                + lastName + ", age=" + age + "]";
    }

}
