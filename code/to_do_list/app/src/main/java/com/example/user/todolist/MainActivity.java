package com.example.user.todolist;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView listView;
    Button newItemButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_item);
        newItemButton = (Button) findViewById(R.id.button_new);
        final DataBase db = new DataBase(this);

//        db.deleteAllItems();
//
//        db.addItem(new ListItem("Wash Dishes", "Dishes need to be washed!"));
//        db.addItem(new ListItem("Wash Clothes", "Clothes need to be washed too!"));
//        db.addItem(new ListItem("Vacuum Apartment", "Dust. Dust everywhere."));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getAllItems(db));
        listView.setAdapter(adapter);

        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewItem.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) listView.getItemAtPosition(position);
                ListItem listItem = db.getItem(selectedItem);
                Intent intent = new Intent(MainActivity.this, ViewItem.class);
                intent.putExtra("id", listItem.getId());
                intent.putExtra("name", listItem.getName());
                intent.putExtra("description", listItem.getDescription());
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> getAllItems(DataBase db) {
        ArrayList<String> listItems = new ArrayList<>();
        ArrayList<ListItem> items = db.getAllItems();
        for (ListItem item : items) {
            listItems.add(item.getName());
        }
        return listItems;
    }

}
