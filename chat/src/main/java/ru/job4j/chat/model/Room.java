package ru.job4j.chat.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class Room {
    private final RestTemplate restTemplate;

    public Room(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }





}
