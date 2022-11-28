package ru.acorn.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.FirstSecurityApp.modells.Person;
import ru.acorn.FirstSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> getPersonByFullUsername(String username){
       return peopleRepository.findByFullUsername(username);
    }
}
