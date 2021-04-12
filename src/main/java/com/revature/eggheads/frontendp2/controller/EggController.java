package com.revature.eggheads.frontendp2.controller;

import com.revature.eggheads.frontendp2.model.Egg;
import com.revature.eggheads.frontendp2.model.Greeting;
import com.revature.eggheads.frontendp2.util.EggImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class EggController {

    @GetMapping("/egg")
    public String greetingForm(Model model) {                       // the Model attributes can be accessed by the template view, greeting.html
        model.addAttribute("egg", new Egg());           // add the object to be populated by the form
        return "egg_form";                                          // return egg_form.html
    }

    @PostMapping("/egg")
    public String greetingSubmit(@ModelAttribute Egg egg, Model model) {
        model.addAttribute("egg", egg);
        return "egg_result";
    }
}