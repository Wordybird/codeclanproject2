package com.example.user.todolist;

import android.app.Application;

public class MainApp extends Application {

    DataBase db;

    @Override
    public void onCreate(){
        super.onCreate();
        db = new DataBase(this);
    }

}
