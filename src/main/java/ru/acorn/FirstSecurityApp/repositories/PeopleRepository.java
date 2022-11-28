package ru.acorn.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.FirstSecurityApp.modells.Person;

import java.util.Optional;
//напрямую нельзя использовать repository, поэтому используем service
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
        Optional<Person> findByUsername(String username);
        Optional <Person> findByFullUsername(String username);
}
