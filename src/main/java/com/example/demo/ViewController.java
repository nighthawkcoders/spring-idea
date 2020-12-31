package com.example.demo;
/* MVC code that shows defining a simple Model, calling View, and this file serving as Controller
 * Web Content with Spring MVCSpring Example: https://spring.io/guides/gs/serving-web-con
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.fibonacci.*;

@Controller  // HTTP requests are handled a controller, using the @Controller annotation
public class ViewController {

    @GetMapping("/greet")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("name", name);   // MODEL is passed to html
        return "greet";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/repos")
    public String repos() {
        return "repos";
    }

    @GetMapping("/java-event")
    public String javaEvent(Model model) {
        model.addAttribute("url", "https://padlet.com/jmortensen7/jho9v5wc4p9jgyn2");
        return "timeline";
    }

    @GetMapping("/java-mvc")
    public String javaMVC(Model model) {
        model.addAttribute("url", "https://padlet.com/jmortensen7/csatime1_2");
        return "timeline";
    }

    @GetMapping("/java-hello")
    public String javaHello(Model model) {
        model.addAttribute("url", "https://padlet.com/jmortensen7/csatime");
        return "timeline";
    }

    @GetMapping("/pbl")   // CONTROLLER handles GET request for
    public String pblArticle() {
        return "articles/pbl";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/snake")   // CONTROLLER handles GET request for
    public String snake() {
        return "games/snake";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/fib")   // CONTROLLER handles GET request for
    public String fib(@RequestParam(name="seq", required=false,  defaultValue="2") String seq, Model model) {
        //nth is fibonacci request
        int nth = Integer.parseInt(seq);

        //fibonacci methods
        FibFor fibfor = new FibFor(nth);
        FibRecurse fibrecurse = new FibRecurse(nth);
        FibStream fibstream = new FibStream(nth);
        FibWhile fibwhile = new FibWhile(nth);

        //MODEL attributes are passed back html
        model.addAttribute("fib", fibstream.getNth());
        model.addAttribute("fibseq", fibstream.getNthSeq());
        model.addAttribute("fibfortime", fibfor.getTimeElapsed());
        model.addAttribute("fibrecursetime", fibrecurse.getTimeElapsed());
        model.addAttribute("fibstreamtime", fibstream.getTimeElapsed());
        model.addAttribute("fibwhiletime", fibwhile.getTimeElapsed());

        //render fibonacci results
        return "algos/fib";
    }
}