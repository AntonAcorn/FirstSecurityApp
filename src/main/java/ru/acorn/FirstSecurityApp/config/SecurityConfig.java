package ru.acorn.FirstSecurityApp.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import ru.acorn.FirstSecurityApp.security.AuthProviderImpl;

//конфигурационный класс для Spring Security
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfiguration {
   private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    //настраивает аутентификацию
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }
}
