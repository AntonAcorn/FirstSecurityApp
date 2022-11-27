package ru.acorn.FirstSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;
import ru.acorn.FirstSecurityApp.services.PersonDetailsService;

import java.util.Collections;
//класс - логика для аутентификации
//сравнивает пароль, которые пришел из формы c БД
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;
    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    //лежит логика аутентификации// из формы в authentication Spring Security передает сам
    @Override//в аргументах приходят credentials, а возвращается principal
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();//credentional из формы
        UserDetails personDetails = personDetailsService.loadUserByUsername(userName);

        String password = authentication.getCredentials().toString();
        if (!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");

        //объект Authentication, последний аргумент - это список прав
        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());

    }

    //для кого AuthProviderImpl authentication работает
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
