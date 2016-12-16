package com.example.user.todolist;

public class ListItem {

    int _id;
    String _name;
    String _description;

    public ListItem() {

    }

    public ListItem(int id, String name, String description) {
        this._id = id;
        this._name = name;
        this._description = description;
    }

    public ListItem(String name, String description) {
        this._name = name;
        this._description = description;
    }

    public int getId() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

}
