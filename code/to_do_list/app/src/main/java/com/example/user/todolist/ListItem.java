package com.example.user.todolist;

public class ListItem {

    int id;
    String name;
    String description;

    public ListItem(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ListItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
