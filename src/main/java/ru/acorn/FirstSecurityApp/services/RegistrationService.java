package ru.acorn.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.FirstSecurityApp.modells.Person;
import ru.acorn.FirstSecurityApp.repositories.PeopleRepository;
@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional//при регистрации забираем из поля пароль и кодируем его с помощью метода passwordEncoder,
            //который возвращает bCrypt
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");//обязательно ROLE_ иначе Security не поймет
        peopleRepository.save(person);
    }
}
