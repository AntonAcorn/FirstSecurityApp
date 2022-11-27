package ru.acorn.FirstSecurityApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.acorn.FirstSecurityApp.modells.Person;

import java.util.Collection;
//Этот класс является оберткой над сущностью Person, чтобы не работать на прямую над Person
public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//Роли, которые есть у пользователя
        return null;
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    //нужно чтобы доставать информацию и методы из самой сущности Person (паттерн обертка)
    public Person getPerson(){
        return this.person;
    }
}
