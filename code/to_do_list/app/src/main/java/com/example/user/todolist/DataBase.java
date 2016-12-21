package com.example.user.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "toDoList.db";
    private static final String TABLE_LIST = "to_do_list";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "list_item";
    private static final String KEY_DESCRIPTION = "description";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_LIST + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT ) ";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST );
        onCreate(db);
    }

    private void runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void addItem(ListItem listItem) {
        String name = listItem.getName();
        String description = listItem.getDescription();
        String sql = "INSERT INTO " + TABLE_LIST +
                "(" + KEY_NAME + "," + KEY_DESCRIPTION + " ) VALUES ('"
                + name + "','" + description + "' )";
        runSQL(sql);
    }

    ListItem getItem(int id) {
        String sql = "SELECT * FROM " + TABLE_LIST + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            ListItem listItem = getItemfromDBCursor(cursor);
            return listItem;
        }
        return null;
    }

    public ListItem getItem(String name) {
        String sql = "SELECT * FROM " + TABLE_LIST + " WHERE " + KEY_NAME + " = '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
            ListItem listItem = getItemfromDBCursor(cursor);
            return listItem;
        }
        return null;
    }

    public ArrayList<ListItem> getAllItems() {
        ArrayList<ListItem> itemList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_LIST ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ListItem listItem = getItemfromDBCursor(cursor);
                itemList.add(listItem);
            }
            while (cursor.moveToNext());
        }
        return itemList;
    }

    public void updateItem(ListItem listItem) {
        int id = listItem.getId();
        String name = listItem.getName();
        String description = listItem.getDescription();
        String sql = "UPDATE " + TABLE_LIST + " SET "
                + KEY_NAME + " = '" + name + "', "
                + KEY_DESCRIPTION + " = '" + description + "'"
                + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deleteItem(ListItem listItem) {
        int id = listItem.getId();
        String sql = "DELETE FROM " + TABLE_LIST + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deleteItem(int id) {
        String sql = "DELETE FROM " + TABLE_LIST + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deleteAllItems() {
        String sql = "DELETE FROM " + TABLE_LIST ;
        runSQL(sql);
    }

    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LIST ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    private ListItem getItemfromDBCursor(Cursor cursor) {
        int idColumnNum = cursor.getColumnIndex(KEY_ID );
        int nameColumNum = cursor.getColumnIndex(KEY_NAME );
        int descriptionColumnNum = cursor.getColumnIndex(KEY_DESCRIPTION );
        int id = Integer.parseInt(cursor.getString(idColumnNum));
        String name = cursor.getString(nameColumNum);
        String description = cursor.getString(descriptionColumnNum);

        ListItem listItem = new ListItem(id, name, description);
        return listItem;
    }

}
