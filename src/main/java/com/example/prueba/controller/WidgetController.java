package com.example.prueba.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WidgetController {

    @GetMapping("/api/widgets")
    public ResponseEntity<? extends Object> getWidgetContents(@RequestParam String url){

        if(!url.startsWith("https://")){
            return ResponseEntity.badRequest().body("<h1>The url parameter must be a valid url</h1>");
        }

        RestTemplate restTemplate = new RestTemplate();
        JsonNode result = null;
        try{
            result = restTemplate.getForObject(url, JsonNode.class);
        }catch(Exception e){
            System.out.println(e);
        }
        JsonNode widgets = null;
        JsonNode widgetContents = null;

        if(!result.findPath("widgets").isMissingNode()){
            widgets = result.findPath("widgets");
            if(!widgets.findPath("contents").isMissingNode()){
                widgetContents = widgets.findPath("contents");
            } else {
                return ResponseEntity.badRequest().body("<h1>resource not found</h1>");
            }
        } else {
            return ResponseEntity.badRequest().body("<h1>resource not found</h1>");
        }

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();

        for(JsonNode widget : widgetContents){
            String type = widget.get("type").asText();
            String title = widget.get("title").asText();
            ArrayList<String> titles = new ArrayList<String>();
            if (hashMap.containsKey(type)) {
                titles = hashMap.get(type);
            }
            titles.add(title);
            hashMap.put(type,titles);
        }

        ObjectNode node = null;
        ArrayList<ObjectNode> responseList = new ArrayList<ObjectNode>();
        for(Map.Entry<String,ArrayList<String>> entry : hashMap.entrySet()){
            node = JsonNodeFactory.instance.objectNode();
            node.put("type",entry.getKey());
            node.put("titles",mapper.valueToTree(entry.getValue()));
            node.put("size",entry.getValue().size());
            responseList.add(node);
        }

        JsonNode response = mapper.convertValue(responseList, JsonNode.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
