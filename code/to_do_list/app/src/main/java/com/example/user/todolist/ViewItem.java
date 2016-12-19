package com.example.user.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //final DBHandler db = new DBHandler(this);
        final DataBase db = ((MainApp) getApplication()).db;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);
    }

}
