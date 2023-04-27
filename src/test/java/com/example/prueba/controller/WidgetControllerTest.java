package com.example.prueba.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class WidgetControllerTest {

    @Test
    void getWidgetContents() {
        WidgetController widgetController = new WidgetController();
        Assertions
                .assertEquals(widgetController
                        .getWidgetContents("httpp://localhost:8080/api/widgets"),
                        ResponseEntity.badRequest().body("<h1>The url parameter must be a valid url</h1>"));
    }
}