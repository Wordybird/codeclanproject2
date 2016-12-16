package com.example.user.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "To-do List";
    private static final String TABLE_LIST = "Things To Do";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "list_item";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LIST + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);

        // Create tables again
        onCreate(db);
    }

    public void addItem(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, listItem.getName());
        values.put(KEY_DESCRIPTION, listItem.getDescription());
        db.insert(TABLE_LIST, null, values);
        db.close();
    }

    ListItem getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LIST, new String[]{KEY_ID, KEY_NAME, KEY_DESCRIPTION},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ListItem listItem = new ListItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return listItem;
    }

    public List<ListItem> getAllItems() {
        List<ListItem> itemList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ListItem listItem = new ListItem();
                listItem.setID(Integer.parseInt(cursor.getString(0)));
                listItem.setName(cursor.getString(1));
                listItem.setDescription(cursor.getString(2));
                itemList.add(listItem);
            } while (cursor.moveToNext());
        }
        return itemList;
    }

    public int updateItem(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, listItem.getName());
        values.put(KEY_DESCRIPTION, listItem.getDescription());

        // updating row
        return db.update(TABLE_LIST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(listItem.getId()) });
    }

    public void deleteItem(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST, KEY_ID + " = ?", new String[] {
                String.valueOf(listItem.getId())
        });
        db.close();
    }


    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
