package com.example.demo;
/* MVC code that shows defining a simple Model, calling View, and this file serving as Controller
 * Web Content with Spring MVCSpring Example: https://spring.io/guides/gs/serving-web-con
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.technicals.FibStream;


@Controller  // HTTP requests are handled a controller, using the @Controller annotation
public class ViewController {

    @GetMapping("/greet")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String greeting() {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        return "greet";                     // returns name of VIEW (greeting)
    }

    @GetMapping("/greeting")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("name", name);   // MODEL is passed to html
        return "greeting";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/pbl")   // CONTROLLER handles GET request for
    public String pblArticle() {
        return "articles/pbl";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/snake")   // CONTROLLER handles GET request for
    public String snake() {
        return "games/snake";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/fib")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String fib() {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        return "technicals/fib";                     // returns name of VIEW (greeting)
    }

    @GetMapping("/fibnth")   // CONTROLLER handles GET request for
    public String fibNth(@RequestParam(name="seq", required=false,  defaultValue="20") String seq, Model model) {
        FibStream fib = new FibStream(Integer.parseInt(seq));
        model.addAttribute("fib", fib.getNth());        // MODEL is passed to html
        model.addAttribute("fibseq", fib.getNthSeq());  // MODEL is passed to html
        return "technicals/fibnth";                        // returns HTML VIEW (greeting)
    }

}