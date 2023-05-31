package com.example.db_manager;

// user model is created to make users manipulation easy
public class User {
    private int id;
    private String name;
    private int salary;

    // constructor
    public User(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // getter functions only will be used
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
