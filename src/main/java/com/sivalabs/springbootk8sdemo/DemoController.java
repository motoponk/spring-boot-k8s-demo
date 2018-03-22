package com.sivalabs.springbootk8sdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("")
    public String index() {
        return "SpringBoot Kubernetes Demo";
    }

    @GetMapping("/todos")
    public List<String> todos() {
        return Arrays.asList("Learn SpringBoot", "Learn Kubernetes", "Learn VueJS");
    }
}
