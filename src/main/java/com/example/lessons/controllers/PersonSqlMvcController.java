package com.example.lessons.controllers;

import com.example.lessons.modelsSQL.Person;
import com.example.lessons.modelsSQL.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;
import java.util.List;

// Built using article: https://spring.io/guides/gs/validating-form-input/
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class PersonSqlMvcController implements WebMvcConfigurer {

    // Autowired enables Control to connect HTML and POJO Object to Database easily for CRUD
    @Autowired
    private PersonService service;

    @GetMapping("/sql/person")
    public String person(Model model) {
        List<Person> list = service.listAll();
        model.addAttribute("list", list);
        return "mvc/sql/person";
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return - template for person form
        @param - Person Class
    */
    @GetMapping("/sql/personcreate")
    public String personAdd(Person person) {
        return "mvc/sql/personcreate";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/sql/personcreate")
    public String personSave(@Valid Person person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/sql/personcreate";
        }
        service.save(person);
        // Redirect to next step
        return "redirect:/sql/person";
    }

    @GetMapping("/sql/personupdate/{id}")
    public String personUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", service.get(id));
        return "mvc/sql/personupdate";
    }

    @PostMapping("/sql/personupdate")
    public String personUpdateSave(@Valid Person person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/sql/personupdate";
        }
        service.save(person);
        // Redirect to next step
        return "redirect:/sql/person";
    }

    @GetMapping("/sql/persondelete/{id}")
    public String personDelete(@PathVariable("id") long id) {
        service.delete(id);
        return "redirect:/sql/person";
    }

}