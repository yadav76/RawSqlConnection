package com.example.dbapp;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component            // Creates Bean of DbManager class
public class DbManager {

    // make SQL Connection
    public Connection connection;
    // create function to getSQL Connection and it's return type is connection

    // Constructor of DbManager
    public DbManager() throws SQLException{
        getConnection();
        createTable();
    }
    // When Object is created then constructor is called and both two methods are called directly

    public Connection getConnection() throws SQLException {

        if (connection == null) {
            // connect sql

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teachdb","root","hpnote15");
        }
        // connection statement is critical statement so might throws an Exception that's why throws written

        return connection;
    }

    // Now create table into database
    public void createTable() throws SQLException{

        String sql = "create table if not exists student_info(id INT primary key auto_increment, age INT, name VARCHAR(30))";

        Statement st = connection.createStatement();
        // These help to create above sql java statment into SQL statement

        boolean value = st.execute(sql);   // to execute sql statement
        System.out.println(value);
    }
    // These above two methods has to be called at start of our Application so I call these two methds in constructor
    // of DbManager class

    // Insert Function

    public void insert_info(Student s) throws SQLException {

        String sql = "insert into student_info(age,name) values("+s.getAge()+",'"+s.getName()+"')";

        Statement st = connection.createStatement();
        int rows = st.executeUpdate(sql);
        System.out.println("Rows Affected : " + rows);
    }

    // function to get Data from table of DataBase like Student_info Table

    public void getAllStudent() throws SQLException {
        String sql = "select * from student_info";   // Qeury to get all data from table
        Statement st = connection.createStatement();   // to create

        // get function returns data in Tabular format. to store these kind of Data we use ResultSet Interface in java
        ResultSet rs = st.executeQuery(sql);   // these type of Query used to get data from DB

        // Now ResultSet is Tabular data so for Interating on ResultSet I have to use Iterator
        while(rs.next()) {
            // Result Set Iterator returns data in String format no matter format of DATA stored in DB
            // Also returns data in another formats but I don't know them
            // all parameters name passed in getString() function of ResultSet are should be same as column name
            // of Table else it will return an Error

            String name = rs.getString("name");   // "name" should be same as stored in DB
            String id = rs.getString("id");    // returns String but id stored in Int format in DB
            String age = rs.getString("age");

            System.out.println("name "+name+" id "+id+" age "+age);
            // I am printing on Console not returning to Api so I will not get it to Postman
        }
    }
}
