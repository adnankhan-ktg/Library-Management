package com.intelliatech.LibraryManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic")
public class HomeController {

    @GetMapping("/home")
    public String home()
    {
        return "I am in the home()";
    }
}
