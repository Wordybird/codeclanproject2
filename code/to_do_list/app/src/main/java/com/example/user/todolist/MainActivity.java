package com.example.user.todolist;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase db = new DataBase(this);
        Log.d("Insert:", "Inserting...");
        db.addItem(new ListItem("Wash Dishes", "Dishes need to be washed!"));
        db.addItem(new ListItem("Wash Clothes", "Clothes need to be washed too!"));
        db.addItem(new ListItem("Vacuum Apartment", "Dust. Dust everywhere."));

        Log.d("Reading: ", "Reading all contacts..");
        List<ListItem> listItem = db.getAllItems();
        for (ListItem items : listItem) {
            String log = "ID: " + items.getId() + " , Name: " + items.getName() + " , Description: " + items.getDescription();
            Log.d("Name: ", log);
        }
    }

}
