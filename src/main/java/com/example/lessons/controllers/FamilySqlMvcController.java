package com.example.lessons.controllers;

import com.example.lessons.modelsSQL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

// Built using article: https://spring.io/guides/gs/validating-form-input/
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class FamilySqlMvcController implements WebMvcConfigurer {

    @Autowired
    private FamilySqlRepository familySqlRepository;

    @Autowired
    private PersonSqlRepository personSqlRepository;

    /*
    If all bound attribute are valid, a redirect occurs to this route and template.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/familyresults").setViewName("mvc/sql/familyresults");
    }

    /*  The HTML template Forms and Model attributes are bound
        @return - Template for form
        @param -  Class for form
    */
    @GetMapping("/familycreate")
    public String showForm(Model model) {
        Family family = new Family();
        List<Person> listPersons = personSqlRepository.listAll();
        model.addAttribute("family", family);
        model.addAttribute("listPersons", listPersons);
        return "mvc/sql/familycreate";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/familysave")
    public String saveData(@Valid Family family, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Validation of Family attributes, validation of nested object supported
        if (bindingResult.hasErrors()) {
            return "mvc/sql/familycreate";
        }
        // Redirect to next step
        familySqlRepository.save(family);
        redirectAttributes.addAttribute("familyString", family.toString());
        return "redirect:/familyresults";
    }
}