package com.example.lessons.controllers;
/* MVC code that shows defining a simple Model, calling View, and this file serving as Controller
 * Web Content with Spring MVCSpring Example: https://spring.io/guides/gs/serving-web-con
 */

import com.example.lessons.algos.Palindrome;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.lessons.algos.fibonacci.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class _MainController {

    @GetMapping("/greet")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("name", name);   // MODEL is passed to html
        return "starters/greet";                     // returns HTML VIEW (greeting)
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

    @GetMapping("/pbl")   // GET request
    public String pblArticle() {
        return "articles/pbl";                     // returns HTML VIEW (greeting)
    }

    @GetMapping("/snake")   // GET request
    public String snake() {
        return "starters/snake";                     // returns HTML VIEW (greeting)
    }

    // GET request,, parameters are passed with URL
    @GetMapping("/fib")
    public String fib(@RequestParam(name="fibseq", required=false,  defaultValue="2") String fibseq, Model model) {
        //nth is fibonacci request
        int nth = Integer.parseInt(fibseq);

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

    // GET and POST request, form action is POST - parameters are now hidden
    @RequestMapping(value = "/pali", method = { RequestMethod.GET, RequestMethod.POST })
    public String pali(@RequestParam(name="phrase", required=false,  defaultValue="A man a plan a canal panama") String phrase, Model model) {

        //MODEL attributes are passed back html
        model.addAttribute("log1", Palindrome.isPaliLog2(phrase, 1));
        model.addAttribute("log2", Palindrome.isPaliLog2(phrase, 2));
        model.addAttribute("log3", Palindrome.isPaliLog2(phrase, 3));

        //render fibonacci results
        return "algos/pali";
    }

    // GET request, no parameters
    @GetMapping("/covid19")
    public String covid19(Model model) throws IOException, InterruptedException {
        //rapidapi setup
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://corona-virus-world-and-india-data.p.rapidapi.com/api"))
            .header("x-rapidapi-key", "dec069b877msh0d9d0827664078cp1a18fajsn2afac35ae063")
            .header("x-rapidapi-host", "corona-virus-world-and-india-data.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        //rapidapi call
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //convert to java hash map
        HashMap map = new ObjectMapper().readValue(response.body(), HashMap.class);
        //pass country stats to view
        model.addAttribute("data", map);
        model.addAttribute("world", map.get("world_total"));
        model.addAttribute("countries", map.get("countries_stat"));
        return "starters/covid19";
    }
}