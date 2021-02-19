package com.example.lessons.controllers;

import com.example.lessons.models.Family;
import com.example.lessons.models.FamilyTester;
import com.example.lessons.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /*  The HTML template Forms and Model attributes are bound
        @return - Template for form
        @param -  Class for form
    */
    @GetMapping("/family")
    public String showForm(Family family, Model model) {
        return "mvc/family";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/family")
    public String checkData(@Valid Family family, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Validation of Family attributes, validation of nested object supported
        if (bindingResult.hasErrors()) {
            return "mvc/family";
        };
        // Redirect to next step
        redirectAttributes.addAttribute("familyJSON", FamilyTester.getJSON(family).toString());
        redirectAttributes.addAttribute("familyString", family.toString());
        return "redirect:/familyresults";
    }
}