package com.common.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fantasy
 * @date 2018/10/16
 * @time 15:23
 */

@Table(name = "person")
public class Person extends BaseEntity{

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                '}';
    }
}
