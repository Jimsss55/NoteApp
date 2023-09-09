package com.example.notepad;

public class Notes {
    private String title;
    private String description;

    public Notes(String title, String description){
        this.description=description;
        this.title=title;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
