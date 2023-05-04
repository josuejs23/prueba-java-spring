package com.example.prueba.controller;
import com.example.prueba.models.Content;
import com.example.prueba.services.WidgetService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class WidgetController {

    @GetMapping("/api/widgets")
    public ResponseEntity<? extends Object> getWidgetContents(@RequestParam String url){

        if(!url.startsWith("https://")){
            return ResponseEntity.badRequest().body("<h1>The url parameter must be a valid url</h1>");
        }

        RestTemplate restTemplate = new RestTemplate();
        JsonNode result = null;
        ObjectMapper mapper = new ObjectMapper();

        try{
            result = restTemplate.getForObject(url, JsonNode.class);
        }catch(Exception e){
            System.out.println(e);
        }

        JsonNode response = mapper.convertValue(result, JsonNode.class);

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

        WidgetService service = new WidgetService();
        List<Content> responseList = service.getContents(widgetContents);
        JsonNode responseBody = mapper.convertValue(responseList, JsonNode.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
