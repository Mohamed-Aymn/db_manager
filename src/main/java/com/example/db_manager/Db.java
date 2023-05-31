package com.example.db_manager;
import java.sql.*;

public class Db {
    // this method is static to be called once all over the program
    static Connection connection;
    // I changed port in my pc as it was used by something else
    private String URL = "jdbc:mysql://localhost:3307/db_manager";
    private String USER = "root";
    private String PASSWORD = "passpass123";
    public boolean isConnected = false;

    // empty constructor
    public Db(){};

    // connection function
    public void connect(){
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            isConnected = true;
            System.out.println("database connection succeed");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("database connection failed");
        }
    }
}
