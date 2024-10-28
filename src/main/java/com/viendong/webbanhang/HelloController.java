package com.viendong.webbanhang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloGet() {
        return "Hello Controller via GET";
    }
}
