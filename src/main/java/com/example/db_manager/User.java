package com.example.db_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
