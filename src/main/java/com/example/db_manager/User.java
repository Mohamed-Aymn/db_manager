package com.example.db_manager;

public class User {
    private int id;
    private String name;
    private int salary;

    public User(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    int getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }

    int getSalary(){
        return this.salary;
    }
}
