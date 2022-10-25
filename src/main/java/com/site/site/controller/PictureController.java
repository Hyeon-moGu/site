package com.site.site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/picture")
@Controller
@RequiredArgsConstructor
public class PictureController {

    @RequestMapping("/list")
    public String list(){
        return "picture_list";
    }
}
