package com.example.Practicando1.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


//Registra beans
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthenticacion(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username,password, enabled FROM users where username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM users where username=?");
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
                //Configuramos la ruta del login personalizado
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                //Cierre de sesión
                .logout(l -> l.permitAll())
                //Si queremos entrar a alguno de los endpoint teniendo usuario USER nos llevar al archivo
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"));

        return httpSecurity.build();

    }
}
