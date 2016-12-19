package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewItem extends AppCompatActivity {
    TextView nameEditText;
    TextView descriptionText;
    Button editButton;
    Button deleteButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final DataBase db = ((MainApp) getApplication()).db;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);

        nameEditText = (TextView)findViewById(R.id.item_name);
        descriptionText = (TextView)findViewById(R.id.item_description);
        editButton = (Button)findViewById(R.id.button_edit);
        deleteButton = (Button)findViewById(R.id.button_delete);
        backButton = (Button)findViewById(R.id.button_back);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String name = extras.getString("name");
        final String description = extras.getString("nickname");

        nameEditText.setText(name);
        descriptionText.setText(description);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewPlayer: ", "editing player with id " + id);
                Intent intent = new Intent(ViewItem.this, EditItem.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("nickname", description);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewPlayer: ", "deleting player with id " + id);
                db.deleteItem(id);
                backToMainActivity();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(ViewItem.this, MainActivity.class);
        startActivity(intent);
    }
}

