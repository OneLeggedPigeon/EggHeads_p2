package com.revature.eggheads.frontendp2.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.revature.eggheads.frontendp2.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public String greetingForm(Model model) {                       // the Model attributes can be accessed by the template view, greeting.html
        model.addAttribute("greeting", new Greeting()); // add the object to be populated by the form
        return "greeting";                                          // return greeting.html
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }
}