package com.example.prueba.services;
import com.example.prueba.models.Content;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetService {

    public List<Content> getContents(JsonNode widgetContents){

        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();

        for(JsonNode widget : widgetContents){
            String type = widget.get("type").asText();
            String title = widget.get("title").asText();
            ArrayList<String> titles = new ArrayList<>();
            if (hashMap.containsKey(type)) {
                titles = hashMap.get(type);
            }
            titles.add(title);
            hashMap.put(type,titles);
        }

        ArrayList<Content> responseList = new ArrayList<>();
        hashMap.forEach( (type,titles) -> {
            Content node = new Content(type, titles);
            responseList.add(node);
        });

        return responseList;
    }
}
