package com.example.springsecurity.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class Home {
    @GetMapping("/home")
    public String getHome(){
        return "index";

    }

}
