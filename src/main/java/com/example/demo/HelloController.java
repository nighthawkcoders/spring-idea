package com.example.demo;
/* Web Content with Spring MVC
Spring Example: https://spring.io/guides/gs/serving-web-con
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // HTTP requests are handled a controller, using the @Controller annotation
public class HelloController {

    @GetMapping("/greeting")    // HelloController handles GET request for /greeting, maps it to greeting() method and variable bindings
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);   // model is passed to html
        return "greeting";                     // returns name of VIEW (greeting)
    }

}