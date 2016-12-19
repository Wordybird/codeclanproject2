package com.example.user.todolist;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_item);
        final DataBase db = new DataBase(this);

        db.deleteAllItems();

        Log.d("Insert:", "Inserting...");
        db.addItem(new ListItem("Wash Dishes", "Dishes need to be washed!"));
        db.addItem(new ListItem("Wash Clothes", "Clothes need to be washed too!"));
        db.addItem(new ListItem("Vacuum Apartment", "Dust. Dust everywhere."));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getAllItems(db));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) listView.getItemAtPosition(position);
                Log.d("DartPlayers: ", selectedItem + " clicked!");
                ListItem dartPlayer = db.getItem(selectedItem);
                Intent intent = new Intent(MainActivity.this, ViewItem.class);
                intent.putExtra("id", dartPlayer.getId());
                intent.putExtra("name", dartPlayer.getName());
                intent.putExtra("nickname", dartPlayer.getDescription());
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
