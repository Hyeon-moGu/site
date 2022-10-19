package com.site.site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/thinking")
@Controller
@RequiredArgsConstructor
public class thinkingController {

    @RequestMapping("")
    public String album(){
        return "thinking";
    }
}
