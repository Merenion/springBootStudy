package com.example.demo.controllers;

import com.example.demo.domain.Message;
import com.example.demo.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String,Object> model) { //required - обязательное или нет
        return "greeting";
    }

    @GetMapping("/main")
    public String main (Map<String,Object> model){
        Iterable<Message> messages =messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main") //если ничего не добавлять то пройдет как /post такое можно лишь единожды
    public String add (@RequestParam String text,@RequestParam String tag, Map<String,Object> model){  //text и tag совпадает с названием переменной в файле main.mustache
        Message message = new Message(text,tag);
        messageRepo.save(message);

        Iterable<Message> messages =messageRepo.findAll();
        model.put("messages", messages);

        return "main"; //название файла на который это все кидается
    }

    @PostMapping("filter") //добавляем мапинг так как post уже имеется
    public String filter (@RequestParam String filter, Map<String,Object> model) {
        Iterable<Message> messages;
        if (!filter.isEmpty())
            messages = messageRepo.findByTag(filter);
        else
            messages = messageRepo.findAll();
        model.put("messages",messages);
        return "main";
    }
}

