package com.example.lessons.controllers;

import com.example.lessons.modelsMongo.PersonMongo;
import com.example.lessons.modelsMongo.PersonMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

// Built using article: https://spring.io/guides/gs/validating-form-input/
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class PersonMongoMvcController implements CommandLineRunner {

    // Autowired enables Control to connect HTML and POJO Object to Database easily for CRUD
    @Autowired
    private PersonMongoRepository repository;

    @GetMapping("/mongo/person")
    public String person(Model model) {
        List<PersonMongo> list = repository.findAll();
        model.addAttribute("list", list);
        return "mvc/mongo/person";
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return - template for person form
        @param - Person Class
    */
    @GetMapping("/mongo/personcreate")
    public String personAdd(PersonMongo person) {
        return "mvc/mongo/personcreate";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/mongo/personcreate")
    public String personSave(@Valid PersonMongo person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/mongo/personcreate";
        }
        repository.save(person);
        // Redirect to next step
        return "redirect:/mongo/person";
    }

    @GetMapping("/mongo/personupdate/{id}")
    public String personUpdate(@PathVariable("id") String id, Model model) {
        model.addAttribute("person", repository.findById(id));
        return "mvc/mongo/personupdate";
    }

    @PostMapping("/mongo/personupdate")
    public String personUpdateSave(@Valid PersonMongo person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/mongo/personupdate";
        }
        repository.save(person);
        // Redirect to next step
        return "redirect:/mongo/person";
    }

    @GetMapping("/mongo/persondelete/{id}")
    public String personDelete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "redirect:/mongo/person";
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new PersonMongo("Alice", 10));
        repository.save(new PersonMongo("Bob", 20));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (PersonMongo person : repository.findAll()) {
            System.out.println(person);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("Alice"));
    }
}