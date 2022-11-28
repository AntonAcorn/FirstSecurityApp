package ru.acorn.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.acorn.FirstSecurityApp.modells.Person;
import ru.acorn.FirstSecurityApp.services.PeopleService;

//если человек уже существует в БД, то ошибка
@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(peopleService.getPersonByFullUsername(person.getUsername()).isPresent())
            errors.rejectValue("username","", "This name is already exists");
    }
}
