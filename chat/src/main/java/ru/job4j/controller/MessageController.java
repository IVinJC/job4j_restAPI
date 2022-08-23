package ru.job4j.controller;

import ru.job4j.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    Message message = new Message("Hello!!");
}
