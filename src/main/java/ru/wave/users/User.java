package ru.wave.users;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private Date date;
    private int salary;
    private String departamentname;
    private String post;
    private String bossName;

    private static List<User> users = new ArrayList<>();

    public User(int id, String name, Date date, int salary, String departamentname, String post, String bossName) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.salary = salary;
        this.departamentname = departamentname;
        this.post = post;
        this.bossName = bossName;
    }

    public User() {
    }

    public static List<User> getUsers() {
        return users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartamentname() {
        return departamentname;
    }

    public void setDepartamentname(String departamentname) {
        this.departamentname = departamentname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    @Override
    public String toString() {
        return "[   EMPLOY " + id + "   ]" + "\n" +
                "name: " + name + "\n" +
                "salary: " + salary + "\n" +
                "date: " + date + "\n" +
                "post: " + post + "\n" +
                "departamentname: " + departamentname + "\n" +
                "boss: " + bossName + "\n";
    }

    public String toStringSalary(){
        return "name: " + name + " salary: " + salary;
    }
    public String toStringDate(){
        return "name: " + name + " salary: " + date;
    }
}
