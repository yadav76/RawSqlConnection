package com.example.dbapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class StudentController {

    @Autowired
    DbManager dbManager;

    @PostMapping("/insert_info")
    public void insertInfo(@RequestBody() Student s) throws SQLException {
        dbManager.insert_info(s);
        return;
    }

    // Api for Getting and printing Data from Table of DataBase
    @GetMapping("/info")
    public void getInfo() throws SQLException {
        dbManager.getAllStudent();
    }
}
