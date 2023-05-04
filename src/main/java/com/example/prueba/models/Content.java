package com.example.prueba.models;

import java.io.Serializable;
import java.util.List;
public class Content implements Serializable {


    private List<String> titles;
    private String type;
    private int numOfTitle;

    public Content(  String type, List<String> titles){
        this.titles = titles;
        this.type = type;
        this.setNumOfTitle(titles.size());
    }

    public List<String> getTitles() {
        return titles;
    }
    public void setTitle(List<String> titles) {
        this.titles = titles;
    }
    public void seType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setNumOfTitle(int size){
        this.numOfTitle = size;
    }

    public int getNumOfTitles(){
        return this.numOfTitle;
    }

    @Override
    public String toString() {
        return "Content{" +
                "titles=" + titles +
                ", type='" + type + '\'' +
                ", numOfTitle=" + numOfTitle +
                '}';
    }
}
