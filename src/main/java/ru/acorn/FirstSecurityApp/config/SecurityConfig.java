package ru.acorn.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.acorn.FirstSecurityApp.services.PersonDetailsService;

//конфигурационный класс для Spring Security
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfiguration {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //конфигурируем сам Spring Security
        //конфигурируем авторизацию
        //правила авторизации читаются сверху вниз, поэтому сначала специфичные, потом общие. по приниципе if else.
        //если первый if совпал, то идем дальше в else if, если нет, то выходим в else
                http.authorizeHttpRequests().//все запросы придут через авторизацию, которая будет настроена здесь
                requestMatchers("/auth/login","/auth/registration","/error").//какой запрос пришел в приложение, если на эту страничку, то пускаем его и на страницу с er
                permitAll().
                anyRequest().authenticated().//для всех других запросов пользователь должен быть аутентицифирован
                and().//все что выше про авторизацию
                formLogin().loginPage("/auth/login").//адрес страницы авторизации в проекте
                loginProcessingUrl("/process_login").//адрес страницы, куда уходит действие, указали в html, там названия обязательнo password&username
                defaultSuccessUrl("/hello", true).//что делать если успешно, обязательно ntrue
                failureUrl("/auth/login?error").//что делать если неверно, обязательно добавить ключ error дальше смотри th html
                and().
                logout().logoutUrl("/logout").//удаляются куки браузере и удаляется сессия в приложении
                //Spring реализует логику сам, удаляет куки и закрывает сессию
                logoutSuccessUrl("/auth/login");//при удаче перенаправляет сюда
        return http.build();
    }


    //настраивает аутентификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).
                passwordEncoder(getPasswordEncoder());//security при аутентификации прогонят пароль через bCrypt
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
