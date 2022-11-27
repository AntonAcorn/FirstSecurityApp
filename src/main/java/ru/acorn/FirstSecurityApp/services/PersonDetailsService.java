package ru.acorn.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.acorn.FirstSecurityApp.modells.Person;
import ru.acorn.FirstSecurityApp.repositories.PeopleRepository;
import ru.acorn.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

//Сервис предназначается именно для SpringSecurity,
//чтобы Security знал, что именно этот сервис загружает пользователя => интерфейс
@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);
        if(person.isEmpty())
            throw new UsernameNotFoundException("Username not found!");

        return new PersonDetails(person.get());

    }
}
