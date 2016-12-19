package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItem extends AppCompatActivity{
    EditText nameEditText;
    EditText descriptionEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DataBase db = ((MainApp)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item);

        nameEditText = (EditText)findViewById(R.id.item_name);
        descriptionEditText = (EditText)findViewById(R.id.item_description);
        saveButton = (Button)findViewById(R.id.button_save);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String name = extras.getString("name");
        final String description = extras.getString("description");

        nameEditText.setText(name);
        descriptionEditText.setText(description);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                ListItem itemToUpdate = new ListItem(id, name, description);
                db.updateItem(itemToUpdate);
                backToItemView(id, name, description);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToItemView(id, name, description);
            }
        });
    }

    private void backToItemView(int id, String name, String description) {
        Intent intent = new Intent(EditItem.this, ViewItem.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        startActivity(intent);
    }
}

