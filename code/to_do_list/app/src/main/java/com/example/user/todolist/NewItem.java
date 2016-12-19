package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewItem extends AppCompatActivity {
    EditText nameEditText;
    EditText descriptionEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DataBase db = ((MainApp)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        nameEditText = (EditText)findViewById(R.id.item_name);
        descriptionEditText = (EditText)findViewById(R.id.item_description);
        saveButton = (Button)findViewById(R.id.button_add);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                ListItem newItem = new ListItem(name, description);
                db.addItem(newItem);
                backToMainView();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainView();
            }
        });
    }

    private void backToMainView() {
        Intent intent = new Intent(NewItem.this, MainActivity.class);
        startActivity(intent);
    }
}
