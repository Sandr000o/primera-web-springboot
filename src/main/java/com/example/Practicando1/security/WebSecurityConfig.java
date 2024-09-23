package com.example.Practicando1.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


//Registra beans
@Configuration
public class WebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1= User.builder()
                        .username("user")
                        .password("{bcrypt}$2a$10$LjlbHyU6.cHtil.6EkjEleDSPZPmPtl2Khqeo6aHrWksCtKYq/3Ku")
                        .roles("USER")
                        .build();


        UserDetails user2= User.builder().
                username("admin").
                password("{bcrypt}$2a$10$LjlbHyU6.cHtil.6EkjEleDSPZPmPtl2Khqeo6aHrWksCtKYq/3Ku").
                roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {

        //Establecemos la autorizacion de acuerdo al rol que tiene
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        //Cualqueier personas puede entrar
                        .requestMatchers("/listar").permitAll()
                        //Solo podra ingresar si tiene el rol de ADMIN o EDIT
                        .requestMatchers("/listar/nuevos").hasAnyRole("ADMIN")
                        //Verifica que el usuario tenga necesariamente el usuario ADMIN
                        .requestMatchers("/listar/editar/*","/listar/eliminar/*").hasRole("ADMIN")
                        //Cualquier otra peticion, se verificara si la persona es la que tiene acceso al recurso que quiere ingrear
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                //Si queremos entrar a alguno de los endpoint teniendo usuario USER nos llevar al archivo
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"));

        return httpSecurity.build();

    }
}
