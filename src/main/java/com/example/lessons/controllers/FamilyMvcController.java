package com.example.lessons.controllers;

import com.example.lessons.models.Family;
import com.example.lessons.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;

// Built using article: https://spring.io/guides/gs/validating-form-input/
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class FamilyMvcController implements WebMvcConfigurer {

    /*
    If all bound attribute are valid, a redirect occurs to this route and template.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/familyresults").setViewName("mvc/familyresults");
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return - template for person form
        @param - Person Class
    */
    @GetMapping("/family")
    public String showForm(Family family, Model model) {
        model.addAttribute("family", family );
        model.addAttribute("person", family.getPrimary() );
        return "mvc/family";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/family")
    public String checkData(@Valid Person person, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/family";
        }
        // Redirect to next step
        return "redirect:/familyresults";
    }
}