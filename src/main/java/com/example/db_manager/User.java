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

    public static List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = Db.connection;

        String sql = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");

            User user = new User(id, name,salary);
            users.add(user);
        }

        return users;
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
