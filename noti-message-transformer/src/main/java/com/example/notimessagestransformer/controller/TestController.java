package com.example.notimessagestransformer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/wtf")
    public String test(){
        return "success";
    }
}
