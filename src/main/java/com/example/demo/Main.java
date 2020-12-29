package com.example.demo;
/* Basic web site that loads index.html, added is route to /hello
 * From IntelliJ Tutorial - https://www.jetbrains.com/help/idea/your-first-spring-application.html
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication  // explained here - https://dzone.com/articles/the-springbootapplication-annotation-example-in-ja#
@RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);   // Internal magic:
                                                    // loads index.html page from static folder
                                                    // run server on localhost:8080
    }

    @GetMapping("/hello")   // Main class handles GET request for /hello via @GetMapping annotation
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        // @RequestParam handles default values, name is regular Java parameter
        return String.format("Hello, %s!", name);    // This is a simple output of formatted string
    }

}

