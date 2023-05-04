package com.example.prueba.services;
import com.example.prueba.models.Content;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;

class WidgetServiceTest {


    @Test
    void getContents() {
        JsonNode jsonNode;
        ObjectMapper mapper = new ObjectMapper();

        String pathFile = "src/test/java/com/example/prueba/services/data.json";
        {
            try {
                 jsonNode = mapper.readTree(new File(pathFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        WidgetService widgetService = new WidgetService();
        List<Content> contents = widgetService.getContents(jsonNode);
        Assertions.assertEquals(contents.size(),3);

    }
}