package com.example.prueba.models;

import java.util.ArrayList;

public class Content {

    private String title;
    private ArrayList<String> titles;
    private int amountOfTitle = 0;
    public Content(String title){
        this.title = title;
        this.amountOfTitle = 0;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public int getAmountOfTitle(){
        return this.amountOfTitle;
    }

    public void addTitle(String title){
        this.titles.add(title);
        this.amountOfTitle += 1;
    }
}
