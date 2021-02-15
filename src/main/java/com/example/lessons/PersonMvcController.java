package com.example.lessons;

import com.example.lessons.models.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;

@Controller
public class PersonMvcController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/personresults").setViewName("starters/personresults");
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return(template for person form)
        @param(PersonForm Class)
    */
    @GetMapping("/person")
    public String showForm(PersonForm personForm) {
        return "starters/person";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param(PersonForm object with @Valid)
    @param(BindingResult object)
     */
    @PostMapping("/person")
    public String checkData(@Valid PersonForm personForm, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "starters/person";
        }
        // Redirect to next step
        return "redirect:/personresults";
    }
}