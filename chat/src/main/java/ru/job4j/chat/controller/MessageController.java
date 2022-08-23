package ru.job4j.chat.controller;

import ru.job4j.chat.model.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    Message message = new Message("Hello!!");
}
