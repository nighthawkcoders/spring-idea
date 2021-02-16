package com.example.lessons;

import com.example.lessons.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;

// Built using article: https://spring.io/guides/gs/validating-form-input/
@Controller
public class PersonMvcController implements WebMvcConfigurer {

    /*
    If all bound attribute are valid, a redirect occurs to this route and template.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/personresults").setViewName("starters/personresults");
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return - template for person form
        @param - Person Class
    */
    @GetMapping("/person")
    public String showForm(Person person) {
        return "starters/person";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/person")
    public String checkData(@Valid Person person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "starters/person";
        }
        // Redirect to next step
        return "redirect:/personresults";
    }
}