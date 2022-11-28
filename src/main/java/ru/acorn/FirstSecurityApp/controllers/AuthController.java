package ru.acorn.FirstSecurityApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.acorn.FirstSecurityApp.modells.Person;
import ru.acorn.FirstSecurityApp.services.RegistrationService;
import ru.acorn.FirstSecurityApp.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }



    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person person)//в модель кладет пустого человека
                                   // Model model)
                                    {
        //model.addAttribute("person", new Person()) - равнозначно @ModelAttribute("person")Person person
        return "auth/registration";
    }
    @PostMapping("/registration")//@valid включает аннотации, которые висят в классе сущности person
    //binding result от валидатора, туда кладется информация, были ли какие-то ошибки
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(person);
        return "redirect:/auth/login";
    }
}
